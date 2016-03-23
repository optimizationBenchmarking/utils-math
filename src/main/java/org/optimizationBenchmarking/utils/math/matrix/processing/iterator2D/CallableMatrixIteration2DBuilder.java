package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.collections.visitors.IVisitor;

/**
 * The builder for the matrix iterations.
 *
 * @param <R>
 *          the result type
 */
public class CallableMatrixIteration2DBuilder<R> extends
    _MatrixIteration2DBuilderBase<CallableMatrixIteration2DBuilder<R>> {

  /** create */
  public CallableMatrixIteration2DBuilder() {
    super();
  }

  /**
   * Set the visitor to receive the iteration data
   *
   * @param visitor
   *          the visitor to receive the iteration data
   * @return this builder
   */
  public final CallableMatrixIteration2DBuilder<R> setVisitor(
      final IVisitor<MatrixIteration2DState> visitor) {
    MatrixIteration2DSpec._checkVisitor(visitor);
    this.m_visitor = visitor;
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final CallableMatrixIteration2D<R> create() {
    return new CallableMatrixIteration2D<>(this);
  }
}
