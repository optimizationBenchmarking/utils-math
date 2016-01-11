package org.optimizationBenchmarking.utils.math.matrix.impl;

import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;
import org.optimizationBenchmarking.utils.reflection.EPrimitiveType;

/**
 * A matrix builder allows us to build a matrix row by row. It tries to fit
 * the data into a primitive array of minimal type.
 */
public final class MatrixBuilder extends ArrayBasedMatrixBuilder {

  /** the m */
  private int m_m;

  /** the n */
  private int m_n;

  /**
   * create the matrix builder
   *
   * @param expectedType
   *          the expected numerical type
   * @param expectedSize
   *          the expected size
   */
  public MatrixBuilder(final EPrimitiveType expectedType,
      final int expectedSize) {
    super(expectedType, expectedSize);
  }

  /**
   * create the matrix builder
   *
   * @param expectedSize
   *          the expected size
   */
  public MatrixBuilder(final int expectedSize) {
    super(expectedSize);
  }

  /** create the matrix builder */
  public MatrixBuilder() {
    super();
  }

  /**
   * create the matrix builder
   *
   * @param expectedType
   *          the expected numerical type
   */
  public MatrixBuilder(final EPrimitiveType expectedType) {
    super(expectedType);
  }

  /**
   * set m
   *
   * @param m
   *          the m
   */
  public final void setM(final int m) {
    final int maxSize, size;

    if (m <= 0) {
      throw new IllegalArgumentException("M cannot be less than 1."); //$NON-NLS-1$
    }
    if (m == this.m_m) {
      return;
    }

    if (this.m_m > 0) {
      throw new IllegalStateException("M has already been set."); //$NON-NLS-1$
    }

    if (this.m_n > 0) {
      size = this.getCurrentSize();
      if (size > (maxSize = (m * this.m_n))) {
        throw new IllegalArgumentException(//
            "Cannot set m to " + m + //$NON-NLS-1$
                " since n is " + this.m_n + //$NON-NLS-1$
                ", i.e., maxSize=" + maxSize + //$NON-NLS-1$
                ", which would be less than the number " + size + //$NON-NLS-1$
                " of already stored elements."); //$NON-NLS-1$
      }
      this.setMaxSize(maxSize);
    }
    this.m_m = m;
  }

  /**
   * set n
   *
   * @param n
   *          the n
   */
  public final void setN(final int n) {
    final int maxSize, size;

    if (n <= 0) {
      throw new IllegalArgumentException("N cannot be less than 1."); //$NON-NLS-1$
    }
    if (n == this.m_n) {
      return;
    }

    if (this.m_n > 0) {
      throw new IllegalStateException("N has already been set."); //$NON-NLS-1$
    }

    if (this.m_m > 0) {
      size = this.getCurrentSize();
      if (size > (maxSize = (n * this.m_m))) {
        throw new IllegalArgumentException(//
            "Cannot set n to " + n + //$NON-NLS-1$
                " since m is " + this.m_m + //$NON-NLS-1$
                ", i.e., maxSize=" + maxSize + //$NON-NLS-1$
                ", which would be less than the number " + size + //$NON-NLS-1$
                " of already stored elements."); //$NON-NLS-1$
      }
      this.setMaxSize(maxSize);
    }
    this.m_n = n;
  }

  /**
   * Append a matrix row-by-row
   *
   * @param matrix
   *          the matrix to be appended
   */
  public void appendRowByRow(final IMatrix matrix) {
    final int m, n;
    int i, j;

    if (matrix == null) {
      throw new IllegalArgumentException(
          "Matrix to append row-wise cannot be null."); //$NON-NLS-1$
    }

    m = matrix.m();
    n = matrix.n();
    if (matrix.isIntegerMatrix()) {
      for (i = 0; i < m; i++) {
        for (j = 0; j < n; j++) {
          this.append(matrix.getLong(i, j));
        }
      }
    } else {
      for (i = 0; i < m; i++) {
        for (j = 0; j < n; j++) {
          this.append(matrix.getDouble(i, j));
        }
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final AbstractMatrix make() {
    int size, maxSize;

    size = this.getCurrentSize();
    maxSize = this.getMaximumSize();

    if (this.m_m > 0) {
      if (this.m_n > 0) {
        if (size != maxSize) {
          throw new IllegalStateException(((((((//
          size + " elements in matrix, but ") + //$NON-NLS-1$
              this.m_m) + 'x') + this.m_n) + '=') + maxSize)
              + " required.");//$NON-NLS-1$
        }
      } else {
        this.m_n = (size / this.m_m);
        if ((this.m_n * this.m_m) != size) {
          throw new IllegalStateException((((//
          size + " elements in matrix and m=") + this.m_m) //$NON-NLS-1$
              + " which would result in n=") + //$NON-NLS-1$
              ((size / ((double) (this.m_m)))));
        }
      }
    } else {
      if (this.m_n > 0) {
        this.m_m = (size / this.m_n);
        if ((this.m_n * this.m_m) != size) {
          throw new IllegalStateException((((//
          size + " elements in matrix and n=") + this.m_m) //$NON-NLS-1$
              + " which would result in m=") + //$NON-NLS-1$
              ((size / ((double) (this.m_n)))));
        }
      } else {
        if (size < 1) {
          throw new IllegalStateException(//
              "Matrix must have at least 1 element.");//$NON-NLS-1$
        }
        this.m_m = size;
        this.m_n = 1;
      }
    }

    return super.make();
  }

  /** {@inheritDoc} */
  @Override
  protected final ByteMatrix1D make(final byte[] data) {
    return new ByteMatrix1D(data, this.m_m, this.m_n);
  }

  /** {@inheritDoc} */
  @Override
  protected final ShortMatrix1D make(final short[] data) {
    return new ShortMatrix1D(data, this.m_m, this.m_n);
  }

  /** {@inheritDoc} */
  @Override
  protected final IntMatrix1D make(final int[] data) {
    return new IntMatrix1D(data, this.m_m, this.m_n);
  }

  /** {@inheritDoc} */
  @Override
  protected final LongMatrix1D make(final long[] data) {
    return new LongMatrix1D(data, this.m_m, this.m_n);
  }

  /** {@inheritDoc} */
  @Override
  protected final FloatMatrix1D make(final float[] data) {
    return new FloatMatrix1D(data, this.m_m, this.m_n);
  }

  /** {@inheritDoc} */
  @Override
  protected final DoubleMatrix1D make(final double[] data) {
    return new DoubleMatrix1D(data, this.m_m, this.m_n);
  }
}
