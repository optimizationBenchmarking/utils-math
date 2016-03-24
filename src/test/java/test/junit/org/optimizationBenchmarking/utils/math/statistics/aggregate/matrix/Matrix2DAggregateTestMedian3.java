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
public class Matrix2DAggregateTestMedian3 extends Matrix2DAggregateTest {

  /** create */
  public Matrix2DAggregateTestMedian3() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected void setup(
      final CallableMatrixIteration2DBuilder<AbstractMatrix> builder) {
    super.setup(builder);

    builder.setStartReplacement(Double.valueOf(Double.POSITIVE_INFINITY));
    builder.setEndMode(EMissingValueMode.SKIP);
    builder.setIterationMode(EIterationMode.KEEP_PREVIOUS);
    builder.setXDirection(EIterationDirection.INCREASING);

    builder.setVisitor(
        new Matrix2DAggregate(new QuantileAggregate(0.5d), null));

    builder.setMatrices(//
        new LongMatrix1D(new long[] { //
            10L, 20L, //
            30L, 40L, //
            50L, 60L, //
            70L, 80L, //
            90L, 100L,//
    }, 5, 2), //
        new LongMatrix1D(new long[] { //
            30L, 40L, //
            50L, 60L, //
            70L, 80L, //
            90L, 100L,//
    }, 4, 2), //
        new LongMatrix1D(new long[] { //
            50L, 60L, //
            70L, 80L, //
            90L, 100L,//
    }, 3, 2));
  }

  /** {@inheritDoc} */
  @Override
  protected AbstractMatrix getExpectedResult() {
    return new DoubleMatrix1D(new double[] { //
        10d, Double.POSITIVE_INFINITY, //
        30d, 40d, //
        50L, 60L, //
        70L, 80L, //
        90L, 100L,//
    }, 5, 2);
  }
}
