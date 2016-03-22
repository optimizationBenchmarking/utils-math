package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;

/** the internal base class for matrix */
abstract class _Matrix extends AbstractMatrix {
  /** the current number of columns */
  int m_currentN;

  /** create */
  _Matrix() {
    super();
  }

  /**
   * there is always exactly one row in this matrix
   *
   * @return 1
   */
  @Override
  public final int m() {
    return 1;
  }

  /** {@inheritDoc} */
  @Override
  public final int n() {
    return this.m_currentN;
  }

  /** reset the matrix */
  void _reset() {
    this.m_currentN = 0;
  }

  /**
   * add a {@code long} element to the matrix
   *
   * @param value
   *          the {@code long} to add
   * @return the source index under which the matrix should be stored
   */
  int _addLong(final long value) {
    return this._addDouble(value);
  }

  /**
   * add a {@code double} element to the matrix
   *
   * @param value
   *          the {@code double} to add
   * @return the source index under which the matrix should be stored
   */
  int _addDouble(final double value) {
    throw new UnsupportedOperationException();
  }
}
