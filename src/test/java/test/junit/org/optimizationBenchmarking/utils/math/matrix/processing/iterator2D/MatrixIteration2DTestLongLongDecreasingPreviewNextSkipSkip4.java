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

/** A test for the matrix iteration */
public class MatrixIteration2DTestLongLongDecreasingPreviewNextSkipSkip4
    extends MatrixIteration2DTest {

  /** create */
  public MatrixIteration2DTestLongLongDecreasingPreviewNextSkipSkip4() {
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
        new LongMatrix1D(new long[] { //
            3L, 10L, //
            2L, 20L, //
            1L, 30L, //
            0L, 40L,//
    }, 4, 2), //
        new LongMatrix1D(new long[] { //
            4L, 100L, //
            3L, 200L, //
            2L, 300L, //
            1L, 400L, //
            0L, 500L, //
            -1L, 600L,//
    }, 6, 2), //
        new LongMatrix1D(new long[] { //
            Long.MAX_VALUE, 999999L, //
            3L, 99999L, //
            2L, 9999L, //
            1L, 999L, //
            0L, 99L, //
            Long.MIN_VALUE, 9L,//
    }, 6, 2), //
        new LongMatrix1D(new long[] { //
            Long.MAX_VALUE - 1L, 888888L, //
            3L, 88888L, //
            2L, 8888L, //
            1L, 888L, //
            0L, 88L, //
            Long.MIN_VALUE + 1L, 8L,//
    }, 6, 2)

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
        Assert.assertEquals(2, state.getSourceMatrixIndex(0));
        return;
      }
      case 1: {
        Assert.assertEquals(2, state.getSourceMatrixCount());
        Assert.assertEquals(2, state.getSourceMatrixIndex(0));
        Assert.assertEquals(3, state.getSourceMatrixIndex(1));
        return;
      }
      case 2: {
        Assert.assertEquals(3, state.getSourceMatrixCount());
        Assert.assertEquals(1, state.getSourceMatrixIndex(0));
        Assert.assertEquals(2, state.getSourceMatrixIndex(1));
        Assert.assertEquals(3, state.getSourceMatrixIndex(2));
        return;
      }
      case 3:
      case 4:
      case 5:
      case 6: {
        Assert.assertEquals(4, state.getSourceMatrixCount());
        Assert.assertEquals(0, state.getSourceMatrixIndex(0));
        Assert.assertEquals(1, state.getSourceMatrixIndex(1));
        Assert.assertEquals(2, state.getSourceMatrixIndex(2));
        Assert.assertEquals(3, state.getSourceMatrixIndex(3));
        return;
      }
      case 7: {
        Assert.assertEquals(3, state.getSourceMatrixCount());
        Assert.assertEquals(1, state.getSourceMatrixIndex(0));
        Assert.assertEquals(2, state.getSourceMatrixIndex(1));
        Assert.assertEquals(3, state.getSourceMatrixIndex(2));
        return;
      }
      case 8: {
        Assert.assertEquals(2, state.getSourceMatrixCount());
        Assert.assertEquals(2, state.getSourceMatrixIndex(0));
        Assert.assertEquals(3, state.getSourceMatrixIndex(1));
        return;
      }
      case 9: {
        Assert.assertEquals(1, state.getSourceMatrixCount());
        Assert.assertEquals(2, state.getSourceMatrixIndex(0));
        return;
      }
      default: {
        throw new AssertionError("Only ten steps allowed."); //$NON-NLS-1$
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void checkTotalSteps(final int totalSteps) {
    super.checkTotalSteps(totalSteps);
    Assert.assertEquals(10, totalSteps);
  }

  /** {@inheritDoc} */
  @Override
  protected void checkX(final int step, final BasicNumber x) {

    super.checkX(step, x);

    Assert.assertTrue(x.isInteger());

    switch (step) {
      case 0: {
        Assert.assertEquals(Long.MAX_VALUE, x.longValue());
        return;
      }
      case 1: {
        Assert.assertEquals(Long.MAX_VALUE - 1L, x.longValue());
        return;
      }
      case 2: {
        Assert.assertEquals(4L, x.longValue());
        return;
      }
      case 3: {
        Assert.assertEquals(3L, x.longValue());
        return;
      }
      case 4: {
        Assert.assertEquals(2L, x.longValue());
        return;
      }
      case 5: {
        Assert.assertEquals(1L, x.longValue());
        return;
      }
      case 6: {
        Assert.assertEquals(0L, x.longValue());
        return;
      }
      case 7: {
        Assert.assertEquals(-1L, x.longValue());
        return;
      }
      case 8: {
        Assert.assertEquals(Long.MIN_VALUE + 1L, x.longValue());
        return;
      }
      case 9: {
        Assert.assertEquals(Long.MIN_VALUE, x.longValue());
        return;
      }
      default: {
        throw new AssertionError("Only ten steps allowed."); //$NON-NLS-1$
      }
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
        Assert.assertEquals(999999L, y.getLong(0, 0));
        return;
      }
      case 1: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(99999L, y.getLong(0, 0));
        Assert.assertEquals(888888L, y.getLong(0, 1));
        return;
      }
      case 2: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(100L, y.getLong(0, 0));
        Assert.assertEquals(99999L, y.getLong(0, 1));
        Assert.assertEquals(88888L, y.getLong(0, 2));
        return;
      }
      case 3: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(10L, y.getLong(0, 0));
        Assert.assertEquals(200L, y.getLong(0, 1));
        Assert.assertEquals(99999L, y.getLong(0, 2));
        Assert.assertEquals(88888L, y.getLong(0, 3));
        return;
      }
      case 4: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(20L, y.getLong(0, 0));
        Assert.assertEquals(300L, y.getLong(0, 1));
        Assert.assertEquals(9999L, y.getLong(0, 2));
        Assert.assertEquals(8888L, y.getLong(0, 3));
        return;
      }
      case 5: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(30L, y.getLong(0, 0));
        Assert.assertEquals(400L, y.getLong(0, 1));
        Assert.assertEquals(999L, y.getLong(0, 2));
        Assert.assertEquals(888L, y.getLong(0, 3));
        return;
      }
      case 6: {
        Assert.assertEquals(y.n(), 4);
        Assert.assertEquals(40L, y.getLong(0, 0));
        Assert.assertEquals(500L, y.getLong(0, 1));
        Assert.assertEquals(99L, y.getLong(0, 2));
        Assert.assertEquals(88L, y.getLong(0, 3));
        return;
      }
      case 7: {
        Assert.assertEquals(y.n(), 3);
        Assert.assertEquals(600L, y.getLong(0, 0));
        Assert.assertEquals(9L, y.getLong(0, 1));
        Assert.assertEquals(8L, y.getLong(0, 2));
        return;
      }
      case 8: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(9L, y.getLong(0, 0));
        Assert.assertEquals(8L, y.getLong(0, 1));
        return;
      }
      case 9: {
        Assert.assertEquals(y.n(), 1);
        Assert.assertEquals(9L, y.getLong(0, 0));
        return;
      }
      default: {
        throw new AssertionError("Only ten steps allowed."); //$NON-NLS-1$
      }
    }
  }
}
