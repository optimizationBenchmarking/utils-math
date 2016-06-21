package org.optimizationBenchmarking.utils.math.statistics.statisticInfo;

import org.optimizationBenchmarking.utils.math.statistics.aggregate.IAggregate;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.InterQuantileRangeAggregate;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.QuantileAggregate;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.QuantileDataStore;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.StandardDeviationAggregate;

/** A builder for statistic info records. */
public final class StatisticInfoBuilder implements IAggregate {

  /** the standard deviation aggregate */
  private final StandardDeviationAggregate m_stddev;

  /** the quantile data store */
  private final QuantileDataStore m_quantileStore;

  /** the inter-quantile range */
  private final InterQuantileRangeAggregate m_interQuantileRange;

  /** the 5% quantile aggregate */
  private final QuantileAggregate m_q05;

  /** the 25% quantile aggregate */
  private final QuantileAggregate m_q25;

  /** the median aggregate */
  private final QuantileAggregate m_median;

  /** the 75% quantile aggregate */
  private final QuantileAggregate m_q75;

  /** the 95% quantile aggregate */
  private final QuantileAggregate m_q95;

  /** create the builder */
  public StatisticInfoBuilder() {
    super();
    this.m_stddev = new StandardDeviationAggregate();
    this.m_quantileStore = new QuantileDataStore();
    this.m_interQuantileRange = new InterQuantileRangeAggregate(0.25,
        0.75d, this.m_quantileStore);
    this.m_q05 = new QuantileAggregate(0.05d, this.m_quantileStore);
    this.m_q25 = new QuantileAggregate(0.25d, this.m_quantileStore);
    this.m_median = new QuantileAggregate(0.5d, this.m_quantileStore);
    this.m_q75 = new QuantileAggregate(0.75d, this.m_quantileStore);
    this.m_q95 = new QuantileAggregate(0.95d, this.m_quantileStore);
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final byte v) {
    this.m_stddev.append(v);
    this.m_quantileStore.append(v);
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final short v) {
    this.m_stddev.append(v);
    this.m_quantileStore.append(v);
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final int v) {
    this.m_stddev.append(v);
    this.m_quantileStore.append(v);
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final long v) {
    this.m_stddev.append(v);
    this.m_quantileStore.append(v);
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final float v) {
    this.m_stddev.append(v);
    this.m_quantileStore.append(v);
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final double v) {
    this.m_stddev.append(v);
    this.m_quantileStore.append(v);
  }

  /**
   * Build the statistic info
   *
   * @return the statistic information record
   */
  public final StatisticInfo build() {
    return new StatisticInfo(//
        this.m_stddev.getCountValue(), //
        this.m_stddev.getMinimum().toNumber(), //
        this.m_stddev.getMaximum().toNumber(), //
        this.m_stddev.getArithmeticMean().toNumber(), //
        this.m_stddev.toNumber(), //
        this.m_median.toNumber(), //
        this.m_interQuantileRange.toNumber(), this.m_q05.toNumber(), //
        this.m_q25.toNumber(), //
        this.m_q75.toNumber(), //
        this.m_q95.toNumber());
  }
}
