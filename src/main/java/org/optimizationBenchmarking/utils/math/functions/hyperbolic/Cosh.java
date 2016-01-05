package org.optimizationBenchmarking.utils.math.functions.hyperbolic;

import org.apache.commons.math3.util.FastMath;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;

/** The cosh function */
public final class Cosh extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the precedence priority of the hyperbolic cosine operator */
  public static final int PRECEDENCE_PRIORITY = //
  Sinh.PRECEDENCE_PRIORITY;
  /** the globally shared instance */
  public static final Cosh INSTANCE = new Cosh();

  /** instantiate */
  private Cosh() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int getPrecedencePriority() {
    return Cosh.PRECEDENCE_PRIORITY;
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x1) {
    return FastMath.cosh(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final ACosh invertFor(final int index) {
    return ACosh.INSTANCE;
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
    return Cosh.INSTANCE;
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
    return Cosh.INSTANCE;
  }
}
