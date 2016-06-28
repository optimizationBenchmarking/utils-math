package org.optimizationBenchmarking.utils.math.functions.analysis.differentiation;

import org.optimizationBenchmarking.utils.ICloneable;
import org.optimizationBenchmarking.utils.collections.iterators.InstanceIterator;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;

/**
 * The base class for derivatives of an unary function.
 */
abstract class BasicUnaryDerivative extends UnaryFunction
    implements ICloneable, Iterable<Object> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** @serial the function */
  final UnaryFunction m_function;

  /**
   * Create
   *
   * @param f
   *          the function
   */
  public BasicUnaryDerivative(final UnaryFunction f) {
    super();
    this.m_function = f;
  }

  /** {@inheritDoc} */
  @Override
  public abstract BasicUnaryDerivative clone();

  /** {@inheritDoc} */
  @Override
  public final InstanceIterator<Object> iterator() {
    return new InstanceIterator<Object>(this.m_function);
  }
}
