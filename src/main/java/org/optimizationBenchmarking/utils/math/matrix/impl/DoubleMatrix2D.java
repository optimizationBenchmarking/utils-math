package org.optimizationBenchmarking.utils.math.matrix.impl;

import org.optimizationBenchmarking.utils.IImmutable;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;

/**
 * A matrix backed by a two-dimensional {@code double} array.
 */
public final class DoubleMatrix2D extends AbstractMatrix
    implements IImmutable {

  /** the data */
  private final double[][] m_data;

  /**
   * create the matrix
   *
   * @param data
   *          the data
   */
  public DoubleMatrix2D(final double[][] data) {
    super();

    int c;

    if ((data == null) || (data.length <= 0)) {
      throw new IllegalArgumentException(//
          "Matrix data must not be null and must have at least one row."); //$NON-NLS-1$
    }

    c = data[0].length;
    for (final double[] dd : data) {
      if (dd.length != c) {
        throw new IllegalArgumentException(//
            "All rows must have same number of columns."); //$NON-NLS-1$
      }
    }

    this.m_data = data;
  }

  /** {@inheritDoc} */
  @Override
  public final int m() {
    return this.m_data.length;
  }

  /** {@inheritDoc} */
  @Override
  public final int n() {
    return this.m_data[0].length;
  }

  /** {@inheritDoc} */
  @Override
  public final double getDouble(final int row, final int column) {
    return this.m_data[row][column];
  }

  /** {@inheritDoc} */
  @Override
  public final long getLong(final int row, final int column) {
    return ((long) (this.m_data[row][column]));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isIntegerMatrix() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final IMatrix copy() {
    return this;
  }
}
