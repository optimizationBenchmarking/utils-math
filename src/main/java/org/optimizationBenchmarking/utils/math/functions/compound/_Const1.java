package org.optimizationBenchmarking.utils.math.functions.compound;

import org.optimizationBenchmarking.utils.ICloneable;
import org.optimizationBenchmarking.utils.collections.iterators.InstanceIterator;
import org.optimizationBenchmarking.utils.document.spec.IMath;
import org.optimizationBenchmarking.utils.document.spec.IMathRenderable;
import org.optimizationBenchmarking.utils.document.spec.IParameterRenderer;
import org.optimizationBenchmarking.utils.document.spec.IText;
import org.optimizationBenchmarking.utils.math.NumericalTypes;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;
import org.optimizationBenchmarking.utils.math.functions.arithmetic.Negate;
import org.optimizationBenchmarking.utils.math.text.DefaultParameterRenderer;
import org.optimizationBenchmarking.utils.math.text.NamedConstant;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;
import org.optimizationBenchmarking.utils.text.textOutput.MemoryTextOutput;

/**
 * This is the automatically generated code for a
 * {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
 * 1-ary} which returns a constant value.
 */
final class _Const1 extends UnaryFunction
    implements Iterable<Object>, ICloneable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * @serial the instance of {@link java.lang.Number} holding the constant
   *         value returned by this function
   */
  final Number m_const;

  /**
   * Create the
   * {@link org.optimizationBenchmarking.utils.math.functions.compound._Const1}
   * , a function which returns a constant value.
   * 
   * @param constant
   *          the instance of {@link java.lang.Number} holding the constant
   *          value returned by this function
   * @throws IllegalArgumentException
   *           if {@code constant} is {@code null}
   */
  _Const1(final Number constant) {
    super();
    if (constant == null) {
      throw new IllegalArgumentException( //
          "Constant result of {@link org.optimizationBenchmarking.utils.math.functions.compound._Const1}, a function which returns a constant value, cannot be null."); //$NON-NLS-1$
    }
    this.m_const = constant;
  }

  /** {@inheritDoc} */
  @Override
  public final byte computeAsByte(final byte x0) {
    return this.m_const.byteValue();
  }

  /** {@inheritDoc} */
  @Override
  public final short computeAsShort(final short x0) {
    return this.m_const.shortValue();
  }

  /** {@inheritDoc} */
  @Override
  public final int computeAsInt(final int x0) {
    return this.m_const.intValue();
  }

  /** {@inheritDoc} */
  @Override
  public final long computeAsLong(final long x0) {
    return this.m_const.longValue();
  }

  /** {@inheritDoc} */
  @Override
  public final float computeAsFloat(final float x0) {
    return this.m_const.floatValue();
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x0) {
    return this.m_const.doubleValue();
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final int x0) {
    return this.m_const.doubleValue();
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final long x0) {
    return this.m_const.doubleValue();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isLongArithmeticAccurate() {
    if (this.m_const instanceof NamedConstant) {
      return ((NamedConstant) (this.m_const)).isLongArithmeticAccurate();
    }
    return ((NumericalTypes.getTypes(this.m_const)
        & NumericalTypes.IS_LONG) != 0);
  }

  /** {@inheritDoc} */
  @Override
  public final int getPrecedencePriority() {
    if (this.m_const instanceof NamedConstant) {
      return Integer.MAX_VALUE;
    }
    return ((this.m_const.doubleValue() >= 0d) ? Integer.MAX_VALUE
        : Negate.PRECEDENCE_PRIORITY);
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final IMath out,
      final IParameterRenderer renderer) {
    if (this.m_const instanceof IMathRenderable) { // for named constants
      ((IMathRenderable) (this.m_const)).mathRender(out, renderer);
    } else { // normal constants
      try (final IText number = out.number()) {
        this.mathRender(number, renderer);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final ITextOutput out,
      final IParameterRenderer renderer) {
    if (this.m_const instanceof IMathRenderable) { // for named constants
      ((IMathRenderable) (this.m_const)).mathRender(out, renderer);
    } else { // normal constants
      if (this.isLongArithmeticAccurate()) {
        out.append(this.m_const.longValue());
      } else {
        out.append(this.m_const.doubleValue());
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final _Const1 clone() {
    if (this.m_const instanceof ICloneable) {
      final Number constant = ((Number) (((ICloneable) (this.m_const))
          .clone()));
      if (constant != this.m_const) {
        return new _Const1(constant);
      }
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return (65647 ^ this.m_const.hashCode());
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return ((o instanceof _Const1)
        && (this.m_const.equals(((_Const1) o).m_const)));
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    final MemoryTextOutput output;
    output = new MemoryTextOutput();
    this.mathRender(output, DefaultParameterRenderer.INSTANCE);
    return output.toString();
  }

  /** {@inheritDoc} */
  @Override
  public final InstanceIterator<Object> iterator() {
    return new InstanceIterator<Object>(this.m_const);
  }
}
