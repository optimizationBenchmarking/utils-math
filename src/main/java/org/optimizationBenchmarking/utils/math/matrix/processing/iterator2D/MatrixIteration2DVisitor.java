package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.collections.visitors.IVisitor;

/**
 * A base class for visitors which build stuff.
 *
 * @param <R>
 *          the result type
 */
public class MatrixIteration2DVisitor<R>
    implements IVisitor<MatrixIteration2DState> {

  /** create */
  protected MatrixIteration2DVisitor() {
    super();
  }

  /**
   * This method is called before the iteration process starts.
   *
   * @param spec
   *          the specification of the iteration process
   */
  protected void initialize(final MatrixIteration2DSpec spec) {
    //
  }

  /**
   * Create and build the result of the iteration process.
   *
   * @return the result
   */
  protected R build() {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public boolean visit(final MatrixIteration2DState object) {
    return false;
  }
}
