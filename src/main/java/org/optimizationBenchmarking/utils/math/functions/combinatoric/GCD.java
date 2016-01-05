package org.optimizationBenchmarking.utils.math.functions.combinatoric;

import org.optimizationBenchmarking.utils.math.MathUtils;
import org.optimizationBenchmarking.utils.math.NumericalTypes;
import org.optimizationBenchmarking.utils.math.functions.BinaryFunction;

/**
 * The gcd function
 */
public final class GCD extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final GCD INSTANCE = new GCD();

  /** instantiate */
  private GCD() {
    super();
  }

  /**
   * compute the greatest common divisor
   *
   * @param a
   *          the n
   * @param b
   *          the k
   * @return the coefficient
   */
  @Override
  public final int computeAsInt(final int a, final int b) {
    return ((int) (MathUtils.gcd(a, b)));
  }

  /**
   * compute the greatest common divisor
   *
   * @param a
   *          the n
   * @param b
   *          the k
   * @return the coefficient
   */
  @Override
  public final long computeAsLong(final long a, final long b) {
    final long result;

    result = MathUtils.gcd(a, b);
    if (result < 0L) {
      throw new IllegalArgumentException(//
          "GCD of " + a + " and " + b + //$NON-NLS-1$//$NON-NLS-2$
              " cannot be represented as long.");//$NON-NLS-1$ /
    }
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x1, final double x2) {
    long a;
    double u, v, temp;

    if (NumericalTypes.isLong(x1) && NumericalTypes.isLong(x2)) {
      a = MathUtils.gcd(((long) x1), ((long) x2));
      if (a >= 0L) {
        return a;
      }
    }

    u = x1;
    v = x2;

    for (;;) {
      if (NumericalTypes.isLong(v)) {
        a = ((long) v);
        if (a == 0L) {
          return Math.abs(u);
        }

        if (NumericalTypes.isLong(u)) {
          return this.computeAsLong(((long) u), a);
        }
      } else {
        if (v != v) {
          return Double.NaN;
        }
      }

      if (v == 0d) {
        return Math.abs(u);
      }

      temp = (u % v);
      u = v;
      v = temp;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isLongArithmeticAccurate() {
    return true;
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
    return GCD.INSTANCE;
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
    return GCD.INSTANCE;
  }
}
