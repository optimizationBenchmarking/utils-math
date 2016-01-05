package org.optimizationBenchmarking.utils.math.statistics.aggregate;

import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.MathUtils;
import org.optimizationBenchmarking.utils.math.NumericalTypes;

/**
 * This class computes the minimum of a set of numbers, but ignores all
 * infinite or NaN numbers.
 */
public final class FiniteMinimumAggregate extends _StatefulNumber {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** instantiate */
  public FiniteMinimumAggregate() {
    super();
  }

  /** {@inheritDoc} */
  @SuppressWarnings("incomplete-switch")
  @Override
  public final void append(final long value) {
    switch (this.m_state) {
      case STATE_EMPTY:
      case STATE_NEGATIVE_OVERFLOW:
      case STATE_NEGATIVE_INFINITY:
      case STATE_POSITIVE_OVERFLOW:
      case STATE_POSITIVE_INFINITY:
      case STATE_NAN: {
        this.m_state = BasicNumber.STATE_INTEGER;
        this.m_long = value;
        return;
      }

      case STATE_INTEGER: {
        if (value < this.m_long) {
          this.m_long = value;
        }
        return;
      }

      case STATE_DOUBLE: {
        if (value < this.m_double) {
          this.m_state = BasicNumber.STATE_INTEGER;
          this.m_long = value;
        }
      }
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("incomplete-switch")
  @Override
  public final void append(final double value) {

    if (NumericalTypes.isLong(value)) {
      this.append((long) value);
      return;
    }

    if (!MathUtils.isFinite(value)) {
      return;
    }

    switch (this.m_state) {
      case STATE_EMPTY:
      case STATE_NEGATIVE_OVERFLOW:
      case STATE_NEGATIVE_INFINITY:
      case STATE_POSITIVE_OVERFLOW:
      case STATE_POSITIVE_INFINITY:
      case STATE_NAN: {
        this.m_state = BasicNumber.STATE_DOUBLE;
        this.m_double = value;
        return;
      }

      case STATE_INTEGER: {
        if (value < this.m_long) {
          this.m_state = BasicNumber.STATE_DOUBLE;
          this.m_double = value;
        }
        return;
      }

      case STATE_DOUBLE: {
        if (value < this.m_double) {
          this.m_double = value;
        }
      }
    }
  }
}
