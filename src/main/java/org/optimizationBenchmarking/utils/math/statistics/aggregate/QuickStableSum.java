package org.optimizationBenchmarking.utils.math.statistics.aggregate;

import org.optimizationBenchmarking.utils.math.BasicNumber;

/**
 * This is an implementation of a stable sum which is designed to be
 * quicker than {@link StableSum} but retain some of its good properties,
 * but sacrifices some for speed:
 * <ul>
 * <li>It will not consider {@code long} arithmetic and work solely in
 * {@code double} arithmetic.</li>
 * <li>It will not distinguish between overflow and infinity.</li>
 * <li>It will not distinguish between 0 and empty.
 * <li>
 * </ul>
 */
public final class QuickStableSum extends ScalarAggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the current sum */
  private double m_sum;

  /** the compensation */
  private double m_compensation;

  /** create */
  public QuickStableSum() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final int value) {
    final double y, t, sum;

    y = value - this.m_compensation;
    t = (sum = this.m_sum) + y;
    this.m_compensation = (t - sum) - y;
    this.m_sum = t;
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final long value) {
    double doubleValue, y, t, sum, compensate;

    doubleValue = value;
    y = doubleValue - this.m_compensation;
    t = (sum = this.m_sum) + y;
    compensate = (t - sum) - y;
    sum = t;

    doubleValue = (value - ((long) doubleValue));
    y = doubleValue - compensate;
    t = sum + y;
    this.m_compensation = (t - sum) - y;
    this.m_sum = t;
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_sum = 0d;
    this.m_compensation = 0d;
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final double value) {
    final double y, t, sum;

    y = value - this.m_compensation;
    t = (sum = this.m_sum) + y;
    this.m_compensation = (t - sum) - y;
    this.m_sum = t;
  }

  /** {@inheritDoc} */
  @Override
  public final int getState() {
    final double sum;

    sum = this.m_sum;
    if (sum >= Double.POSITIVE_INFINITY) {
      return BasicNumber.STATE_POSITIVE_INFINITY;
    }
    if (sum <= Double.NEGATIVE_INFINITY) {
      return BasicNumber.STATE_NEGATIVE_INFINITY;
    }
    if (sum != sum) {
      return BasicNumber.STATE_NAN;
    }
    if (((long) sum) == sum) {
      return BasicNumber.STATE_INTEGER;
    }
    return BasicNumber.STATE_DOUBLE;
  }

  /** {@inheritDoc} */
  @Override
  public final long longValue() {
    return ((long) (this.m_sum));
  }

  /** {@inheritDoc} */
  @Override
  public final double doubleValue() {
    return this.m_sum;
  }
}
