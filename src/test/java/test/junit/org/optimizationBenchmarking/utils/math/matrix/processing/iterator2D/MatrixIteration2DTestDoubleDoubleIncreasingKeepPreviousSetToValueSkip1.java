package test.junit.org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.junit.Assert;
import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.DoubleMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationDirection;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationMode;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EMissingValueMode;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DBuilder;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DState;

/** The first test for the matrix iteration */
public class MatrixIteration2DTestDoubleDoubleIncreasingKeepPreviousSetToValueSkip1
    extends MatrixIteration2DTest {

  /** create */
  public MatrixIteration2DTestDoubleDoubleIncreasingKeepPreviousSetToValueSkip1() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected void setup(final MatrixIteration2DBuilder builder) {
    builder.setEndMode(EMissingValueMode.SKIP);
    builder.setStartReplacement(Double.valueOf(321.5d));
    builder.setIterationMode(EIterationMode.KEEP_PREVIOUS);
    builder.setXDirection(EIterationDirection.INCREASING);
    builder.setXDimension(0);
    builder.setYDimension(1);

    builder.setMatrices(//
        new DoubleMatrix1D(new double[] { //
            0.5d, 10.5d, //
            1.5d, 20.5d, //
            2.5d, 30.5d, //
            3.5d, 40.5d,//
    }, 4, 2), //
        new DoubleMatrix1D(new double[] { //
            -1.5d, 100.5d, //
            0.5d, 200.5d, //
            1.5d, 300.5d, //
            2.5d, 400.5d, //
            3.5d, 500.5d, //
            4.5d, 600.5d,//
    }, 6, 2), //
        new DoubleMatrix1D(new double[] { //
            Double.NEGATIVE_INFINITY, 999999.5d, //
            0.5d, 99999.5d, //
            1.5d, 9999.5d, //
            2.5d, 999.5d, //
            3.5d, 99.5d, //
            Double.POSITIVE_INFINITY, 9.5d,//
    }, 6, 2)

    );
  }

  /** {@inheritDoc} */
  @Override
  protected void checkState(final int step,
      final MatrixIteration2DState state) {
    super.checkState(step, state);

    switch (step) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5: {
        Assert.assertEquals(3, state.getSourceMatrixCount());
        Assert.assertEquals(0, state.getSourceMatrixIndex(0));
        Assert.assertEquals(1, state.getSourceMatrixIndex(1));
        Assert.assertEquals(2, state.getSourceMatrixIndex(2));
        return;
      }
      case 6: {
        Assert.assertEquals(2, state.getSourceMatrixCount());
        Assert.assertEquals(1, state.getSourceMatrixIndex(0));
        Assert.assertEquals(2, state.getSourceMatrixIndex(1));
        return;
      }
      case 7: {
        Assert.assertEquals(1, state.getSourceMatrixCount());
        Assert.assertEquals(2, state.getSourceMatrixIndex(0));
        return;
      }
      default: {
        throw new AssertionError("Only eight steps allowed."); //$NON-NLS-1$
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void checkTotalSteps(final int totalSteps) {
    super.checkTotalSteps(totalSteps);
    Assert.assertEquals(8, totalSteps);
  }

  /** {@inheritDoc} */
  @Override
  protected void checkX(final int step, final BasicNumber x) {

    super.checkX(step, x);

    Assert.assertFalse(x.isInteger());

    switch (step) {
      case 0: {
        Assert.assertEquals(Double.NEGATIVE_INFINITY, x.doubleValue(),
            Double.MIN_VALUE);
        return;
      }
      case 1: {
        Assert.assertEquals(-1.5d, x.doubleValue(), Double.MIN_VALUE);
        return;
      }
      case 2: {
        Assert.assertEquals(0.5d, x.doubleValue(), Double.MIN_VALUE);
        return;
      }
      case 3: {
        Assert.assertEquals(1.5d, x.doubleValue(), Double.MIN_VALUE);
        return;
      }
      case 4: {
        Assert.assertEquals(2.5d, x.doubleValue(), Double.MIN_VALUE);
        return;
      }
      case 5: {
        Assert.assertEquals(3.5d, x.doubleValue(), Double.MIN_VALUE);
        return;
      }
      case 6: {
        Assert.assertEquals(4.5d, x.doubleValue(), Double.MIN_VALUE);
        return;
      }
      case 7: {
        Assert.assertEquals(Double.POSITIVE_INFINITY, x.doubleValue(),
            Double.MIN_VALUE);
        return;
      }
      default: {
        throw new AssertionError("Only eight steps allowed."); //$NON-NLS-1$
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void checkY(final int step, final AbstractMatrix y) {
    super.checkY(step, y);

    Assert.assertFalse(y.isIntegerMatrix());

    switch (step) {
      case 0: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(321.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(321.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(999999.5d, y.getDouble(0, 2),
            Double.MIN_VALUE);
        return;
      }
      case 1: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(321.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(100.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(999999.5d, y.getDouble(0, 2),
            Double.MIN_VALUE);
        return;
      }
      case 2: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(10.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(200.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(99999.5d, y.getDouble(0, 2), Double.MIN_VALUE);
        return;
      }
      case 3: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(20.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(300.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(9999.5d, y.getDouble(0, 2), Double.MIN_VALUE);
        return;
      }
      case 4: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(30.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(400.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(999.5d, y.getDouble(0, 2), Double.MIN_VALUE);
        return;
      }
      case 5: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(40.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(500.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(99.5d, y.getDouble(0, 2), Double.MIN_VALUE);
        return;
      }
      case 6: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(600.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(99.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        return;
      }
      case 7: {
        Assert.assertEquals(y.n(), 1);
        Assert.assertEquals(9.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        return;
      }
      default: {
        throw new AssertionError("Only eight steps allowed."); //$NON-NLS-1$
      }
    }
  }
}
