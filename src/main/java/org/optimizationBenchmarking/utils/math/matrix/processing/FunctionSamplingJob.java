package org.optimizationBenchmarking.utils.math.matrix.processing;

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
import org.optimizationBenchmarking.utils.math.functions.basic.Identity;
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
 * there should be less samples.
 * </p>
 * <p>
 * The goal is to provide a utility allowing to elegantly plot the function
 * or visually display it, even without quadratic interpolation or splines
 * (which then may add even more visual sugar ^_^). For this purpose, we
 * additional allow a posteriori transformation of the {@code x}
 * -coordinates (but we ignore this for now).
 * </p>
 * <h2>How it works</h2>
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
 * <h2>A Posteriori {@code x}-Transformation</h2>
 * <p>
 * Sometimes we want to plot a function, say {@code y=f(x)=exp(3-x^2)} over
 * a log-scaled {@code x}-axis. Of course, we want the function to look
 * good <em>after</em> transforming the {@code x}-coordinates. However, we
 * need to compute the {@code y} values <em>before</em> transforming the
 * {@code x}-coordinates, i.e., with the original values.
 * </p>
 * <p>
 * For this purpose, we provide an a posteriori {@code x} transformation:
 * The {@code y} values of the resulting matrix are computed with the
 * original {@code x} values. The {@code x} values in the matrix are the
 * transformed values. The adaptive point sampling uses the transformed
 * values, too.
 * </p>
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
   * the transformation of the {@code x}-coordinates <em>after</em> using
   * them for the computations
   */
  private final UnaryFunction m_xTransformation;

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
    this(function, min, max, null);
  }

  /**
   * Create the function sampling job
   *
   * @param function
   *          the function to be sampled
   * @param min
   *          the minimum
   * @param max
   *          the maximum
   * @param xCoordinateTransformation
   *          the transformation for the {@code x}-coordinate
   */
  public FunctionSamplingJob(final UnaryFunction function,
      final double min, final double max,
      final UnaryFunction xCoordinateTransformation) {

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

    this.m_xTransformation = ((xCoordinateTransformation != null)
        ? xCoordinateTransformation : Identity.INSTANCE);
  }

  /** {@inheritDoc} */
  @Override
  public final DoubleMatrix1D call() {
    final UnaryFunction function, transformation;
    final __Optimizer optimizer;
    final double[] result;
    double[] segments;
    int totalSegments, processedSegments;
    double xStart, xStartTransformed, xEndTransformed, yStart, xEnd, yEnd,
        xMid, xMidTransformed, yMid, minDeviationFromLinearFunction,
        minStep;
    boolean recompute;

    function = this.m_function;
    transformation = this.m_xTransformation;
    segments = new double[FunctionSamplingJob.MAX_ADAPTIVE_POINTS * 8];

    totalSegments = FunctionSamplingJob.__fillInPoint(//
        xStart = xMid = this.m_min, //
        xStartTransformed = xMidTransformed = //
        transformation.computeAsDouble(xStart), //
        yStart = yMid = function.computeAsDouble(this.m_min), //
        xEnd = this.m_max, //
        xEndTransformed = transformation.computeAsDouble(xEnd), //
        yEnd = function.computeAsDouble(this.m_max), //
        7e-5d, // the minimum deviation from a line which is still OK
        // the minimum x-space between points
        (xEndTransformed - xStartTransformed) * 1e-6d, //
        segments, 0);

    optimizer = new __Optimizer(function, transformation);

    // process the segments
    processedSegments = 0;
    result = new double[4];
    processSegments: while ((processedSegments < totalSegments)
        && (totalSegments < segments.length)) {

      xStart = segments[processedSegments++];
      xStartTransformed = segments[processedSegments++];
      yStart = segments[processedSegments++];
      xEnd = segments[processedSegments++];
      xEndTransformed = segments[processedSegments++];
      yEnd = segments[processedSegments++];
      minDeviationFromLinearFunction = segments[processedSegments++];
      minStep = segments[processedSegments++];

      if ((xStart >= xEnd) || (xStartTransformed == xEndTransformed)) {
        // The interval is empty: continue with the next segment.
        continue processSegments;
      }

      if (MathUtils.isFinite(yEnd) && MathUtils.isFinite(yStart)) {
        // Both ends of the interval are finite. We can find the point in
        // the middle which deviates the most from a linear function by
        // using a univariate optimization method.
        optimizer._solve(xStart, xStartTransformed, yStart, xEnd,
            xEndTransformed, yEnd, result);
        if (result[0] < minDeviationFromLinearFunction) {
          continue processSegments;
        }
        xMid = result[1];
        xMidTransformed = result[2];
        yMid = result[3];
      } else {
        // At least one end of the interval is non-finite. In this case, we
        // just split it in the middle and try again.
        xMid = ((xEnd + xStart) * 0.5d);
        xMidTransformed = transformation.computeAsDouble(xMid);
        yMid = function.computeAsDouble(xMid);
      }

      // We obtained a mid point at which we can split the interval into
      // two pieces. We now need to check whether this point is too close
      // to one of the interval ends. If so, we adjust it (and recompute
      // the corresponding y value).
      recompute = false;
      if (Math.abs(xMidTransformed - xStartTransformed) < minStep) {
        FunctionSamplingJob.__traceToGoal(xStart, xStartTransformed, xEnd,
            xEndTransformed,
            (xMidTransformed < xStartTransformed)
                ? (xStartTransformed - (1.01d * minStep))
                : (xStartTransformed + (1.01d * minStep)),
            transformation, result);
        if (xMid != result[0]) {
          recompute = true;
          xMid = result[0];
        }
        xMidTransformed = result[1];
      }
      if (Math.abs(xMidTransformed - xEndTransformed) < minStep) {
        FunctionSamplingJob.__traceToGoal(xStart, xStartTransformed, xEnd,
            xEndTransformed,
            (xMidTransformed < xEndTransformed)
                ? (xEndTransformed - (1.01d * minStep))
                : (xEndTransformed + (1.01d * minStep)),
            transformation, result);
        if (xMid != result[0]) {
          recompute = true;
          xMid = result[0];
        }
        xMidTransformed = result[1];
      }
      if ((Math.abs(xMidTransformed - xStartTransformed) < minStep)
          || (Math.abs(xMidTransformed - xEndTransformed) < minStep)) {
        continue processSegments;
      }
      if (recompute) {
        yMid = function.computeAsDouble(xMid);
      }

      // Ok, we can now split the segment into two new segments. We adjust
      // the minDeviationFromLinearFunction: smaller intervals need larger
      // minDeviationFromLinearFunction to
      // produce visibly different.
      minStep *= 1.7d;

      // Add first new segment.
      totalSegments = FunctionSamplingJob.__fillInPoint(xStart,
          xStartTransformed, yStart, xMid, xMidTransformed, yMid, ///
          (0.9d * minDeviationFromLinearFunction
              * Math.abs(xEndTransformed - xStartTransformed))
              / Math.abs(xMidTransformed - xStartTransformed), //
          minStep, //
          segments, totalSegments);

      if (totalSegments >= segments.length) {
        break processSegments;
      }

      // Add second new segment.
      totalSegments = FunctionSamplingJob.__fillInPoint(xMid,
          xMidTransformed, yMid, xEnd, xEndTransformed, yEnd, //
          (0.9d * minDeviationFromLinearFunction
              * Math.abs(xEndTransformed - xStartTransformed))
              / Math.abs(xEndTransformed - xMidTransformed), //
          minStep, //
          segments, totalSegments);
    }

    return this.__createMatrix(segments, (totalSegments / 8));
  }

  /**
   * try to obtain the real {@code x} coordinate fitting to the specified
   * transformed goal coordinate.
   *
   * @param xStart
   *          the start coordinate
   * @param xStartTransformed
   *          the transformed start coordinate
   * @param xEnd
   *          the end coordinate
   * @param xEndTransformed
   *          the transformed end coordinate
   * @param xGoalTransformed
   *          the transformed goal coordinate
   * @param transformation
   *          the transformation
   * @param dest
   *          the destination array
   */
  private static final void __traceToGoal(final double xStart,
      final double xStartTransformed, final double xEnd,
      final double xEndTransformed, final double xGoalTransformed,
      final UnaryFunction transformation, final double[] dest) {
    double xBest, xBestTransformed, xBestError, xMin, xMinTransformed,
        xMax, xMaxTransformed, xMid, xMidTransformed, xMidError, xMinError,
        xMaxError;
    int looper;

    xMin = xStart;
    xMinTransformed = xStartTransformed;
    xMinError = Math.abs(xMinTransformed - xGoalTransformed);
    xMax = xEnd;
    xMaxTransformed = xEndTransformed;
    xMaxError = Math.abs(xMaxTransformed - xGoalTransformed);

    if (xMinError < xMaxError) {
      xBest = xMin;
      xBestTransformed = xMinTransformed;
      xBestError = xMinError;
    } else {
      xBest = xMax;
      xBestTransformed = xMaxTransformed;
      xBestError = xMaxError;
    }

    for (looper = 1000; (--looper) >= 0;) {
      xMid = (0.5d * (xStart - xEnd));
      xMidTransformed = transformation.computeAsDouble(xMid);
      xMidError = Math.abs(xMidTransformed - xGoalTransformed);
      if (xMidError < xBestError) {
        xBest = xMid;
        xBestTransformed = xMidTransformed;
        xBestError = xMidError;
        if (xMidError <= 0d) {
          break;
        }
      }
      if (xMaxError > xMinError) {
        xMax = xMid;
        xMaxTransformed = xMidTransformed;
        xMaxError = xMidError;
      } else {
        xMin = xMid;
        xMinTransformed = xMidTransformed;
        xMinError = xMidError;
      }
      if (xMin >= xMax) {
        break;
      }
    }

    dest[0] = xBest;
    dest[1] = xBestTransformed;
  }

  /**
   * fill in a point into the array
   *
   * @param xStart
   *          the starting {@code x}-coordinate
   * @param xStartTransformed
   *          the transformed start {@code x} coordinate
   * @param yStart
   *          the starting {@code y}-coordinate
   * @param xEnd
   *          the ending {@code x}-coordinate
   * @param xEndTransformed
   *          the transformed end {@code x} coordinate
   * @param yEnd
   *          the ending {@code y}-coordinate
   * @param minDeviationFromLinearFunction
   *          the minDeviationFromLinearFunction
   * @param minStep
   *          the minimum step length
   * @param dest
   *          the destination array
   * @param created
   *          the number of points so far
   * @return the next point index
   */
  private static final int __fillInPoint(final double xStart,
      final double xStartTransformed, final double yStart,
      final double xEnd, final double xEndTransformed, final double yEnd,
      final double minDeviationFromLinearFunction, final double minStep,
      final double[] dest, final int created) {
    dest[created] = xStart;
    dest[created + 1] = xStartTransformed;
    dest[created + 2] = yStart;
    dest[created + 3] = xEnd;
    dest[created + 4] = xEndTransformed;
    dest[created + 5] = yEnd;
    dest[created + 6] = minDeviationFromLinearFunction;
    dest[created + 7] = minStep;
    return (created + 8);
  }

  /**
   * Create the matrix from the stored (adaptive) points and also add
   * {@value #GRID_POINTS} points at fixed intervals.
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
    // start point of first segment
    point[0] = segments[1];
    point[1] = segments[2];
    dataIndex = 4;

    for (pointsIndex = 1; pointsIndex <= totalSegments; pointsIndex++) {
      point = points[pointsIndex];
      // end points of segments
      point[0] = segments[dataIndex++];
      point[1] = segments[dataIndex++];
      dataIndex += 6;
    }

    // 2. Add fixed-grid points for complicated functions
    xStart = this.m_min;
    xEnd = this.m_max;
    for (dataIndex = FunctionSamplingJob.GRID_POINTS; (--dataIndex) > 0;) {
      point = points[pointsIndex++];
      xCurrent = (xStart + ((dataIndex * (xEnd - xStart))
          / FunctionSamplingJob.GRID_POINTS));
      point[0] = this.m_xTransformation.computeAsDouble(xCurrent);
      point[1] = this.m_function.computeAsDouble(xCurrent);
    }

    // 3. Sort the 2-dimensional array of [x,y] pairs
    Arrays.sort(points, new __Comparator());

    // 4. Transform array of [x,y] pairs into 1-dimensional double array,
    // using "segments" as temporary storage
    dataIndex = 0;
    segments[0] = xCurrent = xPrevious = points[0][0];
    segments[1] = yCurrent = points[0][1];
    if (xCurrent != xCurrent) {
      dataIndex = 0;
    } else {
      dataIndex = 2;
    }

    for (pointsIndex = 1; pointsIndex < points.length; pointsIndex++) {
      point = points[pointsIndex];
      xCurrent = point[0];
      if (xCurrent != xCurrent) {
        continue;
      }
      yCurrent = point[1];
      if (xCurrent > xPrevious) {
        segments[dataIndex++] = xPrevious = xCurrent;
        segments[dataIndex++] = yCurrent;
      }
    }
    if ((xCurrent == xCurrent) && (xCurrent != xPrevious)) {
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
  // matrix = new FunctionSamplingJob(Sin.INSTANCE, -10, 10, Exp.INSTANCE)
  // .call();
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
    /** the function transforming the {@code x} coordinates */
    private final UnaryFunction m_xTransformation;

    /** the range along the {@code x}-coordinates */
    private double m_xRange;
    /** the range along the {@code y}-coordinates */
    private double m_yRange;

    /** the best {@code x}-coordinate */
    private double m_xBest;
    /** the best transformed {@code x}-coordinate */
    private double m_xBestTransformed;
    /** the best {@code x}-coordinate */
    private double m_yBest;
    /** the best distance */
    private double m_distanceBest;
    /**
     * the sum term to be added when computing the distance from the line
     */
    private double m_addendum;
    /** the divisor */
    private double m_divisor;

    /**
     * create the optimizer
     *
     * @param function
     *          the objective function
     * @param xTransformation
     *          the transformation function for {@code x} coordinates
     */
    __Optimizer(final UnaryFunction function,
        final UnaryFunction xTransformation) {
      super(1e-10d, 1e-12d);
      this.m_data = new OptimizationData[] { null,
          new UnivariateObjectiveFunction(this), GoalType.MAXIMIZE,
          new MaxEval(30000), new MaxIter(30000) };
      this.m_function = function;
      this.m_xTransformation = xTransformation;
    }

    /**
     * Obtain the maximum deviation from a line with the given range
     *
     * @param xStart
     *          the starting {@code x}-coordinate
     * @param xStartTransformed
     *          the transformed start {@code x} coordinate
     * @param yStart
     *          the starting {@code y}-coordinate
     * @param xEnd
     *          the end {@code x}-coordinate
     * @param xEndTransformed
     *          the transformed end {@code x} coordinate
     * @param yEnd
     *          the end {@code y}-coordinate
     * @param dest
     *          the destination
     */
    final void _solve(final double xStart, final double xStartTransformed,
        final double yStart, final double xEnd,
        final double xEndTransformed, final double yEnd,
        final double[] dest) {

      this.m_xRange = (xEndTransformed - xStartTransformed);
      this.m_yRange = (yEnd - yStart);
      this.m_addendum = ((xEndTransformed * yStart)
          - (yEnd * xStartTransformed));
      this.m_divisor = ((this.m_xRange * this.m_xRange)
          + (this.m_yRange * this.m_yRange));

      this.m_data[0] = new SearchInterval(xStart, xEnd);
      this.m_xBest = xStartTransformed;
      this.m_yBest = yStart;
      this.m_distanceBest = Double.NEGATIVE_INFINITY;

      try {
        this.optimize(this.m_data);
      } catch (@SuppressWarnings("unused") final Throwable error) {
        // ignore
      }
      dest[0] = this.m_distanceBest;
      dest[1] = this.m_xBest;
      dest[2] = this.m_xBestTransformed;
      dest[3] = this.m_yBest;
    }

    /** {@inheritDoc} */
    @Override
    public final double value(final double x) {
      final double yMid, distance, transformedX;

      yMid = this.m_function.computeAsDouble(x);
      transformedX = this.m_xTransformation.computeAsDouble(x);
      distance = Math
          .abs(((this.m_yRange * transformedX) - (this.m_xRange * yMid))
              + this.m_addendum)
          / this.m_divisor;

      if (distance > this.m_distanceBest) {
        this.m_distanceBest = distance;
        this.m_xBest = x;
        this.m_xBestTransformed = transformedX;
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
