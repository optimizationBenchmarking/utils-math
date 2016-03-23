package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

/** The iteration mode. */
public enum EIterationMode {

  /**
   * This indicates a so-to-say right-conservative mode:
   * <ul>
   * <li>If a matrix does not have a {@code y} value for a given {@code x}
   * coordinate, the iteration should simply return the {@code y} value of
   * the next {@code x} coordinate.</li>
   * <li>If there are multiple values for a given {@code x} coordinate, we
   * take the first one.</li>
   * </ul>
   * Imagine something like iterating over objective values as {@code x}
   * and time as {@code y} of an optimization process. If you look for the
   * time you need to find a given objective value, you will choose the
   * earliest (first) time entry for the objective value. If no entry for
   * the objective value exist, you need to take the time for the
   * next-better objective value.
   */
  PREVIEW_NEXT(true) {
    /** {@inheritDoc} */
    @Override
    final void _handleGoalXBetweenTwoValues(final int index,
        final int positionOfSmallerX, final _MatrixIteration2DImpl impl) {
      impl._setYCoordinateFromMatrix(index, positionOfSmallerX + 1);
    }
  },
  /**
   * This indicates a so-to-say left-conservative mode:
   * <ul>
   * <li>If a matrix does not have a {@code y} value for a given {@code x}
   * coordinate, the iteration should simply return the {@code y} value of
   * the previous {@code x} coordinate.</li>
   * <li>If a matrix has multiple {@code y} values for a given {@code x}
   * coordinate, we take the last (latest) one.</li></li>
   * </ul>
   * Imagine something like iterating over time values as {@code x} and
   * corresponding objective values as {@code y} in an optimization
   * process. If there is no objective value for a given time value, we
   * have to assume that the optimization process did not discover anything
   * better than the previous objective value by then. If we have multiple
   * objective values entries for the same time value, we can assume the
   * best (latest) one.
   */
  KEEP_PREVIOUS(false) {
    /** {@inheritDoc} */
    @Override
    final void _handleGoalXBetweenTwoValues(final int index,
        final int positionOfSmallerX, final _MatrixIteration2DImpl impl) {
      impl._setYCoordinateFromMatrix(index, positionOfSmallerX);
    }
  };

  /**
   * should we use the first objective value entry for a given time value?
   */
  final boolean m_useFirstEntry;

  /**
   * create
   *
   * @param useFirstEntry
   *          should we use the first objective value entry for a given
   *          time value?
   */
  EIterationMode(final boolean useFirstEntry) {
    this.m_useFirstEntry = useFirstEntry;
  }

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
