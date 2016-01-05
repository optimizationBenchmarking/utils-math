package org.optimizationBenchmarking.utils.math.functions.power;

import org.optimizationBenchmarking.utils.document.spec.IMath;
import org.optimizationBenchmarking.utils.document.spec.IParameterRenderer;
import org.optimizationBenchmarking.utils.document.spec.IText;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;

/**
 * The 2^x function
 */
public final class Pow2 extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the precedence priority of the base-2 power operator */
  public static final int PRECEDENCE_PRIORITY = Pow.PRECEDENCE_PRIORITY;

  /** the globally shared instance */
  public static final Pow2 INSTANCE = new Pow2();

  /** instantiate */
  private Pow2() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int getPrecedencePriority() {
    return Pow2.PRECEDENCE_PRIORITY;
  }

  /** {@inheritDoc} */
  @Override
  public final int computeAsInt(final int x1) {
    if (x1 < 0) {
      return 0;
    }
    if (x1 < 31) {
      return (1 << x1);
    }
    return Integer.MAX_VALUE;
  }

  /** {@inheritDoc} */
  @Override
  public final long computeAsLong(final long x1) {
    if (x1 < 0) {
      return 0;
    }
    if (x1 < 63L) {
      return (1 << x1);
    }
    return Long.MAX_VALUE;
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x1) {
    final int i1;

    if ((x1 > (-63)) && (x1 < 63)) {
      i1 = ((int) x1);
      if (i1 == x1) {
        if (i1 >= 0) {
          return (1L << i1);
        }
        return (1d / (1L << (-i1)));
      }
    }

    return Pow.INSTANCE.computeAsDouble(2d, x1);
  }

  /** {@inheritDoc} */
  @Override
  public final Ld invertFor(final int index) {
    return Ld.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final ITextOutput out,
      final IParameterRenderer renderer) {
    out.append('2');
    out.append('^');
    out.append('(');
    renderer.renderParameter(0, out);
    out.append(')');
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final IMath out,
      final IParameterRenderer renderer) {
    try (final IMath pow = out.pow()) {
      try (final IText num = pow.number()) {
        num.append(2);
      }
      renderer.renderParameter(0, pow);
    }
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link #INSTANCE} for serialization, i.e.,
   * when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link #INSTANCE})
   */
  private final Object writeReplace() {
    return Pow2.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link #INSTANCE} after serialization,
   * i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link #INSTANCE})
   */
  private final Object readResolve() {
    return Pow2.INSTANCE;
  }
}
