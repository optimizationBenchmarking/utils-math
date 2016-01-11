package org.optimizationBenchmarking.utils.math.matrix.impl;

import java.util.concurrent.Callable;

import org.optimizationBenchmarking.utils.math.statistics.aggregate.IAggregate;
import org.optimizationBenchmarking.utils.reflection.EPrimitiveType;

/**
 * A builder for distance matrices wrapping around
 * {@link DistanceMatrixBuilder} and providing a way to ensure that all
 * data is stored in the proper order.
 */
public abstract class DistanceMatrixBuilderJob
    implements Callable<DistanceMatrix> {

  /** create */
  protected DistanceMatrixBuilderJob() {
    super();
  }

  /**
   * Get the expected type of distances
   *
   * @return the expected distance type
   */
  protected EPrimitiveType getDistanceType() {
    return ArrayBasedMatrixBuilder.DEFAULT_MATRIX_TYPE;
  }

  /**
   * Get the number of data elements
   *
   * @return the number of data elements
   */
  protected abstract int getElementCount();

  /**
   * Store the distance between the element at index {@code i} and the
   * element at index {@code j} into the aggregate {@code appendTo}.
   * Notice:
   * <ol>
   * <li>This distance must be the same as the distance between the
   * elements at indices {@code j} and {@code i}.</li>
   * <li>You must call exactly one of the {@code append} methods of the
   * aggregate.</li>
   * </ol>
   *
   * @param i
   *          the element at index {@code i}
   * @param j
   *          the element at index {@code j}
   * @param appendTo
   *          the aggregate to store the distance to
   */
  protected abstract void setDistance(final int i, final int j,
      final IAggregate appendTo);

  /**
   * The element at index {@code i} is no longer needed and may be
   * released, nulled, or disposed.
   *
   * @param i
   *          the element index
   */
  protected void releaseElement(final int i) {
    // does nothing by default
  }

  /** {@inheritDoc} */
  @Override
  public final DistanceMatrix call() {
    final DistanceMatrixBuilder builder;
    final int m, size;
    int i, j;

    m = this.getElementCount();
    size = DistanceMatrix._size(m);
    builder = new DistanceMatrixBuilder(//
        this.getDistanceType(), size);
    builder.setM(m);

    for (i = 0; i < m; i++) {
      for (j = (i + 1); j < m; j++) {
        this.setDistance(i, j, builder);
      }
      this.releaseElement(i);
    }

    return builder.make();
  }
}
