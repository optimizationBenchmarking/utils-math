package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;

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
   * Get the number of matrices used in the current {@link #getY()}. This
   * is the same as
   * <code>{@link #getY() getY()}.{@link org.optimizationBenchmarking.utils.math.matrix.IMatrix#n() n()}</code>
   * . You can get a full list of all matrices over which we iterate via
   * {@link #getMatrices()}.
   *
   * @return the number of currently used source matrices
   * @see #getSourceMatrix(int)
   * @see #getSourceMatrixIndex(int)
   * @see #getMatrices()
   */
  public abstract int getSourceMatrixCount();

  /**
   * Get the source matrix at the given index. This is the matrix which is
   * responsible for the {@code yIndexN}th value in {@link #getY()}, i.e.,
   * the matrix represented by
   * <code>{@link #getY()}.{@link org.optimizationBenchmarking.utils.math.matrix.IMatrix#getDouble(int, int) getDouble(0, yIndexN)}</code>
   * . You can get a full list of all matrices over which we iterate via
   * {@link #getMatrices()}.
   *
   * @param yIndexN
   *          the index
   * @return the source matrix at that index
   * @see #getSourceMatrixCount()
   * @see #getSourceMatrixIndex(int)
   * @see #getMatrices()
   */
  public abstract IMatrix getSourceMatrix(final int yIndexN);

  /**
   * Get the index of the source matrix responsible for the {@code y} value
   * at index {@code yIndexN}, i.e., the index of the matrix represented by
   * <code>{@link #getY()}.{@link org.optimizationBenchmarking.utils.math.matrix.IMatrix#getDouble(int, int) getDouble(0, yIndexN)}</code>
   * . You can get a full list of all matrices over which we iterate via
   * {@link #getMatrices()}.
   *
   * @param yIndexN
   *          the index
   * @return the index of the source matrix
   * @see #getSourceMatrixCount()
   * @see #getSourceMatrix(int)
   * @see #getMatrices()
   */
  public abstract int getSourceMatrixIndex(final int yIndexN);
}
