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
public class MatrixIteration2DTestDoubleDoubleDecreasingPreviewNextSkipSkip5
    extends MatrixIteration2DTest {

  /** create */
  public MatrixIteration2DTestDoubleDoubleDecreasingPreviewNextSkipSkip5() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected void setup(final MatrixIteration2DBuilder builder) {
    builder.setEndMode(EMissingValueMode.SKIP);
    builder.setStartMode(EMissingValueMode.SKIP);
    builder.setIterationMode(EIterationMode.PREVIEW_NEXT);
    builder.setXDirection(EIterationDirection.DECREASING);
    builder.setXDimension(0);
    builder.setYDimension(1);

    builder.setMatrices(//
        new DoubleMatrix1D(new double[] { //
            -0.5d, 0.5d, //
            -4.5d, 4.5d, //
            -8.5d, 8.5d, //
            -12.5d, 12.5d,//
    }, 4, 2), //
        new DoubleMatrix1D(new double[] { //
            -1.5d, 100.5d, //
            -5.5d, 500.5d, //
            -9.5d, 900.5d, //
            -13.5d, 1300.5d, //
    }, 4, 2), //
        new DoubleMatrix1D(new double[] { //
            -2.5d, 20000.5d, //
            -6.5d, 60000.5d, //
            -10.5d, 100000.5d, //
            -14.5d, 140000.5d, //
    }, 4, 2), //
        new DoubleMatrix1D(new double[] { //
            -3.5d, 3000000.5d, //
            -7.5d, 7000000.5d, //
            -11.5d, 11000000.5d, //
            -15.5d, 15000000.5d, //
    }, 4, 2));
  }

  /** {@inheritDoc} */
  @Override
  protected void checkState(final int step,
      final MatrixIteration2DState state) {
    super.checkState(step, state);

    switch (step) {
      case 0: {
        Assert.assertEquals(1, state.getSourceMatrixCount());
        Assert.assertEquals(0, state.getSourceMatrixIndex(0));
        return;
      }
      case 1: {
        Assert.assertEquals(2, state.getSourceMatrixCount());
        Assert.assertEquals(0, state.getSourceMatrixIndex(0));
        Assert.assertEquals(1, state.getSourceMatrixIndex(1));
        return;
      }
      case 2: {
        Assert.assertEquals(3, state.getSourceMatrixCount());
        Assert.assertEquals(0, state.getSourceMatrixIndex(0));
        Assert.assertEquals(1, state.getSourceMatrixIndex(1));
        Assert.assertEquals(2, state.getSourceMatrixIndex(2));
        return;
      }
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12: {
        Assert.assertEquals(4, state.getSourceMatrixCount());
        Assert.assertEquals(0, state.getSourceMatrixIndex(0));
        Assert.assertEquals(1, state.getSourceMatrixIndex(1));
        Assert.assertEquals(2, state.getSourceMatrixIndex(2));
        Assert.assertEquals(3, state.getSourceMatrixIndex(3));
        return;
      }
      case 13: {
        Assert.assertEquals(3, state.getSourceMatrixCount());
        Assert.assertEquals(1, state.getSourceMatrixIndex(0));
        Assert.assertEquals(2, state.getSourceMatrixIndex(1));
        Assert.assertEquals(3, state.getSourceMatrixIndex(2));
        return;
      }
      case 14: {
        Assert.assertEquals(2, state.getSourceMatrixCount());
        Assert.assertEquals(2, state.getSourceMatrixIndex(0));
        Assert.assertEquals(3, state.getSourceMatrixIndex(1));
        return;
      }
      case 15: {
        Assert.assertEquals(1, state.getSourceMatrixCount());
        Assert.assertEquals(3, state.getSourceMatrixIndex(0));
        return;
      }
      default: {
        throw new AssertionError("Only sixteen steps allowed."); //$NON-NLS-1$
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void checkTotalSteps(final int totalSteps) {
    super.checkTotalSteps(totalSteps);
    Assert.assertEquals(16, totalSteps);
  }

  /** {@inheritDoc} */
  @Override
  protected void checkX(final int step, final BasicNumber x) {

    super.checkX(step, x);

    Assert.assertFalse(x.isInteger());
    if (step <= 15) {
      Assert.assertEquals(-step - 0.5d, x.doubleValue(), Double.MIN_VALUE);
    } else {
      throw new AssertionError("Only sixteen steps allowed."); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void checkY(final int step, final AbstractMatrix y) {
    super.checkY(step, y);

    Assert.assertFalse(y.isIntegerMatrix());

    switch (step) {
      case 0: {
        Assert.assertEquals(y.n(), 1);
        Assert.assertEquals(0.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        return;
      }
      case 1: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(4.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(100.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        return;
      }
      case 2: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(4.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(500.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(20000.5d, y.getDouble(0, 2), Double.MIN_VALUE);
        return;
      }
      case 3: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(4.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(500.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(60000.5d, y.getDouble(0, 2), Double.MIN_VALUE);
        Assert.assertEquals(3000000.5d, y.getDouble(0, 3),
            Double.MIN_VALUE);
        return;
      }
      case 4: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(4.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(500.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(60000.5d, y.getDouble(0, 2), Double.MIN_VALUE);
        Assert.assertEquals(7000000.5d, y.getDouble(0, 3),
            Double.MIN_VALUE);
        return;
      }
      case 5: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(8.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(500.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(60000.5d, y.getDouble(0, 2), Double.MIN_VALUE);
        Assert.assertEquals(7000000.5d, y.getDouble(0, 3),
            Double.MIN_VALUE);
        return;
      }
      case 6: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(8.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(900.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(60000.5d, y.getDouble(0, 2), Double.MIN_VALUE);
        Assert.assertEquals(7000000.5d, y.getDouble(0, 3),
            Double.MIN_VALUE);
        return;
      }
      case 7: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(8.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(900.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(100000.5d, y.getDouble(0, 2),
            Double.MIN_VALUE);
        Assert.assertEquals(7000000.5d, y.getDouble(0, 3),
            Double.MIN_VALUE);
        return;
      }
      case 8: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(8.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(900.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(100000.5d, y.getDouble(0, 2),
            Double.MIN_VALUE);
        Assert.assertEquals(11000000.5d, y.getDouble(0, 3),
            Double.MIN_VALUE);
        return;
      }
      case 9: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(12.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(900.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(100000.5d, y.getDouble(0, 2),
            Double.MIN_VALUE);
        Assert.assertEquals(11000000.5d, y.getDouble(0, 3),
            Double.MIN_VALUE);
        return;
      }
      case 10: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(12.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(1300.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(100000.5d, y.getDouble(0, 2),
            Double.MIN_VALUE);
        Assert.assertEquals(11000000.5d, y.getDouble(0, 3),
            Double.MIN_VALUE);
        return;
      }
      case 11: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(12.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(1300.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(140000.5d, y.getDouble(0, 2),
            Double.MIN_VALUE);
        Assert.assertEquals(11000000.5d, y.getDouble(0, 3),
            Double.MIN_VALUE);
        return;
      }
      case 12: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(12.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(1300.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        Assert.assertEquals(140000.5d, y.getDouble(0, 2),
            Double.MIN_VALUE);
        Assert.assertEquals(15000000.5d, y.getDouble(0, 3),
            Double.MIN_VALUE);
        return;
      }
      case 13: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(1300.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(140000.5d, y.getDouble(0, 1),
            Double.MIN_VALUE);
        Assert.assertEquals(15000000.5d, y.getDouble(0, 2),
            Double.MIN_VALUE);
        return;
      }
      case 14: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(140000.5d, y.getDouble(0, 0),
            Double.MIN_VALUE);
        Assert.assertEquals(15000000.5d, y.getDouble(0, 1),
            Double.MIN_VALUE);
        return;
      }
      case 15: {
        Assert.assertEquals(y.n(), 1);
        Assert.assertEquals(15000000.5d, y.getDouble(0, 0),
            Double.MIN_VALUE);
        return;
      }

      default: {
        throw new AssertionError("Only sixteen steps allowed."); //$NON-NLS-1$
      }
    }
  }
}
