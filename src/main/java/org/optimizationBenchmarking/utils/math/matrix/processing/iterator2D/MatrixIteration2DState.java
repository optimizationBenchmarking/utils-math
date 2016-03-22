package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;

/** The state of a 2-dimensional matrix iteration */
public abstract class MatrixIteration2DState
    extends _MatrixIteration2DBase {

  /**
   * create
   *
   * @param copy
   *          the iteration to copy
   */
  MatrixIteration2DState(final _MatrixIteration2DBase copy) {
    super(copy);
  }

  /**
   * Get the current {@code x} coordinate.
   *
   * @return the current {@code x} coordinate
   */
  public abstract BasicNumber getX();

  /**
   * Get the {@code y} values corresponding to the current {@code x}
   * coordinate in form of a row matrix.
   *
   * @return the {@code y} values corresponding to the current {@code x}
   *         coordinate
   */
  public abstract AbstractMatrix getY();

  /**
   * get the index of the source matrix responsible for the {@code y} value
   * at index {@code yIndexN}.
   *
   * @param yIndexN
   *          the index
   * @return the index of the source matrix
   * @see #getSourceMatrixCount()
   * @see #getSourceMatrix(int)
   */
  public abstract int getSourceMatrixIndex(final int yIndexN);
}
