package org.optimizationBenchmarking.utils.math.statistics.aggregate;

import org.optimizationBenchmarking.utils.math.ModifiableBasicNumber;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;
import org.optimizationBenchmarking.utils.math.functions.basic.Identity;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.MatrixFunctionBuilder;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DSpec;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DState;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DVisitor;

/**
 * A visitor which performs a two-dimensional matrix aggregate to build a
 * stair-style function. Basically, for each of the
 * {@link org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DState#getX()
 * x} coordinates, all the
 * {@link org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.MatrixIteration2DState#getY()
 * y} coordinates are aggregated with a given {@link ScalarAggregate
 * statistical aggregate}.
 */
public final class Matrix2DAggregate
    extends MatrixIteration2DVisitor<AbstractMatrix> {

  /** the unary transformation function */
  private UnaryFunction m_transform;
  /** the matrix builder */
  private MatrixFunctionBuilder m_builder;
  /** the aggregate to use */
  private ScalarAggregate m_aggregate;
  /** the current transformed y state */
  private ModifiableBasicNumber m_currentYTransformed;

  /**
   * create
   *
   * @param aggregate
   *          the aggregate
   * @param transform
   *          the transformation
   */
  public Matrix2DAggregate(final ScalarAggregate aggregate,
      final UnaryFunction transform) {
    super();
    if (aggregate == null) {
      throw new IllegalArgumentException(
          "Scalar aggregate cannot be null."); //$NON-NLS-1$
    }
    this.m_aggregate = aggregate;
    this.m_transform = ((transform == null) ? Identity.INSTANCE
        : transform);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean visit(final MatrixIteration2DState object) {
    this.m_aggregate.reset();
    object.getY().aggregateRow(0, this.m_aggregate);
    this.m_currentYTransformed.assignTransformed(this.m_aggregate,
        this.m_transform);
    this.m_builder.addPoint(object.getX(), this.m_currentYTransformed);
    return true;
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final MatrixIteration2DSpec spec) {
    super.initialize(spec);
    this.m_builder = new MatrixFunctionBuilder(true);
    this.m_currentYTransformed = new ModifiableBasicNumber();
  }

  /** {@inheritDoc} */
  @Override
  protected final AbstractMatrix build() {
    final AbstractMatrix res;

    this.m_aggregate = null;
    this.m_currentYTransformed = null;
    this.m_transform = null;
    res = this.m_builder.build();
    this.m_builder = null;

    return res;
  }
}
