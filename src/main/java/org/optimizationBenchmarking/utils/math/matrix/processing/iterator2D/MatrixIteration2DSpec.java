package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import java.util.logging.Logger;

import org.optimizationBenchmarking.utils.collections.lists.ArrayListView;
import org.optimizationBenchmarking.utils.collections.visitors.IVisitor;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;

/** The matrix iteration 2d. */
public abstract class MatrixIteration2DSpec {

  /** the logger */
  Logger m_logger;
  /** set the iteration visitor */
  IVisitor<MatrixIteration2DState> m_visitor;
  /** the matrices */
  IMatrix[] m_matrices;

  /** the source matrix collection */
  volatile ArrayListView<IMatrix> m_sourceMatrixList;

  /** the {@code x}-dimension */
  int m_xDimension;
  /** the {@code y}-dimension */
  int m_yDimension;
  /** are the {@code x}-coordinates increasing? */
  EIterationDirection m_xDirection;
  /** the iteration mode */
  EIterationMode m_iterationMode;
  /** the start mode */
  EMissingValueMode m_startMode;
  /**
   * the value used to replace missing start values, only relevant if
   * <code>{@link #m_startMode}=={@link EMissingValueMode#SET_TO_VALUE}</code>
   */
  Number m_startReplacement;
  /** the end mode */
  EMissingValueMode m_endMode;
  /**
   * the value used to replace missing end values, only relevant if
   * <code>{@link #m_endMode}=={@link EMissingValueMode#SET_TO_VALUE}</code>
   */
  Number m_endReplacement;

  /** create the iteration */
  MatrixIteration2DSpec() {
    super();
    this.m_yDimension = 1;
    this.m_iterationMode = EIterationMode.DEFAULT;
    this.m_startMode = EMissingValueMode.DEFAULT_START_MODE;
    this.m_endMode = EMissingValueMode.DEFAULT_END_MODE;
    this.m_xDirection = EIterationDirection.INCREASING;
  }

  /**
   * create the matrix iteration 2d
   *
   * @param other
   *          the iteration
   */
  MatrixIteration2DSpec(final MatrixIteration2DSpec other) {
    super();

    this.m_logger = other.m_logger;
    this.m_visitor = other.m_visitor;
    this.m_matrices = other.m_matrices;
    this.m_sourceMatrixList = other.m_sourceMatrixList;
    this.m_xDimension = other.m_xDimension;
    this.m_yDimension = other.m_yDimension;
    this.m_iterationMode = other.m_iterationMode;
    this.m_startMode = other.m_startMode;
    this.m_endMode = other.m_endMode;
    this.m_xDirection = other.m_xDirection;
    this.m_startReplacement = other.m_startReplacement;
    this.m_endReplacement = other.m_endReplacement;
  }

  /**
   * Get the logger used in this matrix iteration
   *
   * @return the logger used in this matrix iteration
   */
  public final Logger getLogger() {
    return this.m_logger;
  }

  /**
   * Get the source matrices.
   *
   * @return the source matrices
   */
  public final ArrayListView<IMatrix> getMatrices() {
    ArrayListView<IMatrix> list;

    list = this.m_sourceMatrixList;
    if (list == null) {
      this.m_sourceMatrixList = list = new ArrayListView<>(
          this.m_matrices);
    }
    return list;
  }

  /**
   * Get the {@code x} dimension, i.e., the dimension over whose unique
   * values in all of the source matrices we iterate in an ordered fashion.
   *
   * @return Get the {@code x} dimension
   */
  public final int getXDimension() {
    return this.m_xDimension;
  }

  /**
   * Get the {@code y} dimension, i.e., the dimension whose values we want
   * to get for each unique value of the {@code x} dimension
   *
   * @return the {@code y} dimension
   */
  public final int getYDimension() {
    return this.m_yDimension;
  }

  /**
   * Get the iteration mode
   *
   * @return the iteration mode
   */
  public final EIterationMode getIterationMode() {
    return this.m_iterationMode;
  }

  /**
   * Get the modus defining how to deal with missing start values: We
   * iterate over multiple matrices. In the first iteration steps, the
   * smallest {@code x}-coordinate might be unique and some matrices may
   * not have any {@code y} value for it. What should we do with these
   * matrices?
   *
   * @return the modus defining how to deal with missing start values
   */
  public final EMissingValueMode getStartMode() {
    return this.m_startMode;
  }

