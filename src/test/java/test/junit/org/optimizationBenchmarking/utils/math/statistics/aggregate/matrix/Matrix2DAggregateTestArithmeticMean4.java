package test.junit.org.optimizationBenchmarking.utils.math.statistics.aggregate.matrix;

import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.LongMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.CallableMatrixIteration2DBuilder;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationDirection;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationMode;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EMissingValueMode;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.ArithmeticMeanAggregate;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.Matrix2DAggregate;

/** A basic test for trivial mean aggregates */
public class Matrix2DAggregateTestArithmeticMean4
    extends Matrix2DAggregateTest {

  /** create */
  public Matrix2DAggregateTestArithmeticMean4() {
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
        new Matrix2DAggregate(new ArithmeticMeanAggregate(), null));

    builder.setMatrices(//
        new LongMatrix1D(new long[] { //
            1L, 2L, //
            3L, 4L, //
            4L, 4L, //
            5L, 6L, //
            7L, 8L, //
            9L, 10L,//
    }, 6, 2), //
        new LongMatrix1D(new long[] { //
            1L, 2L, //
            3L, 4L, //
            5L, 6L, //
            7L, 8L, //
            8L, 8L, //
            9L, 10L,//
    }, 6, 2), //
        new LongMatrix1D(new long[] { //
            1L, 2L, //
            3L, 4L, //
            5L, 6L, //
            5L, 6L, //
            5L, 6L, //
            5L, 6L, //
            5L, 6L, //
            5L, 6L, //
            5L, 6L, //
            7L, 8L, //
            9L, 10L,//
    }, 11, 2), //
        new LongMatrix1D(new long[] { //
            1L, 2L, //
            1L, 2L, //
            1L, 2L, //
            1L, 2L, //
            1L, 2L, //
            3L, 4L, //
            5L, 6L, //
            7L, 8L, //
            9L, 10L,//
    }, 9, 2), //
        new LongMatrix1D(new long[] { //
            1L, 2L, //
            3L, 4L, //
            5L, 6L, //
            7L, 8L, //
            9L, 10L,//
    }, 5, 2), //
        new LongMatrix1D(new long[] { //
            1L, 2L, //
            3L, 4L, //
            5L, 6L, //
            7L, 8L, //
            9L, 10L,//
    }, 5, 2), //
        new LongMatrix1D(new long[] { //
            1L, 2L, //
            3L, 4L, //
            5L, 6L, //
            7L, 8L, //
            9L, 10L,//
    }, 5, 2));
  }

  /** {@inheritDoc} */
  @Override
  protected AbstractMatrix getExpectedResult() {
    return new LongMatrix1D(new long[] { //
        1L, 2L, //
        3L, 4L, //
        5L, 6L, //
        7L, 8L, //
        9L, 10L,//
    }, 5, 2);
  }
}
