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

/** A test for the matrix iteration */
public class MatrixIteration2DTestDoubleDoubleDecreasingPreviewNextSetToValueSkip1
    extends MatrixIteration2DTest {

  /** create */
  public MatrixIteration2DTestDoubleDoubleDecreasingPreviewNextSetToValueSkip1() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected void setup(final MatrixIteration2DBuilder builder) {
    builder.setEndMode(EMissingValueMode.SKIP);
    builder.setStartReplacement(Double.valueOf(-31.5d));
    builder.setIterationMode(EIterationMode.PREVIEW_NEXT);
    builder.setXDirection(EIterationDirection.DECREASING);
    builder.setXDimension(0);
    builder.setYDimension(1);

    builder.setMatrices(//
        new DoubleMatrix1D(new double[] { //
            Double.POSITIVE_INFINITY, 10.5d, //
            1000.5d, 20.5d, //
            200.5d, 30.5d, //
            30.5d, 40.5d,//
    }, 4, 2), //
        new DoubleMatrix1D(new double[] { //
            1000.5d, 100.5d, //
            200.5d, 200.5d, //
            30.5d, 300.5d, //
            Double.NEGATIVE_INFINITY, 400.5d,//
    }, 4, 2)

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
      case 3: {
        Assert.assertEquals(2, state.getSourceMatrixCount());
        Assert.assertEquals(0, state.getSourceMatrixIndex(0));
        Assert.assertEquals(1, state.getSourceMatrixIndex(1));
        return;
      }
      case 4: {
        Assert.assertEquals(1, state.getSourceMatrixCount());
        Assert.assertEquals(1, state.getSourceMatrixIndex(0));
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

    Assert.assertFalse(x.isInteger());

    switch (step) {
      case 0: {
        Assert.assertEquals(Double.POSITIVE_INFINITY, x.doubleValue(),
            Double.MIN_VALUE);
        return;
      }
      case 1: {
        Assert.assertEquals(1000.5d, x.doubleValue(), Double.MIN_VALUE);
        return;
      }
      case 2: {
        Assert.assertEquals(200.5d, x.doubleValue(), Double.MIN_VALUE);
        return;
      }
      case 3: {
        Assert.assertEquals(30.5d, x.doubleValue(), Double.MIN_VALUE);
        return;
      }
      case 4: {
        Assert.assertEquals(Double.NEGATIVE_INFINITY, x.doubleValue(),
            Double.MIN_VALUE);
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

    Assert.assertFalse(y.isIntegerMatrix());

    switch (step) {
      case 0: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(10.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(-31.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        return;
      }
      case 1: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(20.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(100.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        return;
      }
      case 2: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(30.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(200.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        return;
      }
      case 3: {
        Assert.assertEquals(y.n(), 2);
        Assert.assertEquals(40.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        Assert.assertEquals(300.5d, y.getDouble(0, 1), Double.MIN_VALUE);
        return;
      }
      case 4: {
        Assert.assertEquals(y.n(), 1);
        Assert.assertEquals(400.5d, y.getDouble(0, 0), Double.MIN_VALUE);
        return;
      }
      default: {
        throw new AssertionError("Only five steps allowed."); //$NON-NLS-1$
      }
    }
  }
}
