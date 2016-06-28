package org.optimizationBenchmarking.utils.math.functions.compound;

import org.optimizationBenchmarking.utils.ICloneable;
import org.optimizationBenchmarking.utils.collections.iterators.BasicIterator;
import org.optimizationBenchmarking.utils.document.spec.IMath;
import org.optimizationBenchmarking.utils.document.spec.IParameterRenderer;
import org.optimizationBenchmarking.utils.hash.HashUtils;
import org.optimizationBenchmarking.utils.math.functions.BinaryFunction;
import org.optimizationBenchmarking.utils.math.functions.TernaryFunction;
import org.optimizationBenchmarking.utils.math.text.AbstractParameterRenderer;
import org.optimizationBenchmarking.utils.math.text.DefaultParameterRenderer;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;
import org.optimizationBenchmarking.utils.text.textOutput.MemoryTextOutput;

/**
 * This is the automatically generated code for a
 * {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction
 * 2-ary} which is composed of 3 single functions joined with a
 * {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
 * 3-ary} function.
 */
final class _Compound3x2 extends BinaryFunction
    implements ICloneable, Iterable<Object> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * @serial The
   *         {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
   *         3-ary} function used to compute this function's result based
   *         on the results of the 3 child
   *         {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction
   *         3-ary} functions.
   */
  final TernaryFunction m_result;

  /**
   * @serial The first child
   *         {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction
   *         2-ary} function which contributes to the result.
   */
  final BinaryFunction m_child1;

  /**
   * @serial The second child
   *         {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction
   *         2-ary} function which contributes to the result.
   */
  final BinaryFunction m_child2;

  /**
   * @serial The third child
   *         {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction
   *         2-ary} function which contributes to the result.
   */
  final BinaryFunction m_child3;

  /**
   * Create the
   * {@link org.optimizationBenchmarking.utils.math.functions.compound._Compound3x2}
   * , a function which combines the result of 3 child
   * {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction
   * 2-ary} functions by using an
   * {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
   * 3-ary} function.
   *
   * @param result
   *          The
   *          {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
   *          3-ary} function used to compute this function's result based
   *          on the results of the 3 child
   *          {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction
   *          3-ary} functions.
   * @param child1
   *          The first child
   *          {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction
   *          2-ary} function which contributes to the result.
   * @param child2
   *          The second child
   *          {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction
   *          2-ary} function which contributes to the result.
   * @param child3
   *          The third child
   *          {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction
   *          2-ary} function which contributes to the result.
   * @throws IllegalArgumentException
   *           if any of the parameters is {@code null}
   */
  _Compound3x2(final TernaryFunction result, final BinaryFunction child1,
      final BinaryFunction child2, final BinaryFunction child3) {
    super();
    if (result == null) {
      throw new IllegalArgumentException( //
          "Result function of {@link org.optimizationBenchmarking.utils.math.functions.compound._Compound3x2}, a function which combines the result of 3 child {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction 2-ary} functions by using an {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction 3-ary} function, cannot be null."); //$NON-NLS-1$
    }
    this.m_result = result;
    if (child1 == null) {
      throw new IllegalArgumentException( //
          "Child function 1 of {@link org.optimizationBenchmarking.utils.math.functions.compound._Compound3x2}, a function which combines the result of 3 child {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction 2-ary} functions by using an {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction 3-ary} function, cannot be null."); //$NON-NLS-1$
    }
    this.m_child1 = child1;
    if (child2 == null) {
      throw new IllegalArgumentException( //
          "Child function 2 of {@link org.optimizationBenchmarking.utils.math.functions.compound._Compound3x2}, a function which combines the result of 3 child {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction 2-ary} functions by using an {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction 3-ary} function, cannot be null."); //$NON-NLS-1$
    }
    this.m_child2 = child2;
    if (child3 == null) {
      throw new IllegalArgumentException( //
          "Child function 3 of {@link org.optimizationBenchmarking.utils.math.functions.compound._Compound3x2}, a function which combines the result of 3 child {@link org.optimizationBenchmarking.utils.math.functions.BinaryFunction 2-ary} functions by using an {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction 3-ary} function, cannot be null."); //$NON-NLS-1$
    }
    this.m_child3 = child3;
  }

  /** {@inheritDoc} */
  @Override
  public final byte computeAsByte(final byte x0, final byte x1) {
    return this.m_result.computeAsByte( //
        this.m_child1.computeAsByte(x0, x1), //
        this.m_child2.computeAsByte(x0, x1), //
        this.m_child3.computeAsByte(x0, x1));
  }

  /** {@inheritDoc} */
  @Override
  public final short computeAsShort(final short x0, final short x1) {
    return this.m_result.computeAsShort( //
        this.m_child1.computeAsShort(x0, x1), //
        this.m_child2.computeAsShort(x0, x1), //
        this.m_child3.computeAsShort(x0, x1));
  }

  /** {@inheritDoc} */
  @Override
  public final int computeAsInt(final int x0, final int x1) {
    return this.m_result.computeAsInt( //
        this.m_child1.computeAsInt(x0, x1), //
        this.m_child2.computeAsInt(x0, x1), //
        this.m_child3.computeAsInt(x0, x1));
  }

  /** {@inheritDoc} */
  @Override
  public final long computeAsLong(final long x0, final long x1) {
    return this.m_result.computeAsLong( //
        this.m_child1.computeAsLong(x0, x1), //
        this.m_child2.computeAsLong(x0, x1), //
        this.m_child3.computeAsLong(x0, x1));
  }

  /** {@inheritDoc} */
  @Override
  public final float computeAsFloat(final float x0, final float x1) {
    return this.m_result.computeAsFloat( //
        this.m_child1.computeAsFloat(x0, x1), //
        this.m_child2.computeAsFloat(x0, x1), //
        this.m_child3.computeAsFloat(x0, x1));
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x0, final double x1) {
    return this.m_result.computeAsDouble( //
        this.m_child1.computeAsDouble(x0, x1), //
        this.m_child2.computeAsDouble(x0, x1), //
        this.m_child3.computeAsDouble(x0, x1));
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final int x0, final int x1) {
    return this.m_result.computeAsDouble( //
        this.m_child1.computeAsDouble(x0, x1), //
        this.m_child2.computeAsDouble(x0, x1), //
        this.m_child3.computeAsDouble(x0, x1));
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final long x0, final long x1) {
    return this.m_result.computeAsDouble( //
        this.m_child1.computeAsDouble(x0, x1), //
        this.m_child2.computeAsDouble(x0, x1), //
        this.m_child3.computeAsDouble(x0, x1));
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
        new __Compound3x2ParameterRenderer(renderer));
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final ITextOutput out,
      final IParameterRenderer renderer) {
    this.m_result.mathRender(out,
        new __Compound3x2ParameterRenderer(renderer));
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
    final _Compound3x2 other;
    if (o == this) {
      return true;
    }
    if (o instanceof _Compound3x2) {
      other = ((_Compound3x2) o);
      return (this.m_result.equals(other.m_result) //
          && this.m_child1.equals(other.m_child1) //
          && this.m_child2.equals(other.m_child2) //
          && this.m_child3.equals(other.m_child3));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final _Compound3x2 clone() {
    final TernaryFunction result = ((this.m_result instanceof ICloneable)
        ? (((TernaryFunction) (((ICloneable) (this.m_result)).clone())))
        : this.m_result);
    final BinaryFunction child1 = ((this.m_child1 instanceof ICloneable)
        ? (((BinaryFunction) (((ICloneable) (this.m_child1)).clone())))
        : this.m_child1);
    final BinaryFunction child2 = ((this.m_child2 instanceof ICloneable)
        ? (((BinaryFunction) (((ICloneable) (this.m_child2)).clone())))
        : this.m_child2);
    final BinaryFunction child3 = ((this.m_child3 instanceof ICloneable)
        ? (((BinaryFunction) (((ICloneable) (this.m_child3)).clone())))
        : this.m_child3);
    if ((result != this.m_result) || (child1 != this.m_child1)
        || (child2 != this.m_child2) || (child3 != this.m_child3)) {
      return new _Compound3x2(result, child1, child2, child3);
    }
    return this;
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
  public final __Compound3x2Iterator iterator() {
    return new __Compound3x2Iterator();
  }

  /**
   * This is the automatically generated code of the
   * {@link org.optimizationBenchmarking.utils.document.spec.IParameterRenderer
   * parameter renderer} of the {@link _Compound3x2}.
   */
  private final class __Compound3x2ParameterRenderer
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
     * parameter renderer} of the {@link _Compound3x2}
     *
     * @param renderer
     *          the
     *          {@link org.optimizationBenchmarking.utils.document.spec.IParameterRenderer
     *          parameter renderer} to bridge to
     * @throws IllegalArgumentException
     *           if {@code renderer} is {@code null}
     */
    __Compound3x2ParameterRenderer(final IParameterRenderer renderer) {
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
          _Compound3x2.this.m_child1.mathRender(out, this.m_renderer);
          return;
        }
        case 1: {
          _Compound3x2.this.m_child2.mathRender(out, this.m_renderer);
          return;
        }
        case 2: {
          _Compound3x2.this.m_child3.mathRender(out, this.m_renderer);
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
          _Compound3x2.this.m_child1.mathRender(out, this.m_renderer);
          return;
        }
        case 1: {
          _Compound3x2.this.m_child2.mathRender(out, this.m_renderer);
          return;
        }
        case 2: {
          _Compound3x2.this.m_child3.mathRender(out, this.m_renderer);
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
          _Compound3x2.this.hashCode());
    }

    /**
     * the internal owner getter
     *
     * @return the owning {@link _Compound3x2} instance
     */
    private final _Compound3x2 __getOwner() {
      return _Compound3x2.this;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean equals(final Object o) {
      final __Compound3x2ParameterRenderer other;
      if (o == this) {
        return true;
      }
      if (o instanceof __Compound3x2ParameterRenderer) {
        other = ((__Compound3x2ParameterRenderer) o);
        return ((this.m_renderer.equals(other.m_renderer))
            && (_Compound3x2.this.equals(other.__getOwner())));
      }
      return false;
    }
  }

  /** the internal iterator implementation */
  private final class __Compound3x2Iterator extends BasicIterator<Object> {
    /** the index */
    private int m_index;

    /** create */
    __Compound3x2Iterator() {
      super();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean hasNext() {
      return (this.m_index <= 3);
    }

    /** {@inheritDoc} */
    @Override
    public final Object next() {
      switch (this.m_index++) {
        case 0: {
          return _Compound3x2.this.m_result;
        }
        case 1: {
          return _Compound3x2.this.m_child1;
        }
        case 2: {
          return _Compound3x2.this.m_child2;
        }
        case 3: {
          return _Compound3x2.this.m_child3;
        }
        default: {
          return super.next();
        }
      }
    }
  }
}
