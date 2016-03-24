package test.junit.org.optimizationBenchmarking.utils.math.statistics.aggregate.matrix;

import org.optimizationBenchmarking.utils.math.functions.power.Sqrt;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.DoubleMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.impl.LongMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.CallableMatrixIteration2DBuilder;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationDirection;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationMode;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EMissingValueMode;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.ArithmeticMeanAggregate;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.Matrix2DAggregate;

/** A basic test for trivial mean aggregates */
public class Matrix2DAggregateTestArithmeticMean5
    extends Matrix2DAggregateTest {

  /** create */
  public Matrix2DAggregateTestArithmeticMean5() {
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

    builder.setVisitor(new Matrix2DAggregate(new ArithmeticMeanAggregate(),
        Sqrt.INSTANCE));

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
            1L, 3L, //
            3L, 5L, //
            5L, 7L, //
            7L, 9L, //
            8L, 9L, //
            9L, 11L,//
    }, 6, 2));
  }

  /** {@inheritDoc} */
  @Override
  protected AbstractMatrix getExpectedResult() {
    return new DoubleMatrix1D(new double[] { //
        1L, Sqrt.INSTANCE.computeAsDouble(2.5d), //
        3L, Sqrt.INSTANCE.computeAsDouble(4.5d), //
        5L, Sqrt.INSTANCE.computeAsDouble(6.5d), //
        7L, Sqrt.INSTANCE.computeAsDouble(8.5d), //
        9L, Sqrt.INSTANCE.computeAsDouble(10.5d),//
    }, 5, 2);
  }
}
