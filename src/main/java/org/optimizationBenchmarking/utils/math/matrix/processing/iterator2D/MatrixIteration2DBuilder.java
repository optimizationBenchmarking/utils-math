package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.collections.visitors.IVisitor;
import org.optimizationBenchmarking.utils.tools.spec.IRunnableToolJob;

/** The builder for the matrix iterations. */
public class MatrixIteration2DBuilder
    extends _MatrixIteration2DBuilderBase<MatrixIteration2DBuilder> {

  /** create */
  public MatrixIteration2DBuilder() {
    super();
  }

  /**
   * Set the visitor to receive the iteration data
   *
   * @param visitor
   *          the visitor to receive the iteration data
   * @return this builder
   */
  public final MatrixIteration2DBuilder setVisitor(
      final IVisitor<MatrixIteration2DState> visitor) {
    MatrixIteration2DSpec._checkVisitor(visitor);
    this.m_visitor = visitor;
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final IRunnableToolJob create() {
    return new _MatrixIteration2D(this);
  }
}
