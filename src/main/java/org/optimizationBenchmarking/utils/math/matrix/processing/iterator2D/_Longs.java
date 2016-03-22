package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

/** a matrix of {@code long}s */
final class _Longs extends _Matrix {

  /** the values */
  private final long[] m_values;

  /**
   * create the double matrix
   *
   * @param maxN
   *          the maximum number of doubles
   */
  _Longs(final int maxN) {
    super();
    this.m_values = new long[maxN];
  }

  /** {@inheritDoc} */
  @Override
  public final double getDouble(final int row, final int column) {
    if ((row == 0) && (column >= 0) && (column < this.m_currentN)) {
      return this.m_values[column];
    }
    return super.getDouble(row, column);
  }

  /** {@inheritDoc} */
  @Override
  public final long getLong(final int row, final int column) {
    if ((row == 0) && (column >= 0) && (column < this.m_currentN)) {
      return this.m_values[column];
    }
    return super.getLong(row, column);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isIntegerMatrix() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  final int _addLong(final long value) {
    final int index;
    index = this.m_currentN;
    this.m_values[index] = value;
    this.m_currentN = (index + 1);
    return index;
  }
}
