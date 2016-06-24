package org.optimizationBenchmarking.utils.math.statistics.aggregate;

import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.BasicNumberWrapper;
import org.optimizationBenchmarking.utils.math.functions.arithmetic.Div;

/**
 * The coefficient of variation is the fraction of standard deviation
 * divided by arithmetic mean
 */
public class CoefficientOfVariationAggregate extends MeanBasedAggregate {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the internal standard deviation aggregate */
  private final StandardDeviationAggregate m_stddev;

  /** create */
  public CoefficientOfVariationAggregate() {
    super();
    this.m_stddev = new StandardDeviationAggregate();
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final long value) {
    this.m_stddev.append(value);
    this._setEmpty();
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final double value) {
    this.m_stddev.append(value);
    this._setEmpty();
  }

  /** compute the value of this aggregate. */
  private final void __compute() {
    final double stddevDouble, meanDouble;
    long stddevLong, meanLong;

    switch (this.m_stddev.getState()) {
      case STATE_EMPTY: {
        this._setEmpty();
        return;
      }
      case STATE_NAN: {
        this._setNaN();
        return;
      }

      case STATE_INTEGER: {
        if (this.m_stddev.m_variance.m_mean
            .getState() == BasicNumber.STATE_INTEGER) {
          meanLong = this.m_stddev.m_variance.m_mean.longValue();
          if (meanLong > Long.MIN_VALUE) {
            stddevLong = this.m_stddev.longValue();

            if (meanLong == 0L) {
              if (stddevLong <= 0L) {
                this._setLong(0L);
                return;
              }
              this._setPositiveInfinity();
              return;
            }

            if (meanLong < 0L) {
              meanLong = (-meanLong);
            }
            if ((stddevLong % meanLong) == 0L) {
              this._setLong(stddevLong / meanLong);
              return;
            }
            this._setDoubleFully(
                Div.INSTANCE.computeAsDouble(stddevLong, meanLong));
            return;
          }
        }
      }

        //$FALL-THROUGH$
      default: {
        stddevDouble = this.m_stddev.doubleValue();
        meanDouble = Math
            .abs(this.m_stddev.m_variance.m_mean.doubleValue());
        if (meanDouble <= 0d) {
          if (stddevDouble <= 0d) {
            this._setLong(0L);
            return;
          }
          this._setPositiveInfinity();
          return;
        }
        this._setDoubleFully(stddevDouble / meanDouble);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int getState() {
    if (this.m_state == BasicNumber.STATE_EMPTY) {
      this.__compute();
    }
    return this.m_state;
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_stddev.reset();
    super.reset();
  }

  /** {@inheritDoc} */
  @Override
  public final long longValue() {
    if (this.m_state == BasicNumber.STATE_EMPTY) {
      this.__compute();
    }
    return super.longValue();
  }

  /** {@inheritDoc} */
  @Override
  public final double doubleValue() {
    if (this.m_state == BasicNumber.STATE_EMPTY) {
      this.__compute();
    }
    return super.doubleValue();
  }

  /** {@inheritDoc} */
  @Override
  public final BasicNumberWrapper getSum() {
    return this.m_stddev.getSum();
  }

  /** {@inheritDoc} */
  @Override
  public final BasicNumberWrapper getMinimum() {
    return this.m_stddev.getMinimum();
  }

  /** {@inheritDoc} */
  @Override
  public final BasicNumberWrapper getMaximum() {
    return this.m_stddev.getMaximum();
  }

  /** {@inheritDoc} */
  @Override
  public final long getCountValue() {
    return this.m_stddev.getCountValue();
  }

  /** {@inheritDoc} */
  @Override
  public final BasicNumber getCount() {
    return this.m_stddev.getCount();
  }

  /** {@inheritDoc} */
  @Override
  public final BasicNumberWrapper getArithmeticMean() {
    return this.m_stddev.getArithmeticMean();
  }

  /**
   * Get a basic number wrapper accessing the second moment
   *
   * @return the second moment
   */
  public final BasicNumberWrapper getSecondMoment() {
    return this.m_stddev.getSecondMoment();
  }

  /**
   * Get a basic number wrapper accessing the variance
   *
   * @return the variance
   */
  public final BasicNumberWrapper getVariance() {
    return this.m_stddev.getVariance();
  }

  /**
   * Get a basic number wrapper accessing the standard deviation
   *
   * @return the standard deviation
   */
  public final BasicNumberWrapper getStandardDeviation() {
    return new BasicNumberWrapper(this.m_stddev);
  }
}