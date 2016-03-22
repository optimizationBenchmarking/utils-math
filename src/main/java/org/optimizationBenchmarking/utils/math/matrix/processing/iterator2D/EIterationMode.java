package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

/** The iteration mode. */
public enum EIterationMode {

  /**
   * if a matrix does not have a {@code y} value for a given {@code x}
   * coordinate, the iteration should simply return the {@code y} value of
   * the next {@code x} coordinate
   */
  PREVIEW_NEXT {
    /** {@inheritDoc} */
    @Override
    final void _handleGoalXBetweenTwoValues(final int index,
        final int positionOfSmallerX, final _MatrixIteration2DImpl impl) {
      impl._setYCoordinateFromMatrix(index, positionOfSmallerX + 1);
    }
  },
  /**
   * if a matrix does not have a {@code y} value for a given {@code x}
   * coordinate, the iteration should simply return the {@code y} value of
   * the previous {@code x} coordinate
   */
  KEEP_PREVIOUS {
    /** {@inheritDoc} */
    @Override
    final void _handleGoalXBetweenTwoValues(final int index,
        final int positionOfSmallerX, final _MatrixIteration2DImpl impl) {
      impl._setYCoordinateFromMatrix(index, positionOfSmallerX);
    }
  };

  /** the default iteration mode */
  public static final EIterationMode DEFAULT = KEEP_PREVIOUS;

  /**
   * Handle the situation where the goal {@code x} value for the given
   * matrix is between two existing values.
   *
   * @param index
   *          the index
   * @param positionOfSmallerX
   *          the position of the smaller {@code x}
   * @param impl
   *          the iteration implementation
   */
  abstract void _handleGoalXBetweenTwoValues(final int index,
      final int positionOfSmallerX, final _MatrixIteration2DImpl impl);
}
