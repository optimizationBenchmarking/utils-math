package org.optimizationBenchmarking.utils.math.matrix;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.univariate.BrentOptimizer;
import org.apache.commons.math3.optim.univariate.SearchInterval;
import org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction;
import org.optimizationBenchmarking.utils.math.MathUtils;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;
import org.optimizationBenchmarking.utils.math.matrix.impl.DoubleMatrix1D;
import org.optimizationBenchmarking.utils.tools.spec.ICallableToolJob;

/**
 * <p>
 * This job attempts to transform a {@link UnaryFunction} into a
 * two-dimensional matrix where each row represents an {@code (x, y)} tuple
 * of input value {@code x} for the function and its corresponding output
 * value {@code y}. The goal is to obtain a matrix such that it closely
 * represents the function's visual shape. In areas where the function
 * changes quickly, the matrix should contain more samples. In areas where
 * the function can closely be approximated by a line between two points,
 * there should be less samples. This will allow us to elegantly plot the
 * function or visually display it, even without quadratic interpolation or
 * splines (which then may add even more visual sugar ^_^).
 * </p>
 * <p>
 * Obtaining such a representation is actually not easy, since some
 * functions have odd behaviors (jumping points/discontinuities, very large
 * or very small values, etc). We try to tackle this task in two ways:
 * </p>
 * <ol>
 * <li>First, we compute a fixed number of samples from an equally-spaced
 * grid of {@value #GRID_POINTS} cells.</li>
 * <li>Second, we allow for at most {@value #MAX_ADAPTIVE_POINTS} adaptive
 * points to be added, whose locations are automatically detected by trying
 * to find areas where the function deviates the most from a linear
 * function.</li>
 * </ol>
 * <p>
 * The second step is done by trying to find a point {@code (xMid, yMid)}
 * where the function to be samples has the largest scaled distance from
 * the line {@code (xStart, yStart), (xEnd, yEnd)}. If the scaled distance
 * is large enough, the interval {@code [xStart, xEnd]} is divided into two
 * intervals {@code [xStart, xMid]}, {@code [xMid, xEnd]} and the point
 * {@code (xMid, yMid)} is added to the set of samples. The two new
 * intervals are then processed in the same manner recursively.
 * </p>
 * <p>
 * For finding {@code xMid}, we use a simple univariate optimization
 * method, namely
 * {@link org.apache.commons.math3.optim.univariate.BrentOptimizer}.
 * </p>
 * .
 */
