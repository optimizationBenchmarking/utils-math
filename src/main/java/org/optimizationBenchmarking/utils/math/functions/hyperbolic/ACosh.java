package org.optimizationBenchmarking.utils.math.functions.hyperbolic;

import org.apache.commons.math3.util.FastMath;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;

/** The acosh function */
public final class ACosh extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the precedence priority of the arcus hyperbolic cosine operator */
  public static final int PRECEDENCE_PRIORITY = //
  Sinh.PRECEDENCE_PRIORITY;

  /** the globally shared instance */
  public static final ACosh INSTANCE = new ACosh();

  /** instantiate */
  private ACosh() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int getPrecedencePriority() {
    return ACosh.PRECEDENCE_PRIORITY;
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x1) {
    return FastMath.acosh(x1);
  }

  /** {@inheritDoc} */
  @Override
  public final Cosh invertFor(final int index) {
    return Cosh.INSTANCE;
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
    return ACosh.INSTANCE;
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
    return ACosh.INSTANCE;
  }
}
