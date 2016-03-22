package test.junit.org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.optimizationBenchmarking.utils.collections.visitors.IVisitor;
import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DBuilder;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DState;

import shared.junit.TestBase;

/** The basic test for the matrix iteration 2D */
@Ignore
public abstract class MatrixIteration2DTest extends TestBase {

  /** create */
  protected MatrixIteration2DTest() {
    super();
  }

  /**
   * setup the iteration builder
   *
   * @param builder
   *          the builder
   */
  protected abstract void setup(MatrixIteration2DBuilder builder);

  /** test whether the iteration proceeds as expected */
  @Test(timeout = 3600000)
  public void testIteration() {
    final MatrixIteration2DBuilder builder;
    final __Visitor visitor;

    visitor = new __Visitor();
    builder = new MatrixIteration2DBuilder();
    this.setup(builder);
    builder.setVisitor(visitor).create().run();
    this.checkTotalSteps(visitor.m_step);
  }

  /**
   * Check the state at the given index
   *
   * @param step
   *          the step
   * @param state
   *          the state
   */
  protected void checkState(final int step,
      final MatrixIteration2DState state) {
    Assert.assertNotNull(state);
    Assert.assertNotNull(state.getStartMode());
    Assert.assertNotNull(state.getEndMode());
    Assert.assertNotNull(state.getMode());
    Assert.assertTrue(state.getSourceMatrixCount() > 0);
    this.checkX(step, state.getX());
    this.checkY(step, state.getY());
  }

  /**
   * check the total number of steps
   *
   * @param totalSteps
   *          the total number of steps
   */
  protected void checkTotalSteps(final int totalSteps) {
    Assert.assertTrue(totalSteps >= 0);
  }

  /**
   * Check the {@code x} coordinate
   *
   * @param step
   *          the step index
   * @param x
   *          the {@code x} coordinate
   */
  protected void checkX(final int step, final BasicNumber x) {
    Assert.assertTrue(step >= 0);
    Assert.assertNotNull(x);
  }

  /**
   * Check the {@code y} coordinates
   *
   * @param step
   *          the step index
   * @param y
   *          the {@code y} coordinates
   */
  protected void checkY(final int step, final AbstractMatrix y) {
    Assert.assertTrue(step >= 0);
    Assert.assertNotNull(y);
    Assert.assertEquals(1, y.m());
    Assert.assertTrue(y.n() >= 1);
  }

  /** the visitor */
  private final class __Visitor
      implements IVisitor<MatrixIteration2DState> {

    /** the step */
    int m_step;

    /** create */
    __Visitor() {
      super();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean visit(final MatrixIteration2DState state) {
      MatrixIteration2DTest.this.checkState((this.m_step++), state);
      return true;
    }
  }
}
