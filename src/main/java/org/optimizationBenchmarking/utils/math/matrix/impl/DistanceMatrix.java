package org.optimizationBenchmarking.utils.math.matrix.impl;

import org.optimizationBenchmarking.utils.IImmutable;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;

/**
 * A square matrix whose diagonal elements are all {@code 0} and the
 * elements below the diagonal mirror those above.
 */
public abstract class DistanceMatrix extends AbstractMatrix
    implements IImmutable {

  /** the m */
  final int m_m;

  /**
   * create the matrix
   *
   * @param m
   *          the m
   */
  DistanceMatrix(final int m) {
    super();
    this.m_m = m;
  }

  /**
   * Check the size.
   *
   * @param length
   *          the length
   * @param m
   *          the m
   */
  static final void _checkSize(final int length, final int m) {
    final int size;
    size = DistanceMatrix._size(m);
    if (length != size) {
      throw new IllegalArgumentException(//
          ((("Distance matrix data must contain exactly " + //$NON-NLS-1$
              size + //
              " elements to facilitate an " + m) + '*') + //$NON-NLS-1$
              m + " distance matrix, but contains " + length) + '.'); //$NON-NLS-1$
    }
  }

  /**
   * Compute the size of a matrix, based on {@code m}
   *
   * @param m
   *          the {@code m}
   * @return the matrix size
   */
  static final int _size(final int m) {
    return ((m * (m - 1)) >>> 1);
  }

  /** {@inheritDoc} */
  @Override
  public final int m() {
    return this.m_m;
  }

  /** {@inheritDoc} */
  @Override
  public final int n() {
    return this.m_m;
  }

  /**
   * Compute the index of the matrix element representing the given
   * distance
   *
   * @param smallIndex
   *          the smaller index dimension
   * @param bigIndex
   *          the larger index dimension
   * @param m
   *          the matrix dimension
   * @return the index into the 1-D array
   */
  static final int _index(final int smallIndex, final int bigIndex,
      final int m) {
    return (((smallIndex * m) - ((smallIndex * (smallIndex + 3)) >>> 1))
        + bigIndex) - 1;
  }

  /** {@inheritDoc} */
  @Override
  public final DistanceMatrix copy() {
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final DistanceMatrix transpose() {
    return this;
  }

  /**
   * Convert this distance matrix to a row vector
   *
   * @return a row vector backed by the same array as this matrix.
   */
  public abstract AbstractMatrix asRowVector();
}