  /**
   * Get the modus defining how to deal with missing end values: We iterate
   * over multiple matrices. In the last iteration steps, the largest
   * {@code x}-coordinate might be unique and some matrices may not have
   * any {@code y} value for it. What should we do with these matrices?
   *
   * @return the modus defining how to deal with missing end values
   */
  public final EMissingValueMode getEndMode() {
    return this.m_endMode;
  }

  /**
   * Get the iteration direction
   *
   * @return the iteration direction
   */
  public final EIterationDirection getXDirection() {
    return this.m_xDirection;
  }

  /**
   * Get the replacement for missing start values, or {@code null} if none
   * is set.
   *
   * @return the replacement for missing start values, or {@code null} if
   *         none is set
   */
  public final Number getStartReplacement() {
    return this.m_startReplacement;
  }

  /**
   * Get the replacement for missing end values, or {@code null} if none is
   * set.
   *
   * @return the replacement for missing end values, or {@code null} if
   *         none is set
   */
  public final Number getEndReplacement() {
    return this.m_endReplacement;
  }

  /**
   * Check the {@code y}-dimension
   *
   * @param yDimension
   *          the dimension
   */
  static final void _checkYDimension(final int yDimension) {
    if (yDimension < 0) {
      throw new IllegalArgumentException(//
          "Y-dimension cannot be " + yDimension); //$NON-NLS-1$
    }
  }

  /**
   * check a visitor
   *
   * @param visitor
   *          the visitor
   */
  static final void _checkVisitor(
      final IVisitor<MatrixIteration2DState> visitor) {
    if (visitor == null) {
      throw new IllegalArgumentException(
          "Cannot set iteration visitor to null."); //$NON-NLS-1$
    }
  }

  /**
   * check whether the matrices are not {@code null}
   *
   * @param matrices
   *          the matrices
   */
  static final void _checkMatricesNotNull(final IMatrix[] matrices) {
    if (matrices == null) {
      throw new IllegalArgumentException("Cannot set null matrix array."); //$NON-NLS-1$
    }
  }

  /**
   * check the matrices
   *
   * @param matrices
   *          the matrices
   * @param xDimension
   *          the {@code x}-dimension
   * @param direction
   *          the iteration direction
   * @param yDimension
   *          the {@code y}-dimension
   */
  static final void _checkMatrices(final IMatrix[] matrices,
      final int xDimension, final EIterationDirection direction,
      final int yDimension) {
    final int maxDim;
    int m;

    MatrixIteration2DSpec._checkMatricesNotNull(matrices);

    maxDim = (Math.max(xDimension, yDimension) + 1);
    if (matrices.length <= 0) {
      throw new IllegalArgumentException(
          "Cannot set matrix array of length 0."); //$NON-NLS-1$
    }
    for (final IMatrix matrix : matrices) {
      if (matrix == null) {
        throw new IllegalArgumentException(
            "No matrix to iterate over must be null."); //$NON-NLS-1$
      }
      if (matrix.n() < maxDim) {
        throw new IllegalArgumentException(
            "Matrix must have at least " + maxDim //$NON-NLS-1$
                + " columns, since x is " + //$NON-NLS-1$
                xDimension + " and y is " + //$NON-NLS-1$
                yDimension + ", but has only " + //$NON-NLS-1$
                matrix.n() + '.');
      }
      m = matrix.m();
      if (m > 1) {
        if (direction._strictlyBeforeDouble(
            matrix.getDouble(m - 1, xDimension), //
            matrix.getDouble(0, xDimension))) {
          throw new IllegalArgumentException("X coordinates should be " //$NON-NLS-1$
              + direction
              + " but are actually not, as the (double) x-coordinate at the matrix start is "//$NON-NLS-1$
              + matrix.getDouble(0, xDimension)
              + " while at the end it is "//$NON-NLS-1$
              + matrix.getDouble(m - 1, xDimension));
        }
      }
    }
  }

