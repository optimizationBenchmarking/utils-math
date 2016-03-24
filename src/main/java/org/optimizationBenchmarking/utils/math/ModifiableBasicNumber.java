package org.optimizationBenchmarking.utils.math;

import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;

/** A basic number which can be modified. */
public final class ModifiableBasicNumber extends BasicNumber {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the state */
  private int m_state;

  /** the long value */
  private long m_long;

  /** the double value */
  private double m_double;

  /** the empty state */
  public ModifiableBasicNumber() {
    super();
    this.m_state = BasicNumber.STATE_EMPTY;
    this.m_double = Double.NaN;
    this.m_long = 0L;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isInteger() {
    return (this.m_state == BasicNumber.STATE_INTEGER);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isReal() {
    return ((this.m_state >= BasicNumber.STATE_INTEGER)
        && (this.m_state <= BasicNumber.STATE_DOUBLE));
  }

  /** {@inheritDoc} */
  @Override
  public final long longValue() {
    switch (this.m_state) {
      case STATE_INTEGER: {
        return this.m_long;
      }
      case STATE_DOUBLE: {
        if (this.m_double >= Long.MAX_VALUE) {
          return Long.MAX_VALUE;
        }
        if (this.m_double <= Long.MIN_VALUE) {
          return Long.MIN_VALUE;
        }
        return ((long) (this.m_double));
      }
      case STATE_POSITIVE_OVERFLOW:
      case STATE_POSITIVE_INFINITY: {
        return Long.MAX_VALUE;
      }
      case STATE_NEGATIVE_OVERFLOW:
      case STATE_NEGATIVE_INFINITY: {
        return Long.MIN_VALUE;
      }
      default: {
        return 0L;
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double doubleValue() {
    switch (this.m_state) {
      case STATE_INTEGER: {
        return this.m_long;
      }
      case STATE_DOUBLE: {
        return this.m_double;
      }
      case STATE_POSITIVE_OVERFLOW:
      case STATE_POSITIVE_INFINITY: {
        return Double.POSITIVE_INFINITY;
      }
      case STATE_NEGATIVE_OVERFLOW:
      case STATE_NEGATIVE_INFINITY: {
        return Double.NEGATIVE_INFINITY;
      }
      default: {
        return Double.NaN;
      }
    }
  }

  /**
   * copy the value of a given number
   *
   * @param src
   *          the number to copy
   */
  public final void assign(final BasicNumber src) {
    switch (src.getState()) {
      case BasicNumber.STATE_EMPTY: {
        this.setEmpty();
        return;
      }
      case BasicNumber.STATE_INTEGER: {
        this.setLong(src.longValue());
        return;
      }
      case BasicNumber.STATE_DOUBLE: {
        this.setDoubleFully(src.doubleValue());
        return;
      }
      case STATE_POSITIVE_OVERFLOW: {
        this.setPositiveOverflow();
        return;
      }
      case STATE_POSITIVE_INFINITY: {
        this.setPositiveInfinity();
        return;
      }
      case STATE_NEGATIVE_OVERFLOW: {
        this.setNegativeOverflow();
        return;
      }
      case STATE_NEGATIVE_INFINITY: {
        this.setNegativeInfinity();
        return;
      }
      default: {
        this.setNaN();
        return;
      }
    }
  }

  /**
   * copy the value of a given number after transforming it
   *
   * @param src
   *          the number to copy before the transform
   * @param transform
   *          the transformation to apply
   */
  public final void assignTransformed(final BasicNumber src,
      final UnaryFunction transform) {
    if (src.getState() == BasicNumber.STATE_INTEGER) {
      if (transform.isLongArithmeticAccurate()) {
        this.setLong(transform.computeAsLong(src.longValue()));
      } else {
        this.setDouble(transform.computeAsDouble(src.longValue()));
      }
    } else {
      this.setDouble(transform.computeAsDouble(src.doubleValue()));
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int getState() {
    return this.m_state;
  }

  /** set this number to empty */
  public final void setEmpty() {
    this.m_state = BasicNumber.STATE_EMPTY;
    this.m_double = Double.NaN;
    this.m_long = 0L;
  }

  /** set this number to NaN */
  public final void setNaN() {
    this.m_state = BasicNumber.STATE_NAN;
    this.m_double = Double.NaN;
    this.m_long = 0L;
  }

  /** Set this number to positive infinity */
  public final void setPositiveInfinity() {
    this.m_state = BasicNumber.STATE_POSITIVE_INFINITY;
    this.m_double = Double.POSITIVE_INFINITY;
    this.m_long = Long.MAX_VALUE;
  }

  /** Set this number to positive overflow */
  public final void setPositiveOverflow() {
    this.m_state = BasicNumber.STATE_POSITIVE_OVERFLOW;
    this.m_double = Double.POSITIVE_INFINITY;
    this.m_long = Long.MAX_VALUE;
  }

  /** Set this number to negative infinity */
  public final void setNegativeInfinity() {
    this.m_state = BasicNumber.STATE_NEGATIVE_INFINITY;
    this.m_double = Double.NEGATIVE_INFINITY;
    this.m_long = Long.MIN_VALUE;
  }

  /** Set this number to negative overflow */
  public final void setNegativeOverflow() {
    this.m_state = BasicNumber.STATE_NEGATIVE_OVERFLOW;
    this.m_double = Double.NEGATIVE_INFINITY;
    this.m_long = Long.MIN_VALUE;
  }

  /**
   * set the {@code long} value
   *
   * @param value
   *          the value
   */
  public final void setLong(final long value) {
    this.m_long = value;
    this.m_state = BasicNumber.STATE_INTEGER;
  }

  /**
   * Set to a finite {@code double} value: This assumes that {@code value}
   * is finite.
   *
   * @param value
   *          the value
   */
  public final void setDouble(final double value) {
    this.m_double = value;
    this.m_state = BasicNumber.STATE_DOUBLE;
  }

  /**
   * Set to a {@code double} value which could also be an integer,
   * infinite, or NaN.
   *
   * @param value
   *          the value
   */
  public final void setDoubleFully(final double value) {
    if (NumericalTypes.isLong(value)) {
      this.setLong((long) value);
    } else {
      if (value >= Double.POSITIVE_INFINITY) {
        this.m_state = BasicNumber.STATE_POSITIVE_INFINITY;
      } else {
        if (value <= Double.NEGATIVE_INFINITY) {
          this.m_state = BasicNumber.STATE_NEGATIVE_INFINITY;
        } else {
          if (value != value) {
            this.m_state = BasicNumber.STATE_NAN;
          } else {
            this.setDouble(value);
          }
        }
      }
    }
  }
}
