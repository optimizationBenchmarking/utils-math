package test.junit.org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.junit.Assert;
import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.LongMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationDirection;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EIterationMode;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.EMissingValueMode;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DBuilder;

/** The first test for the matrix iteration */
public class MatrixIteration2DTestLongLongIncreasingKeepPreviousSkipSkip1
    extends MatrixIteration2DTest {

  /** create */
  public MatrixIteration2DTestLongLongIncreasingKeepPreviousSkipSkip1() {
    super();
  }

  /** create */
  @Override

  protected void setup(final MatrixIteration2DBuilder builder) {
    builder.setEndMode(EMissingValueMode.SKIP);
    builder.setStartMode(EMissingValueMode.SKIP);
    builder.setIterationMode(EIterationMode.KEEP_PREVIOUS);
    builder.setXDirection(EIterationDirection.INCREASING);
    builder.setXDimension(0);
    builder.setYDimension(1);

    builder.setMatrices(//
        new LongMatrix1D(new long[] { //
            0, 10, //
            1, 20, //
            2, 30, //
            3, 40,//
    }, 4, 2));
  }

  /** {@inheritDoc} */
  @Override
  protected void checkTotalSteps(final int totalSteps) {
    super.checkTotalSteps(totalSteps);
    Assert.assertEquals(4, totalSteps);
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
      default: {
        throw new AssertionError("Only four steps allowed."); //$NON-NLS-1$
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void checkY(final int step, final AbstractMatrix y) {
    super.checkY(step, y);

    Assert.assertTrue(y.isIntegerMatrix());
    Assert.assertEquals(y.n(), 1);

    switch (step) {
      case 0: {
        Assert.assertEquals(10L, y.getLong(0, 0));
        return;
      }
      case 1: {
        Assert.assertEquals(20L, y.getLong(0, 0));
        return;
      }
      case 2: {
        Assert.assertEquals(30L, y.getLong(0, 0));
        return;
      }
      case 3: {
        Assert.assertEquals(40L, y.getLong(0, 0));
        return;
      }
      default: {
        throw new AssertionError("Only four steps allowed."); //$NON-NLS-1$
      }
    }
  }
}
