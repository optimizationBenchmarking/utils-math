package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.math.NumericalTypes;

/** a matrix of {@code double}s */
final class _Doubles extends _Matrix {

  /** the values */
  private final double[] m_values;

  /** is this an integer matrix? */
  private boolean m_isInteger;

  /**
   * create the double matrix
   *
   * @param maxN
   *          the maximum number of doubles
   */
  _Doubles(final int maxN) {
    super();
    this.m_values = new double[maxN];
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
      return ((long) (this.m_values[column]));
    }
    return super.getLong(row, column);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isIntegerMatrix() {
    return this.m_isInteger;
  }

  /** {@inheritDoc} */
  @Override
  final void _reset() {
    super._reset();
    this.m_isInteger = true;
  }

  /** {@inheritDoc} */
  @Override
  final int _addDouble(final double value) {
    final int index;
    this.m_isInteger = (this.m_isInteger && NumericalTypes.isLong(value));
    index = this.m_currentN;
    this.m_values[index] = value;
    this.m_currentN = (index + 1);
    return index;
  }
}
