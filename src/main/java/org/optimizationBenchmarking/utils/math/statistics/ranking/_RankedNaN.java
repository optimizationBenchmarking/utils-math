package org.optimizationBenchmarking.utils.math.statistics.ranking;

/** a ranked {@code long} */
final class _RankedNaN extends _RankedElement {

  /** the strategy */
  final ENaNStrategy m_strategy;

  /**
   * create the ranked {@code long}
   *
   * @param index
   *          the index of the original value
   * @param strategy
   *          the strategy
   */
  _RankedNaN(final int index, final ENaNStrategy strategy) {
    super(index);
    this.m_strategy = strategy;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final _RankedElement o) {
    if (o instanceof _RankedLong) {
      return this.m_strategy._compareLong();
    }
    if (o instanceof _RankedDouble) {
      return this.m_strategy._compareDouble(((_RankedDouble) o).m_value);
    }
    return 0;
  }
}
