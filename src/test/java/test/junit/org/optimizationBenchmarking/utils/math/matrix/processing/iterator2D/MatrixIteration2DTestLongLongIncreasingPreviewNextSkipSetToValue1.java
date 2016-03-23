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
public class MatrixIteration2DTestLongLongIncreasingPreviewNextSkipSetToValue1
    extends MatrixIteration2DTest {

  /** create */
  public MatrixIteration2DTestLongLongIncreasingPreviewNextSkipSetToValue1() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected void setup(final MatrixIteration2DBuilder builder) {
    builder.setEndReplacement(Long.valueOf(5555L));
    builder.setStartMode(EMissingValueMode.SKIP);
    builder.setIterationMode(EIterationMode.PREVIEW_NEXT);
    builder.setXDirection(EIterationDirection.INCREASING);
    builder.setXDimension(0);
    builder.setYDimension(1);

    builder.setMatrices(//
        new LongMatrix1D(new long[] { //
            0L, 10L, //
            1L, 20L, //
            2L, 30L, //
            3L, 40L,//
    }, 4, 2), //
        new LongMatrix1D(new long[] { //
            0L, 100L, //
            1L, 200L, //
            2L, 300L, //
            3L, 400L, //
            4L, 500L,//
    }, 5, 2)

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
      case 4: {
        Assert.assertEquals(2, state.getSourceMatrixCount());
        Assert.assertEquals(0, state.getSourceMatrixIndex(0));
        Assert.assertEquals(1, state.getSourceMatrixIndex(1));
        return;
      }
      default: {
        throw new AssertionError("Only five steps allowed."); //$NON-NLS-1$
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void checkTotalSteps(final int totalSteps) {
    super.checkTotalSteps(totalSteps);
    Assert.assertEquals(5, totalSteps);
  }

  /** {@inheritDoc} */
  @Override
  protected void checkX(final int step, final BasicNumber x) {

    super.checkX(step, x);

    Assert.assertTrue(x.isInteger());

    switch (step) {
      case 0: {
        Assert.assertEquals(0L, x.longValue());
        return;
      }
      case 1: {
        Assert.assertEquals(1L, x.longValue());
        return;
      }
      case 2: {
        Assert.assertEquals(2L, x.longValue());
        return;
      }
      case 3: {
        Assert.assertEquals(3L, x.longValue());
        return;
      }
      case 4: {
        Assert.assertEquals(4L, x.longValue());
        return;
      }
      default: {
        throw new AssertionError("Only five steps allowed."); //$NON-NLS-1$
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
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(10L, y.getLong(0, 0));
        Assert.assertEquals(100L, y.getLong(0, 1));
        return;
      }
      case 1: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(20L, y.getLong(0, 0));
        Assert.assertEquals(200L, y.getLong(0, 1));
        return;
      }
      case 2: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(30L, y.getLong(0, 0));
        Assert.assertEquals(300L, y.getLong(0, 1));
        return;
      }
      case 3: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(40L, y.getLong(0, 0));
        Assert.assertEquals(400L, y.getLong(0, 1));
        return;
      }
      case 4: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(5555L, y.getLong(0, 0));
        Assert.assertEquals(500L, y.getLong(0, 1));
        return;
      }
      default: {
        throw new AssertionError("Only five steps allowed."); //$NON-NLS-1$
      }
    }
  }
}