public class FunctionSamplingJob
    implements ICallableToolJob<DoubleMatrix1D> {

  /** the maximum number of adaptively sampled points in a plot */
  private static final int MAX_ADAPTIVE_POINTS = 3096;
  /** the number of points sampled from the grid */
  private static final int GRID_POINTS = 128;

  /** the unary function */
  private final UnaryFunction m_function;

  /** the minimum of the range */
  private final double m_min;

  /** the maximum of the range */
  private final double m_max;

  /**
   * Create the function sampling job
   *
   * @param function
   *          the function to be sampled
   * @param min
   *          the minimum
   * @param max
   *          the maximum
   */
  public FunctionSamplingJob(final UnaryFunction function,
      final double min, final double max) {
    super();
    if (function == null) {
      throw new IllegalArgumentException("Function cannot be null."); //$NON-NLS-1$
    }
    if (max <= min) {
      throw new IllegalArgumentException(
          "Maximum cannot be smaller or equal than minimum, but " + //$NON-NLS-1$
              max + " is smaller/equal to " + min); //$NON-NLS-1$
    }
    if (!(MathUtils.isFinite(max))) {
      throw new IllegalArgumentException(
          "Maximum must be finite, but is " + max); //$NON-NLS-1$
    }
    if (!(MathUtils.isFinite(min))) {
      throw new IllegalArgumentException(
          "Minimum must be finite, but is " + min); //$NON-NLS-1$
    }
    this.m_function = function;
    this.m_min = min;
    this.m_max = max;
  }

  /** {@inheritDoc} */
  @Override
  public final DoubleMatrix1D call() {
    final UnaryFunction function;
    final __Optimizer optimizer;
    final double[] result;
    double[] segments;
    int totalSegments, processedSegments;
    double xStart, yStart, xEnd, yEnd, xMid, yMid, multiplier;

    function = this.m_function;
    segments = new double[FunctionSamplingJob.MAX_ADAPTIVE_POINTS * 5];

    totalSegments = FunctionSamplingJob.__fillInPoint(//
        xStart = xMid = this.m_min, //
        yStart = yMid = function.computeAsDouble(this.m_min), //
        xEnd = this.m_max, //
        yEnd = function.computeAsDouble(this.m_max), //
        7e-5d, segments, 0);

    optimizer = new __Optimizer(function);

    // process the segments
    processedSegments = 0;
    result = new double[3];
    processSegments: while ((processedSegments < totalSegments)
        && (totalSegments < segments.length)) {

      xStart = segments[processedSegments++];
      yStart = segments[processedSegments++];
      xEnd = segments[processedSegments++];
      yEnd = segments[processedSegments++];
      multiplier = segments[processedSegments++];

      if ((!(MathUtils.isFinite(yEnd) && MathUtils.isFinite(yStart)))
          || (xStart >= xEnd)) {
        continue processSegments;
      }

      if (totalSegments >= segments.length) {
        break processSegments;
      }

      optimizer._solve(xStart, yStart, xEnd, yEnd, result);

      if (result[0] < multiplier) {
        continue processSegments;
      }

      xMid = result[1];
      if ((xMid <= xStart) || (xMid >= xEnd)) {
        continue processSegments;
      }

      yMid = result[2];
      totalSegments = FunctionSamplingJob.__fillInPoint(xStart, yStart,
          xMid, yMid,
          (0.9d * multiplier * (xEnd - xStart)) / (xMid - xStart),
          segments, totalSegments);

      if (totalSegments >= segments.length) {
        break processSegments;
      }

      totalSegments = FunctionSamplingJob.__fillInPoint(xMid, yMid, xEnd,
          yEnd, (0.9d * multiplier * (xEnd - xStart)) / (xEnd - xMid),
          segments, totalSegments);
    }

    return this.__createMatrix(segments, (totalSegments / 5));

  }

  /**
   * fill in a point into the array
   *
   * @param xStart
   *          the starting {@code x}-coordinate
   * @param yStart
   *          the starting {@code y}-coordinate
   * @param xEnd
   *          the ending {@code x}-coordinate
   * @param yEnd
   *          the ending {@code y}-coordinate
   * @param multiplier
   *          the multiplier
   * @param dest
   *          the destination array
   * @param created
   *          the number of points so far
   * @return the next point index
   */
  private static final int __fillInPoint(final double xStart,
      final double yStart, final double xEnd, final double yEnd,
      final double multiplier, final double[] dest, final int created) {
    dest[created] = xStart;
    dest[created + 1] = yStart;
    dest[created + 2] = xEnd;
    dest[created + 3] = yEnd;
    dest[created + 4] = multiplier;
    return (created + 5);
  }

  /**
   * Create the matrix from the stored points
   *
   * @param segments
   *          the segments
   * @param totalSegments
   *          the total number of segments
   * @return the matrix
   */
  private final DoubleMatrix1D __createMatrix(final double[] segments,
      final int totalSegments) {
    double[][] points;
    double[] point;
    double xPrevious, xCurrent, yCurrent, xStart, xEnd;
    int dataIndex, pointsIndex;

    points = new double[totalSegments
        + FunctionSamplingJob.GRID_POINTS][2];

    // 1. Transform segments into 2-dimensional array of [x,y] pairs
    point = points[0];
    dataIndex = 0;
    // start point of first segment
    point[0] = segments[dataIndex++];
    point[1] = segments[dataIndex++];

    for (pointsIndex = 1; pointsIndex <= totalSegments; pointsIndex++) {
      point = points[pointsIndex];
      // end points of segments
      point[0] = segments[dataIndex++];
      point[1] = segments[dataIndex++];
      dataIndex += 3;
    }

    // 2. Add fixed-grid points for complicated functions
    xStart = this.m_max;
    xEnd = this.m_min;
    for (dataIndex = FunctionSamplingJob.GRID_POINTS; (--dataIndex) > 0;) {
      point = points[pointsIndex++];

      point[0] = xCurrent = (xStart + ((dataIndex * (xEnd - xStart))
          / FunctionSamplingJob.GRID_POINTS));
      point[1] = this.m_function.computeAsDouble(xCurrent);
    }

    // 3. Sort the 2-dimensional array of [x,y] pairs
    Arrays.sort(points, new __Comparator());

    // 4. Transform array of [x,y] pairs into 1-dimensional double array,
    // using "segments" as temporary storage
    dataIndex = 0;
    segments[dataIndex++] = xCurrent = xPrevious = points[0][0];
    segments[dataIndex++] = yCurrent = points[0][1];

    for (pointsIndex = 1; pointsIndex < points.length; pointsIndex++) {
      point = points[pointsIndex];
      xCurrent = point[0];
      yCurrent = point[1];
      if (xCurrent > xPrevious) {
        segments[dataIndex++] = xPrevious = xCurrent;
        segments[dataIndex++] = yCurrent;
      }
    }
    if (xCurrent != xPrevious) {
      segments[dataIndex++] = xCurrent;
      segments[dataIndex++] = yCurrent;
    }

    points = null;

    // 5. The array will definitely be too long, so resize.
    point = new double[dataIndex];
    System.arraycopy(segments, 0, point, 0, dataIndex);

    // 6. Return created matrix
    return new DoubleMatrix1D(point, (point.length >>> 1), 2);
  }

  /** the internal comparator */
  private static final class __Comparator implements Comparator<double[]> {
    /** create */
    __Comparator() {
      super();
    }

    /** {@inheritDoc} */
    @Override
    public final int compare(final double[] o1, final double[] o2) {
      final double a, b;
      a = o1[0];
      b = o2[0];
      if (a < b) {
        return -1;
      }
      if (a > b) {
        return 1;
      }
      if (a == b) {
        return 0;
      }
      if (a != a) {
        if (b != b) {
          return 0;
        }
        return 1;
      }
      return -1;
    }
  }

  // /**
  // * the main routine
  // *
  // * @param args
  // * the args
  // */
  // public static final void main(final String[] args) {
  // DoubleMatrix1D matrix;
  // int index;
  // double cur, prev;
  //
  // matrix = new FunctionSamplingJob(BesselJ1.INSTANCE, 0, 10).call();
  //
  // System.out.println(matrix.m());
  // System.out.println();
  // prev = Double.POSITIVE_INFINITY;
  // for (index = 0; index < matrix.m(); index++) {
  // cur = matrix.getDouble(index, 0);
  // System.out.print(cur);
  // System.out.print('\t');
  // System.out.print(matrix.getDouble(index, 1));
  // System.out.print('\t');
  // System.out.println(cur - prev);
  // prev = cur;
  // }
  // }

  /** the internal optimizer */
  private static final class __Optimizer extends BrentOptimizer
      implements UnivariateFunction {

    /** the data */
    private final OptimizationData[] m_data;

    /** the function */
    private final UnaryFunction m_function;

    /** the range along the {@code x}-coordinates */
    private double m_xRange;
    /** the range along the {@code y}-coordinates */
    private double m_yRange;

    /** the best {@code x}-coordinate */
    private double m_xBest;
    /** the best {@code x}-coordinate */
    private double m_yBest;
    /** the best distance */
    private double m_distanceBest;
    /** the multiplier */
    private double m_addendum;
    /** the divisor */
    private double m_divisor;

    /**
     * create the optimizer
     *
     * @param function
     *          the objective function
     */
    __Optimizer(final UnaryFunction function) {
      super(1e-10d, 1e-12d);
      this.m_data = new OptimizationData[] { null,
          new UnivariateObjectiveFunction(this), GoalType.MAXIMIZE,
          new MaxEval(30000), new MaxIter(30000) };
      this.m_function = function;
    }

    /**
     * Obtain the maximum deviation from a line with the given range
     *
     * @param xStart
     *          the starting {@code x}-coordinate
     * @param yStart
     *          the starting {@code y}-coordinate
     * @param xEnd
     *          the en d {@code x}-coordinate
     * @param yEnd
     *          the end {@code y}-coordinate
     * @param dest
     *          the destination
     */
    final void _solve(final double xStart, final double yStart,
        final double xEnd, final double yEnd, final double[] dest) {

      this.m_xRange = (xEnd - xStart);
      this.m_yRange = (yEnd - yStart);
      this.m_addendum = ((xEnd * yStart) - (yEnd * xStart));
      this.m_divisor = ((this.m_xRange * this.m_xRange)
          + (this.m_yRange * this.m_yRange));

      this.m_data[0] = new SearchInterval(xStart, xEnd);
      this.m_xBest = xStart;
      this.m_yBest = yStart;
      this.m_distanceBest = Double.NEGATIVE_INFINITY;

      try {
        this.optimize(this.m_data);
      } catch (@SuppressWarnings("unused") final Throwable error) {
        // ignore
      }
      dest[0] = this.m_distanceBest;
      dest[1] = this.m_xBest;
      dest[2] = this.m_yBest;
    }

    /** {@inheritDoc} */
    @Override
    public final double value(final double x) {
      final double yMid, distance;

      yMid = this.m_function.computeAsDouble(x);
      distance = Math.abs(
          ((this.m_yRange * x) - (this.m_xRange * yMid)) + this.m_addendum)
          / this.m_divisor;

      if (distance > this.m_distanceBest) {
        this.m_distanceBest = distance;
        this.m_xBest = x;
        this.m_yBest = yMid;
      } else {
        if (distance != distance) {
          return -1d;
        }
      }
      return distance;
    }
  }
}
