package org.optimizationBenchmarking.utils.math.matrix.impl;

import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;

/**
 * A square matrix whose diagonal elements are all {@code 0} and the
 * elements below the diagonal mirror those above, backed by a
 * one-dimensional array of {@code byte}.
 */
public final class ByteDistanceMatrix1D extends DistanceMatrix {

  /** the data */
  private final byte[] m_data;

  /**
   * create the matrix
   *
   * @param data
   *          the data
   * @param m
   *          the m
   */
  public ByteDistanceMatrix1D(final byte[] data, final int m) {
    super(m);
    DistanceMatrix._checkSize(data.length, m);
    this.m_data = data;
  }

  /** {@inheritDoc} */
  @Override
  public final double getDouble(final int row, final int column) {
    if ((row >= 0) && (row < this.m_m) && (column >= 0)
        && (column < this.m_m)) {
      if (row < column) {
        return this.m_data[DistanceMatrix._index(row, column, this.m_m)];
      }
      if (row > column) {
        return this.m_data[DistanceMatrix._index(column, row, this.m_m)];
      }
      return 0d;
    }
    return super.getDouble(row, column);// throw IndexOutOfBoundsException
  }

  /** {@inheritDoc} */
  @Override
  public final long getLong(final int row, final int column) {
    if ((row >= 0) && (row < this.m_m) && (column >= 0)
        && (column < this.m_m)) {
      if (row < column) {
        return this.m_data[DistanceMatrix._index(row, column, this.m_m)];
      }
      if (row > column) {
        return this.m_data[DistanceMatrix._index(column, row, this.m_m)];
      }
      return 0L;
    }
    return super.getLong(row, column);// throw IndexOutOfBoundsException
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isIntegerMatrix() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public final AbstractMatrix asRowVector() {
    return new ByteMatrix1D(this.m_data, 1, this.m_data.length);
  }
}
