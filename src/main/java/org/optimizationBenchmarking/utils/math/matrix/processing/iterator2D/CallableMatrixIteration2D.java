package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.tools.spec.ICallableToolJob;

/**
 * the callable iteration 2D
 *
 * @param <R>
 *          the result type
 */
public final class CallableMatrixIteration2D<R>
    implements ICallableToolJob<R> {

  /** the internal iteration */
  private _MatrixIteration2D m_iteration;

  /**
   * create
   *
   * @param builder
   *          the builder
   */
  CallableMatrixIteration2D(
      final CallableMatrixIteration2DBuilder<R> builder) {
    super();
    this.m_iteration = new _MatrixIteration2D(builder);
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unchecked")
  @Override
  public final R call() {
    final MatrixIteration2DVisitor<R> visitor;

    visitor = ((MatrixIteration2DVisitor<R>) (this.m_iteration.m_visitor));
    visitor.initialize(this.m_iteration);
    this.m_iteration.run();
    this.m_iteration = null;
    return visitor.build();
  }
}
