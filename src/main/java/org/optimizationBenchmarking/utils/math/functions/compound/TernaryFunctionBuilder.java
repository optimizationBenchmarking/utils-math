package org.optimizationBenchmarking.utils.math.functions.compound;

import org.optimizationBenchmarking.utils.math.functions.BinaryFunction;
import org.optimizationBenchmarking.utils.math.functions.QuaternaryFunction;
import org.optimizationBenchmarking.utils.math.functions.TernaryFunction;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;
import org.optimizationBenchmarking.utils.math.functions.power.Cbrt;
import org.optimizationBenchmarking.utils.math.functions.power.Cube;
import org.optimizationBenchmarking.utils.math.functions.power.Exp;
import org.optimizationBenchmarking.utils.math.functions.power.Pow;
import org.optimizationBenchmarking.utils.math.functions.power.Pow10;
import org.optimizationBenchmarking.utils.math.functions.power.Pow2;
import org.optimizationBenchmarking.utils.math.functions.power.Sqr;
import org.optimizationBenchmarking.utils.math.functions.power.Sqrt;

/**
 * This is the automatically generated code for a
 * {@link org.optimizationBenchmarking.utils.math.functions.compound
 * builder} of
 * {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
 * 3-ary} functions.
 */
public final class TernaryFunctionBuilder
    extends FunctionBuilder<TernaryFunction> {
  /**
   * The globally shared instance of the
   * {@link org.optimizationBenchmarking.utils.math.functions.compound
   * builder} of
   * {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
   * 3-ary} functions.
   */
  private static final TernaryFunctionBuilder INSTANCE = new TernaryFunctionBuilder();

  /**
   * Create the
   * {@link org.optimizationBenchmarking.utils.math.functions.compound
   * builder} of
   * {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
   * 3-ary} functions.
   */
  private TernaryFunctionBuilder() {
    super();
  }

  /**
   * Get an instance of the
   * {@link org.optimizationBenchmarking.utils.math.functions.compound
   * builder} of
   * {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
   * 3-ary} functions.
   *
   * @return an instance of the
   *         {@link org.optimizationBenchmarking.utils.math.functions.compound
   *         builder} of
   *         {@link org.optimizationBenchmarking.utils.math.functions.TernaryFunction
   *         3-ary} functions.
   */
  public static final TernaryFunctionBuilder getInstance() {
    return TernaryFunctionBuilder.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final TernaryFunction compound(final UnaryFunction func,
      final TernaryFunction param1) {
    return new _Compound1x3(func, param1);
  }

  /** {@inheritDoc} */
  @Override
  public final TernaryFunction compound(final BinaryFunction func,
      final TernaryFunction param1, final TernaryFunction param2) {
    double constant;
    if (func instanceof Pow) {
      if (param1 instanceof _Const3) {
        constant = ((_Const3) param1).m_const.doubleValue();
        if (constant == 2d) {
          return this.compound(Pow2.INSTANCE, param2);
        }
        if (constant == 10d) {
          return this.compound(Pow10.INSTANCE, param2);
        }
        if (constant == Math.E) {
          return this.compound(Exp.INSTANCE, param2);
        }
      }
      if (param2 instanceof _Const3) {
        constant = ((_Const3) param2).m_const.doubleValue();
        if (constant == 2d) {
          return this.compound(Sqr.INSTANCE, param1);
        }
        if (constant == 3d) {
          return this.compound(Cube.INSTANCE, param1);
        }
        if (constant == 0.5d) {
          return this.compound(Sqrt.INSTANCE, param1);
        }
        if (constant == (1d / 3d)) {
          return this.compound(Cbrt.INSTANCE, param1);
        }
      }
    }
    return new _Compound2x3(func, param1, param2);
  }

  /** {@inheritDoc} */
  @Override
  public final TernaryFunction compound(final TernaryFunction func,
      final TernaryFunction param1, final TernaryFunction param2,
      final TernaryFunction param3) {
    return new _Compound3x3(func, param1, param2, param3);
  }

  /** {@inheritDoc} */
  @Override
  public final TernaryFunction compound(final QuaternaryFunction func,
      final TernaryFunction param1, final TernaryFunction param2,
      final TernaryFunction param3, final TernaryFunction param4) {
    return new _Compound4x3(func, param1, param2, param3, param4);
  }

  /** {@inheritDoc} */
  @Override
  public final TernaryFunction constant(final Number value) {
    return new _Const3(value);
  }

  /** {@inheritDoc} */
  @Override
  public final TernaryFunction parameter(final int index) {
    switch (index) {
      case 0: {
        return _Select1of3.INSTANCE;
      }
      case 1: {
        return _Select2of3.INSTANCE;
      }
      case 2: {
        return _Select3of3.INSTANCE;
      }
      default: {
        throw new IllegalArgumentException( //
            "The parameter index must be in 0..2, but " //$NON-NLS-1$
                + index + " was specified."); //$NON-NLS-1$
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int getFunctionArity() {
    return 3;
  }

  /** {@inheritDoc} */
  @Override
  public final Class<TernaryFunction> getFunctionClass() {
    return TernaryFunction.class;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return (o instanceof TernaryFunctionBuilder);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return 40343;
  }
}
