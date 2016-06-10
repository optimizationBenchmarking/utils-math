package org.optimizationBenchmarking.utils.math.statistics.ranking;

import org.optimizationBenchmarking.utils.comparison.Compare;

/** a ranked {@code long} */
final class _RankedLong extends _RankedElement {

  /** the original value */
  final long m_value;

  /**
   * create the ranked {@code long}
   *
   * @param index
   *          the first index of the original value
   * @param value
   *          the {@code long} value
   */
  _RankedLong(final int index, final long value) {
    super(index);
    this.m_value = value;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final _RankedElement o) {
    if (o instanceof _RankedLong) {
      return Long.compare(this.m_value, ((_RankedLong) o).m_value);
    }
    if (o instanceof _RankedDouble) {
      return Compare.compare(this.m_value, ((_RankedDouble) o).m_value);
    }
    return (-(o.compareTo(this)));
  }
}
