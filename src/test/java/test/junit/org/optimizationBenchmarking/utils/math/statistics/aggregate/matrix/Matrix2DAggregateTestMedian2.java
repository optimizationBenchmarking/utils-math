package test.junit.org.optimizationBenchmarking.utils.math.statistics.aggregate.matrix;

import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.DoubleMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.impl.LongMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.CallableMatrixIteration2DBuilder;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationDirection;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationMode;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EMissingValueMode;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.Matrix2DAggregate;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.QuantileAggregate;

/** A basic test for trivial mean aggregates */
public class Matrix2DAggregateTestMedian2 extends Matrix2DAggregateTest {

  /** create */
  public Matrix2DAggregateTestMedian2() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected void setup(
      final CallableMatrixIteration2DBuilder<AbstractMatrix> builder) {
    super.setup(builder);

    builder.setStartMode(EMissingValueMode.SKIP);
    builder.setEndMode(EMissingValueMode.SKIP);
    builder.setIterationMode(EIterationMode.KEEP_PREVIOUS);
    builder.setXDirection(EIterationDirection.INCREASING);

    builder.setVisitor(
        new Matrix2DAggregate(new QuantileAggregate(0.5d), null));

    builder.setMatrices(//
        new DoubleMatrix1D(new double[] { //
            1L, 20.5d, //
            3L, 40.5d, //
            5L, 60.5d, //
            7L, 80.5d, //
            9L, 100.5d,//
    }, 5, 2), //
        new DoubleMatrix1D(new double[] { //
            1L, 21L, //
            3L, 41L, //
            5L, 61L, //
            7L, 81L, //
            9L, 101L,//
    }, 5, 2), //
        new DoubleMatrix1D(new double[] { //
            1L, 21L, //
            3L, 41L, //
            5L, 61L, //
            7L, 81L, //
            9L, 101L,//
    }, 5, 2), //
        new DoubleMatrix1D(new double[] { //
            1L, 21.5d, //
            3L, 41.5d, //
            5L, 61.5d, //
            7L, 81.5d, //
            9L, 101.5d,//
    }, 5, 2));
  }

  /** {@inheritDoc} */
  @Override
  protected AbstractMatrix getExpectedResult() {
    return new LongMatrix1D(new long[] { //
        1L, 21L, //
        3L, 41L, //
        5L, 61L, //
        7L, 81L, //
        9L, 101L,//
    }, 5, 2);
  }
}
