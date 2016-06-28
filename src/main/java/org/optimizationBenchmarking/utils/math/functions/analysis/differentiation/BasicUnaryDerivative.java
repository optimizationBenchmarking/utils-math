package org.optimizationBenchmarking.utils.math.functions.analysis.differentiation;

import org.optimizationBenchmarking.utils.ICloneable;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;

/**
 * The base class for derivatives of an unary function.
 */
abstract class BasicUnaryDerivative extends UnaryFunction
    implements ICloneable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the function */
  final UnaryFunction m_f;

  /**
   * Create
   *
   * @param f
   *          the function
   */
  public BasicUnaryDerivative(final UnaryFunction f) {
    super();
    this.m_f = f;
  }

  /** {@inheritDoc} */
  @Override
  public abstract BasicUnaryDerivative clone();
}
