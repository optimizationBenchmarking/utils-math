package org.optimizationBenchmarking.utils.math.matrix.impl;

import org.optimizationBenchmarking.utils.reflection.EPrimitiveType;

/**
 * A matrix builder allows us to build distance matrices. By adding the
 * elements above the diagonal one-by-one. Using this job requires adhering
 * to the specific structure of the distance matrix. It is easier to
 * instead create a new type of {@link DistanceMatrixBuilderJob}, as this
 * will help you to automatically store the data in the right order.
 */
public final class DistanceMatrixBuilder extends ArrayBasedMatrixBuilder {

  /** the {@code m} size */
  private int m_m;

  /**
   * create the matrix builder
   *
   * @param expectedType
   *          the expected numerical type
   * @param expectedSize
   *          the expected size
   */
  public DistanceMatrixBuilder(final EPrimitiveType expectedType,
      final int expectedSize) {
    super(expectedType, expectedSize);
  }

  /**
   * create the matrix builder
   *
   * @param expectedSize
   *          the expected size
   */
  public DistanceMatrixBuilder(final int expectedSize) {
    super(expectedSize);
  }

  /** create the matrix builder */
  public DistanceMatrixBuilder() {
    super();
  }

  /**
   * create the matrix builder
   *
   * @param expectedType
   *          the expected numerical type
   */
  public DistanceMatrixBuilder(final EPrimitiveType expectedType) {
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

    size = this.getCurrentSize();
    maxSize = DistanceMatrix._size(m);
    if (maxSize < size) {
      throw new IllegalArgumentException(//
          "Cannot set m to " + m + //$NON-NLS-1$
              " since the maximum size would be " + maxSize + //$NON-NLS-1$
              ", which would be less than the number " + size + //$NON-NLS-1$
              " of already stored elements."); //$NON-NLS-1$
    }

    this.setMaxSize(maxSize);
    this.m_m = m;
  }

  /**
   * Compute the square matrix dimension {@code m} from a given size.
   *
   * @param size
   *          the size
   * @return the square matrix dimension
   */
  private static final int __m(final int size) {
    final double dres;
    final int ires, resSize;

    dres = (0.5d + Math.sqrt(0.25d + (size + size)));
    ires = ((int) (Math.round(dres)));

    if (Math.abs(dres - ires) > 1e-10d) {
      throw new IllegalStateException(
          "The number of elements added to the matrix, " + size + //$NON-NLS-1$
              ", does not lead to a square distance matrix, as the corresponding matrix dimension would be " //$NON-NLS-1$
              + dres);
    }

    resSize = DistanceMatrix._size(ires);

    if (resSize != size) {
      throw new IllegalStateException(
          "The number of elements added to the matrix, " + size + //$NON-NLS-1$
              ", does not lead to a square distance matrix, as the corresponding matrix dimension would be " //$NON-NLS-1$
              + dres + ", corresponding to a " + resSize + //$NON-NLS-1$
              "-element matrix.");//$NON-NLS-1$
    }

    return ires;
  }

  /** {@inheritDoc} */
  @Override
  public final DistanceMatrix make() {
    int size, maxSize;

    size = this.getCurrentSize();
    maxSize = this.getMaximumSize();

    if (this.m_m <= 0) {
      this.m_m = DistanceMatrixBuilder.__m(size);
    }

    DistanceMatrix._checkSize(size, this.m_m);

    if (maxSize > 0) {
      if (maxSize != size) {
        throw new IllegalStateException((//
            size + " elements in matrix, but ") + //$NON-NLS-1$
            maxSize + " are allocated.");//$NON-NLS-1$
      }
    }

    return ((DistanceMatrix) (super.make()));
  }

  /** {@inheritDoc} */
  @Override
  protected final ByteDistanceMatrix1D make(final byte[] data) {
    return new ByteDistanceMatrix1D(data, this.m_m);
  }

  /** {@inheritDoc} */
  @Override
  protected final ShortDistanceMatrix1D make(final short[] data) {
    return new ShortDistanceMatrix1D(data, this.m_m);
  }

  /** {@inheritDoc} */
  @Override
  protected final IntDistanceMatrix1D make(final int[] data) {
    return new IntDistanceMatrix1D(data, this.m_m);
  }

  /** {@inheritDoc} */
  @Override
  protected final LongDistanceMatrix1D make(final long[] data) {
    return new LongDistanceMatrix1D(data, this.m_m);
  }

  /** {@inheritDoc} */
  @Override
  protected final FloatDistanceMatrix1D make(final float[] data) {
    return new FloatDistanceMatrix1D(data, this.m_m);
  }

  /** {@inheritDoc} */
  @Override
  protected final DoubleDistanceMatrix1D make(final double[] data) {
    return new DoubleDistanceMatrix1D(data, this.m_m);
  }
}
