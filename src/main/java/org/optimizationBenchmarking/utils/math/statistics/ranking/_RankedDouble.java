package org.optimizationBenchmarking.utils.math.statistics.ranking;

import org.optimizationBenchmarking.utils.comparison.Compare;

/** a ranked {@code double} */
final class _RankedDouble extends _RankedElement {

  /** the original value */
  final double m_value;

  /**
   * create the ranked {@code double}
   *
   * @param index
   *          the first index of the original value
   * @param value
   *          the {@code double} value
   */
  _RankedDouble(final int index, final double value) {
    super(index);
    this.m_value = value;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final _RankedElement o) {
    if (o instanceof _RankedDouble) {
      return Compare.compare(this.m_value, ((_RankedDouble) o).m_value);
    }
    if (o instanceof _RankedLong) {
      return (-(Compare.compare(((_RankedLong) o).m_value, this.m_value)));
    }
    return (-(o.compareTo(this)));
  }
}
