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
public class Matrix2DAggregateTestArithmeticMean2
    extends Matrix2DAggregateTest {

  /** create */
  public Matrix2DAggregateTestArithmeticMean2() {
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
            1L, 20L, //
            3L, 40L, //
            5L, 60L, //
            7L, 80L, //
            9L, 100L,//
    }, 5, 2), //
        new LongMatrix1D(new long[] { //
            1L, 21L, //
            3L, 41L, //
            5L, 61L, //
            7L, 81L, //
            9L, 101L,//
    }, 5, 2), //
        new LongMatrix1D(new long[] { //
            1L, 22L, //
            3L, 42L, //
            5L, 62L, //
            7L, 82L, //
            9L, 102L,//
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
