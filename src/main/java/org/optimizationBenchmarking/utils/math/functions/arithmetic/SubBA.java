package org.optimizationBenchmarking.utils.math.functions.arithmetic;

import org.optimizationBenchmarking.utils.document.spec.IMath;
import org.optimizationBenchmarking.utils.document.spec.IParameterRenderer;
import org.optimizationBenchmarking.utils.math.NumericalTypes;
import org.optimizationBenchmarking.utils.math.functions.BinaryFunction;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;

/**
 * The second possible {@code "-"} function
 */
public final class SubBA extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the precedence priority of the argument-swapped subtraction operator
   */
  public static final int PRECEDENCE_PRIORITY = Sub.PRECEDENCE_PRIORITY;

  /** the globally shared instance */
  public static final SubBA INSTANCE = new SubBA();

  /** instantiate */
  private SubBA() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final byte computeAsByte(final byte x0, final byte x1) {
    return ((byte) (x1 - x0));
  }

  /** {@inheritDoc} */
  @Override
  public final short computeAsShort(final short x0, final short x1) {
    return ((short) (x1 - x0));
  }

  /** {@inheritDoc} */
  @Override
  public final int computeAsInt(final int x0, final int x1) {
    return (x1 - x0);
  }

  /** {@inheritDoc} */
  @Override
  public final long computeAsLong(final long x0, final long x1) {
    return (x1 - x0);
  }

  /** {@inheritDoc} */
  @Override
  public final float computeAsFloat(final float x0, final float x1) {
    final long l0, l1;

    if (NumericalTypes.isLong(x0) && NumericalTypes.isLong(x1)) {
      l0 = ((long) x0);
      l1 = ((long) x1);
      if (SaturatingSub.getOverflowType(l1, l0) == 0) {
        return (l1 - l0);
      }
    }
    return (x1 - x0);
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x0, final double x1) {
    final long l0, l1;

    if (NumericalTypes.isLong(x0) && NumericalTypes.isLong(x1)) {
      l0 = ((long) x0);
      l1 = ((long) x1);
      if (SaturatingSub.getOverflowType(l1, l0) == 0) {
        return (l1 - l0);
      }
    }

    return (x1 - x0);
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final long x0, final long x1) {
    switch (SaturatingSub.getOverflowType(x1, x0)) {
      case -1:
      case 1: {
        return (((double) x1) - ((double) x0));
      }
      default: {
        return (x1 - x0);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final int x0, final int x1) {
    return (((long) x1) - ((long) x0));
  }

  /** {@inheritDoc} */
  @Override
  public final BinaryFunction invertFor(final int index) {
    if (index == 1) {
      return Add.INSTANCE;
    }
    return SubBA.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final int getPrecedencePriority() {
    return SubBA.PRECEDENCE_PRIORITY;
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final ITextOutput out,
      final IParameterRenderer renderer) {
    renderer.renderParameter(1, out);
    out.append('-');
    renderer.renderParameter(0, out);
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final IMath out,
      final IParameterRenderer renderer) {
    try (final IMath sub = out.sub()) {
      renderer.renderParameter(1, sub);
      renderer.renderParameter(0, sub);
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
    return SubBA.INSTANCE;
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
    return SubBA.INSTANCE;
  }
}