  /**
   * check the direction
   *
   * @param direction
   *          the iteration direction
   */
  static final void _checkXDirection(final EIterationDirection direction) {
    if (direction == null) {
      throw new IllegalArgumentException(//
          "Iteration direction must not be null."); //$NON-NLS-1$
    }
  }

  /**
   * check the start mode
   *
   * @param startMode
   *          the start mode
   */
  static final void _checkStartMode(final EMissingValueMode startMode) {
    if (startMode == null) {
      throw new IllegalArgumentException(//
          "Start mode must not be null."); //$NON-NLS-1$
    }
  }

  /**
   * check the end mode
   *
   * @param endMode
   *          the end mode
   */
  static final void _checkEndMode(final EMissingValueMode endMode) {
    if (endMode == null) {
      throw new IllegalArgumentException(//
          "End mode must not be null."); //$NON-NLS-1$
    }
  }

  /**
   * Validate the iteration start
   *
   * @param iterationMode
   *          the iteration mode
   * @param startMode
   *          the start mode
   * @param startReplacement
   *          the start replacement
   */
  static final void _checkStart(final EIterationMode iterationMode,
      final EMissingValueMode startMode, final Number startReplacement) {
    MatrixIteration2DSpec._checkIterationMode(iterationMode);
    MatrixIteration2DSpec._checkStartMode(startMode);

    switch (startMode) {
      case SET_TO_VALUE: {
        if (startReplacement == null) {
          throw new IllegalArgumentException("If start mode is " //$NON-NLS-1$
              + startMode + //
              ", then start replacement number cannot be null."); //$NON-NLS-1$
        }
        return;
      }
      case USE_ITERATION_MODE: {
        if (iterationMode != EIterationMode.PREVIEW_NEXT) {
          throw new IllegalArgumentException("If start mode is "//$NON-NLS-1$
              + startMode + ", then iteration mode cannot be " //$NON-NLS-1$
              + iterationMode + '.');
        }
      }
        //$FALL-THROUGH$
      default: {
        if (startReplacement != null) {
          throw new IllegalArgumentException("If start mode is "//$NON-NLS-1$
              + startMode + //
              ", then start replacement number must not be set, but is " //$NON-NLS-1$
              + startReplacement + '.');
        }
      }
    }
  }

  /**
   * check the iteration mode
   *
   * @param iterationMode
   *          the mode
   */
  static final void _checkIterationMode(
      final EIterationMode iterationMode) {
    if (iterationMode == null) {
      throw new IllegalArgumentException(
          "Cannot set iteration mode to null."); //$NON-NLS-1$
    }
  }

  /**
   * Validate the iteration end
   *
   * @param iterationMode
   *          the iteration mode
   * @param endMode
   *          the end mode
   * @param endReplacement
   *          the end replacement
   */
  static final void _checkEnd(final EIterationMode iterationMode,
      final EMissingValueMode endMode, final Number endReplacement) {
    MatrixIteration2DSpec._checkIterationMode(iterationMode);
    MatrixIteration2DSpec._checkEndMode(endMode);

    switch (endMode) {
      case SET_TO_VALUE: {
        if (endReplacement == null) {
          throw new IllegalArgumentException("If end mode is " //$NON-NLS-1$
              + endMode + //
              ", then end replacement number cannot be null."); //$NON-NLS-1$
        }
        return;
      }
      case USE_ITERATION_MODE: {
        if (iterationMode != EIterationMode.KEEP_PREVIOUS) {
          throw new IllegalArgumentException("If end mode is "//$NON-NLS-1$
              + endMode + ", then iteration mode cannot be " //$NON-NLS-1$
              + iterationMode + '.');
        }
      }
        //$FALL-THROUGH$
      default: {
        if (endReplacement != null) {
          throw new IllegalArgumentException("If end mode is "//$NON-NLS-1$
              + endMode + //
              ", then end replacement number must not be set, but is " //$NON-NLS-1$
              + endReplacement + '.');
        }
      }
    }
  }

  /**
   * Check the {@code x}-dimension
   *
   * @param xDimension
   *          the dimension
   */
  static final void _checkXDimension(final int xDimension) {
    if (xDimension < 0) {
      throw new IllegalArgumentException(//
          "X-dimension cannot be " + xDimension); //$NON-NLS-1$
    }
  }
}
