package org.optimizationBenchmarking.utils.math.functions.arithmetic;

import org.optimizationBenchmarking.utils.document.spec.IMath;
import org.optimizationBenchmarking.utils.document.spec.IParameterRenderer;
import org.optimizationBenchmarking.utils.math.functions.BinaryFunction;
import org.optimizationBenchmarking.utils.math.functions.combinatoric.GCD;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;

/**
 * <p>
 * This function implements division.
 * </p>
 * <p>
 * My goal is to provide the most accurate way to get results. This means
 * that if the inputs are {@code long}s and we want to compute a
 * {@code double} result, we will not convert them to {@code double} right
 * away. Instead, we check if the operation can be performed with
 * {@code long} arithmetic directly. If this is not possible, we first
 * divide both numbers by their greatest common divisor in {@code long}
 * arithmetic before performing the actual division in {@code double}
 * arithmetic.
 * </p>
 * <p>
 * Checking if {@code double}s can be converted to {@code long}s and
 * performing the above operation seemingly does not lead to improved (or
 * even different) results, so we use floating point arithmetic for
 * floating point numbers.
 * </p>
 */
public final class Div extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the precedence priority of the division operator */
  public static final int PRECEDENCE_PRIORITY = Mul.PRECEDENCE_PRIORITY;

  /** the globally shared instance */
  public static final Div INSTANCE = new Div();

  /** instantiate */
  private Div() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int getPrecedencePriority() {
    return Div.PRECEDENCE_PRIORITY;
  }

  /** {@inheritDoc} */
  @Override
  public final byte computeAsByte(final byte x0, final byte x1) {
    return ((byte) (x0 / x1));
  }

  /** {@inheritDoc} */
  @Override
  public final short computeAsShort(final short x0, final short x1) {
    return ((short) (x0 / x1));
  }

  /** {@inheritDoc} */
  @Override
  public final int computeAsInt(final int x0, final int x1) {
    return (x0 / x1);
  }

  /** {@inheritDoc} */
  @Override
  public final long computeAsLong(final long x0, final long x1) {
    return (x0 / x1);
  }

  /** {@inheritDoc} */
  @Override
  public final float computeAsFloat(final float x0, final float x1) {
    // if (NumericalTypes.isLong(x0) && NumericalTypes.isLong(x1)) {
    // return ((float) (this.computeAsDouble(((long) x0), ((long) x1))));
    // }

    return (x0 / x1);
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x0, final double x1) {
    // if (NumericalTypes.isLong(x0) && NumericalTypes.isLong(x1)) {
    // return this.computeAsDouble(((long) x0), ((long) x1));
    // }

    return (x0 / x1);
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final long x0, final long x1) {
	    final long gcd;
	    long res;

	    if (x1 == 0L) {
	      if (x0 < 0L) {
	        return Double.NEGATIVE_INFINITY;
	      }
	      if (x0 > 0L) {
	        return Double.POSITIVE_INFINITY;
	      }
	      return Double.NaN;
	    }

	    final boolean x0Min = (x0 != Long.MIN_VALUE);
	    if (x0Min) {
	      // guard against overflow due to (-Long.MIN_VALUE) == Long.MIN_VALUE
	      res = (x0 / x1);
	      if ((x1 * res) == x0) {
	        return res;
	      }
	    }

	    final boolean x1Min = (x1 != Long.MIN_VALUE);
	    if (x1Min) {
	      // guard against overflow due to (-Long.MIN_VALUE) == Long.MIN_VALUE
	      res = (x1 / x0);
	      if ((x0 * res) == x1) {
	        return (1d / res);
	      }
	    }

	    if (x0Min && x1Min) {
	      // guard against overflow due to (-Long.MIN_VALUE) == Long.MIN_VALUE

	      // Try to achieve maximum accuracy by first dividing both numbers by
	      // their greatest common divisor. This could lead to the least
	      // rounding/truncation errors in the subsequent floating point
	      // division.
	      gcd = GCD.INSTANCE.computeAsLong(x0, x1);
	      return (((double) (x0 / gcd)) / ((double) (x1 / gcd)));
	    }
	    return (((double) x0) / ((double) x1));
  }

  /** {@inheritDoc} */
  @Override
  public final BinaryFunction invertFor(final int index) {
    if (index == 0) {
      return Mul.INSTANCE;
    }
    return Div.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final ITextOutput out,
      final IParameterRenderer renderer) {
    renderer.renderParameter(0, out);
    out.append('/');
    renderer.renderParameter(1, out);
  }

  /** {@inheritDoc} */
  @Override
  public final void mathRender(final IMath out,
      final IParameterRenderer renderer) {
    try (final IMath div = out.div()) {
      renderer.renderParameter(0, div);
      renderer.renderParameter(1, div);
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
    return Div.INSTANCE;
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
    return Div.INSTANCE;
  }
}
