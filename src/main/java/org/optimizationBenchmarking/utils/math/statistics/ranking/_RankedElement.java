package org.optimizationBenchmarking.utils.math.statistics.ranking;

/** a ranked element */
abstract class _RankedElement implements Comparable<_RankedElement> {

  /** the index of the original value */
  final int m_index;

  /** the rank this element received */
  double m_rank;

  /**
   * create the ranked element
   *
   * @param index
   *          the first index of the original value
   */
  _RankedElement(final int index) {
    super();
    this.m_index = index;
  }
}
