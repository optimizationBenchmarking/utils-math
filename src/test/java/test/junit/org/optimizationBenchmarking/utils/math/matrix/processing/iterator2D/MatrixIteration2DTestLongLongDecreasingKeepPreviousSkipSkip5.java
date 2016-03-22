package test.junit.org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.junit.Assert;
import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.LongMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationDirection;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationMode;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EMissingValueMode;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DBuilder;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DState;

/** The first test for the matrix iteration */
public class MatrixIteration2DTestLongLongDecreasingKeepPreviousSkipSkip5
    extends MatrixIteration2DTest {

  /** create */
  public MatrixIteration2DTestLongLongDecreasingKeepPreviousSkipSkip5() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected void setup(final MatrixIteration2DBuilder builder) {
    builder.setEndMode(EMissingValueMode.SKIP);
    builder.setStartMode(EMissingValueMode.SKIP);
    builder.setIterationMode(EIterationMode.KEEP_PREVIOUS);
    builder.setXDirection(EIterationDirection.DECREASING);
    builder.setXDimension(0);
    builder.setYDimension(1);

    builder.setMatrices(//
        new LongMatrix1D(new long[] { //
            -0L, 0L, //
            -4L, 4L, //
            -8L, 8L, //
            -12L, 12L,//
    }, 4, 2), //
        new LongMatrix1D(new long[] { //
            -1L, 100L, //
            -5L, 500L, //
            -9L, 900L, //
            -13L, 1300L, //
    }, 4, 2), //
        new LongMatrix1D(new long[] { //
            -2L, 20000L, //
            -6L, 60000L, //
            -10L, 100000L, //
            -14L, 140000L, //
    }, 4, 2), //
        new LongMatrix1D(new long[] { //
            -3L, 3000000L, //
            -7L, 7000000L, //
            -11L, 11000000L, //
            -15L, 15000000L, //
    }, 4, 2)

    );
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

    Assert.assertTrue(x.isInteger());
    if (step <= 15) {
      Assert.assertEquals(-step, x.longValue());
    } else {
      throw new AssertionError("Only sixteen steps allowed."); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void checkY(final int step, final AbstractMatrix y) {
    super.checkY(step, y);

    Assert.assertTrue(y.isIntegerMatrix());

    switch (step) {
      case 0: {
        Assert.assertEquals(y.n(), 1);
        Assert.assertEquals(0L, y.getLong(0, 0));
        return;
      }
      case 1: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(0L, y.getLong(0, 0));
        Assert.assertEquals(100L, y.getLong(0, 1));
        return;
      }
      case 2: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(0L, y.getLong(0, 0));
        Assert.assertEquals(100L, y.getLong(0, 1));
        Assert.assertEquals(20000L, y.getLong(0, 2));
        return;
      }
      case 3: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(0L, y.getLong(0, 0));
        Assert.assertEquals(100L, y.getLong(0, 1));
        Assert.assertEquals(20000L, y.getLong(0, 2));
        Assert.assertEquals(3000000L, y.getLong(0, 3));
        return;
      }
      case 4: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(4L, y.getLong(0, 0));
        Assert.assertEquals(100L, y.getLong(0, 1));
        Assert.assertEquals(20000L, y.getLong(0, 2));
        Assert.assertEquals(3000000L, y.getLong(0, 3));
        return;
      }
      case 5: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(4L, y.getLong(0, 0));
        Assert.assertEquals(500L, y.getLong(0, 1));
        Assert.assertEquals(20000L, y.getLong(0, 2));
        Assert.assertEquals(3000000L, y.getLong(0, 3));
        return;
      }
      case 6: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(4L, y.getLong(0, 0));
        Assert.assertEquals(500L, y.getLong(0, 1));
        Assert.assertEquals(60000L, y.getLong(0, 2));
        Assert.assertEquals(3000000L, y.getLong(0, 3));
        return;
      }
      case 7: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(4L, y.getLong(0, 0));
        Assert.assertEquals(500L, y.getLong(0, 1));
        Assert.assertEquals(60000L, y.getLong(0, 2));
        Assert.assertEquals(7000000L, y.getLong(0, 3));
        return;
      }
      case 8: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(8L, y.getLong(0, 0));
        Assert.assertEquals(500L, y.getLong(0, 1));
        Assert.assertEquals(60000L, y.getLong(0, 2));
        Assert.assertEquals(7000000L, y.getLong(0, 3));
        return;
      }
      case 9: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(8L, y.getLong(0, 0));
        Assert.assertEquals(900L, y.getLong(0, 1));
        Assert.assertEquals(60000L, y.getLong(0, 2));
        Assert.assertEquals(7000000L, y.getLong(0, 3));
        return;
      }
      case 10: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(8L, y.getLong(0, 0));
        Assert.assertEquals(900L, y.getLong(0, 1));
        Assert.assertEquals(100000L, y.getLong(0, 2));
        Assert.assertEquals(7000000L, y.getLong(0, 3));
        return;
      }
      case 11: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(8L, y.getLong(0, 0));
        Assert.assertEquals(900L, y.getLong(0, 1));
        Assert.assertEquals(100000L, y.getLong(0, 2));
        Assert.assertEquals(11000000L, y.getLong(0, 3));
        return;
      }
      case 12: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(12L, y.getLong(0, 0));
        Assert.assertEquals(900L, y.getLong(0, 1));
        Assert.assertEquals(100000L, y.getLong(0, 2));
        Assert.assertEquals(11000000L, y.getLong(0, 3));
        return;
      }
      case 13: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(1300L, y.getLong(0, 0));
        Assert.assertEquals(100000L, y.getLong(0, 1));
        Assert.assertEquals(11000000L, y.getLong(0, 2));
        return;
      }
      case 14: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(140000L, y.getLong(0, 0));
        Assert.assertEquals(11000000L, y.getLong(0, 1));
        return;
      }
      case 15: {
        Assert.assertEquals(y.n(), 1);
        Assert.assertEquals(15000000L, y.getLong(0, 0));
        return;
      }

      default: {
        throw new AssertionError("Only sixteen steps allowed."); //$NON-NLS-1$
      }
    }
  }
}
