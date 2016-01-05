package org.optimizationBenchmarking.utils.math.functions.compound;

import org.optimizationBenchmarking.utils.document.spec.IMath;
import org.optimizationBenchmarking.utils.document.spec.IParameterRenderer;
import org.optimizationBenchmarking.utils.hash.HashUtils;
import org.optimizationBenchmarking.utils.math.functions.TernaryFunction;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;
import org.optimizationBenchmarking.utils.math.text.AbstractParameterRenderer;
import org.optimizationBenchmarking.utils.math.text.DefaultParameterRenderer;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;
import org.optimizationBenchmarking.utils.text.textOutput.MemoryTextOutput;

/**
 * This is the automatically generated code for a
 * {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
 * 1-ary} which is composed of 3 single functions joined with a
 * {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
 * 3-ary} function.
 */
final class _Compound3x1 extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * @serial The
   *         {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
   *         3-ary} function used to compute this function's result based
   *         on the results of the 3 child
   *         {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
   *         3-ary} functions.
   */
  final TernaryFunction m_result;

  /**
   * @serial The first child
   *         {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
   *         1-ary} function which contributes to the result.
   */
  final UnaryFunction m_child1;

  /**
   * @serial The second child
   *         {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
   *         1-ary} function which contributes to the result.
   */
  final UnaryFunction m_child2;

  /**
   * @serial The third child
   *         {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
   *         1-ary} function which contributes to the result.
   */
  final UnaryFunction m_child3;

  /**
   * Create the
   * {@link org.optimizationBenchmarking.utils.math.functions.compound._Compound3x1}
   * , a function which combines the result of 3 child
   * {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
   * 1-ary} functions by using an
   * {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
   * 3-ary} function.
   *
   * @param result
   *          The
   *          {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
   *          3-ary} function used to compute this function's result based
   *          on the results of the 3 child
   *          {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
   *          3-ary} functions.
   * @param child1
   *          The first child
   *          {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
   *          1-ary} function which contributes to the result.
   * @param child2
   *          The second child
   *          {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
   *          1-ary} function which contributes to the result.
   * @param child3
   *          The third child
   *          {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction
   *          1-ary} function which contributes to the result.
   * @throws IllegalArgumentException
   *           if any of the parameters is {@code null}
   */
  _Compound3x1(final TernaryFunction result, final UnaryFunction child1,
      final UnaryFunction child2, final UnaryFunction child3) {
    super();
    if (result == null) {
      throw new IllegalArgumentException( //
          "Result function of {@link org.optimizationBenchmarking.utils.math.functions.compound._Compound3x1}, a function which combines the result of 3 child {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction 1-ary} functions by using an {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction 3-ary} function, cannot be null."); //$NON-NLS-1$
    }
    this.m_result = result;
    if (child1 == null) {
      throw new IllegalArgumentException( //
          "Child function 1 of {@link org.optimizationBenchmarking.utils.math.functions.compound._Compound3x1}, a function which combines the result of 3 child {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction 1-ary} functions by using an {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction 3-ary} function, cannot be null."); //$NON-NLS-1$
    }
    this.m_child1 = child1;
    if (child2 == null) {
      throw new IllegalArgumentException( //
          "Child function 2 of {@link org.optimizationBenchmarking.utils.math.functions.compound._Compound3x1}, a function which combines the result of 3 child {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction 1-ary} functions by using an {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction 3-ary} function, cannot be null."); //$NON-NLS-1$
    }
    this.m_child2 = child2;
    if (child3 == null) {
      throw new IllegalArgumentException( //
          "Child function 3 of {@link org.optimizationBenchmarking.utils.math.functions.compound._Compound3x1}, a function which combines the result of 3 child {@link org.optimizationBenchmarking.utils.math.functions.UnaryFunction 1-ary} functions by using an {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction 3-ary} function, cannot be null."); //$NON-NLS-1$
    }
    this.m_child3 = child3;
  }

  /** {@inheritDoc} */
  @Override
  public final byte computeAsByte(final byte x0) {
    return this.m_result.computeAsByte( //
        this.m_child1.computeAsByte(x0), //
        this.m_child2.computeAsByte(x0), //
        this.m_child3.computeAsByte(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final short computeAsShort(final short x0) {
    return this.m_result.computeAsShort( //
        this.m_child1.computeAsShort(x0), //
        this.m_child2.computeAsShort(x0), //
        this.m_child3.computeAsShort(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final int computeAsInt(final int x0) {
    return this.m_result.computeAsInt( //
        this.m_child1.computeAsInt(x0), //
        this.m_child2.computeAsInt(x0), //
        this.m_child3.computeAsInt(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final long computeAsLong(final long x0) {
    return this.m_result.computeAsLong( //
        this.m_child1.computeAsLong(x0), //
        this.m_child2.computeAsLong(x0), //
        this.m_child3.computeAsLong(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final float computeAsFloat(final float x0) {
    return this.m_result.computeAsFloat( //
        this.m_child1.computeAsFloat(x0), //
        this.m_child2.computeAsFloat(x0), //
        this.m_child3.computeAsFloat(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x0) {
    return this.m_result.computeAsDouble( //
        this.m_child1.computeAsDouble(x0), //
        this.m_child2.computeAsDouble(x0), //
        this.m_child3.computeAsDouble(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final int x0) {
    return this.m_result.computeAsDouble( //
        this.m_child1.computeAsDouble(x0), //
        this.m_child2.computeAsDouble(x0), //
        this.m_child3.computeAsDouble(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final long x0) {
    return this.m_result.computeAsDouble( //
        this.m_child1.computeAsDouble(x0), //
        this.m_child2.computeAsDouble(x0), //
        this.m_child3.computeAsDouble(x0));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isLongArithmeticAccurate() {
    return (this.m_result.isLongArithmeticAccurate() //
        && this.m_child1.isLongArithmeticAccurate() //
        && this.m_child2.isLongArithmeticAccurate() //
        && this.m_child3.isLongArithmeticAccurate());
  }

  /** {@inheritDoc} */
  @Override
  public final int getPrecedencePriority() {
    return this.m_result.getPrecedencePriority();
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final IMath out,
      final IParameterRenderer renderer) {
    this.m_result.mathRender(out,
        new __Compound3x1ParameterRenderer(renderer));
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final ITextOutput out,
      final IParameterRenderer renderer) {
    this.m_result.mathRender(out,
        new __Compound3x1ParameterRenderer(renderer));
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return HashUtils.combineHashes( //
        HashUtils.hashCode(this.m_result), //
        HashUtils.combineHashes( //
            HashUtils.hashCode(this.m_child1), //
            HashUtils.combineHashes( //
                HashUtils.hashCode(this.m_child2), //
                HashUtils.hashCode(this.m_child3))));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    final _Compound3x1 other;
    if (o == this) {
      return true;
    }
    if (o instanceof _Compound3x1) {
      other = ((_Compound3x1) o);
      return (this.m_result.equals(other.m_result) //
          && this.m_child1.equals(other.m_child1) //
          && this.m_child2.equals(other.m_child2) //
          && this.m_child3.equals(other.m_child3));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    final MemoryTextOutput output;
    output = new MemoryTextOutput();
    this.mathRender(output, DefaultParameterRenderer.INSTANCE);
    return output.toString();
  }

  /**
   * This is the automatically generated code of the
   * {@link org.optimizationBenchmarking.utils.document.spec.IParameterRenderer
   * parameter renderer} of the {@link _Compound3x1}.
   */
  private final class __Compound3x1ParameterRenderer
      extends AbstractParameterRenderer {
    /**
     * the
     * {@link org.optimizationBenchmarking.utils.document.spec.IParameterRenderer
     * parameter renderer} to bridge to
     */
    private final IParameterRenderer m_renderer;

    /**
     * Create the
     * {@link org.optimizationBenchmarking.utils.document.spec.IParameterRenderer
     * parameter renderer} of the {@link _Compound3x1}
     *
     * @param renderer
     *          the
     *          {@link org.optimizationBenchmarking.utils.document.spec.IParameterRenderer
     *          parameter renderer} to bridge to
     * @throws IllegalArgumentException
     *           if {@code renderer} is {@code null}
     */
    __Compound3x1ParameterRenderer(final IParameterRenderer renderer) {
      super();
      if (renderer == null) {
        throw new IllegalArgumentException( //
            "The parameter renderer to bridge to cannot be null."); //$NON-NLS-1$
      }
      this.m_renderer = renderer;
    }

    /** {@inheritDoc} */
    @Override
    public final void renderParameter(final int index, final IMath out) {
      switch (index) {
        case 0: {
          _Compound3x1.this.m_child1.mathRender(out, this.m_renderer);
          return;
        }
        case 1: {
          _Compound3x1.this.m_child2.mathRender(out, this.m_renderer);
          return;
        }
        case 2: {
          _Compound3x1.this.m_child3.mathRender(out, this.m_renderer);
          return;
        }
        default: {
          AbstractParameterRenderer.throwInvalidParameterIndex(index, 2);
        }
      }
    }

    /** {@inheritDoc} */
    @Override
    public final void renderParameter(final int index,
        final ITextOutput out) {
      switch (index) {
        case 0: {
          _Compound3x1.this.m_child1.mathRender(out, this.m_renderer);
          return;
        }
        case 1: {
          _Compound3x1.this.m_child2.mathRender(out, this.m_renderer);
          return;
        }
        case 2: {
          _Compound3x1.this.m_child3.mathRender(out, this.m_renderer);
          return;
        }
        default: {
          AbstractParameterRenderer.throwInvalidParameterIndex(index, 2);
        }
      }
    }

    /** {@inheritDoc} */
    @Override
    public final int hashCode() {
      return HashUtils.combineHashes(HashUtils.hashCode(this.m_renderer),
          _Compound3x1.this.hashCode());
    }

    /**
     * the internal owner getter
     *
     * @return the owning {@link _Compound3x1} instance
     */
    private final _Compound3x1 __getOwner() {
      return _Compound3x1.this;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean equals(final Object o) {
      final __Compound3x1ParameterRenderer other;
      if (o == this) {
        return true;
      }
      if (o instanceof __Compound3x1ParameterRenderer) {
        other = ((__Compound3x1ParameterRenderer) o);
        return ((this.m_renderer.equals(other.m_renderer))
            && (_Compound3x1.this.equals(other.__getOwner())));
      }
      return false;
    }
  }
}
