package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

/**
 * This enumeration describes how to deal with missing values. Missing
 * values may either occur at the start or the end of the iteration.
 */
public enum EMissingValueMode {
  /**
   * the matrix with the missing value is simply skipped, i.e., won't be
   * present in the iteration.
   */
  SKIP {
    /** {@inheritDoc} */
    @Override
    final boolean _handleMissingStart(final int index,
        final _MatrixIteration2DImpl impl) {
      return false;
    }

    /** {@inheritDoc} */
    @Override
    final boolean _handleMissingEnd(final int index,
        final _MatrixIteration2DImpl impl) {
      return false;
    }
  },
  /**
   * strictly comply with the specified {@linkplain EIterationMode
   * iteration mode}: this can only be done for
   * {@link EIterationMode#PREVIEW_NEXT} at the start and
   * {@link EIterationMode#KEEP_PREVIOUS} at the end
   */
  USE_ITERATION_MODE {
    /** {@inheritDoc} */
    @Override
    final boolean _handleMissingStart(final int index,
        final _MatrixIteration2DImpl impl) {
      impl._setYCoordinateFromMatrix(index, 0);// must be preview next
      return true;
    }

    /** {@inheritDoc} */
    @Override
    final boolean _handleMissingEnd(final int index,
        final _MatrixIteration2DImpl impl) {
      impl._setYCoordinateFromMatrix(index, //
          impl.m_matrices[index].m() - 1);// must be keep previous
      return true;
    }
  },

  /** set the returned value to a specific value. */
  SET_TO_VALUE {
    /** {@inheritDoc} */
    @Override
    final boolean _handleMissingStart(final int index,
        final _MatrixIteration2DImpl impl) {
      impl._setYCoordinateFromNumber(index, impl.m_startReplacement);
      return true;
    }

    /** {@inheritDoc} */
    @Override
    final boolean _handleMissingEnd(final int index,
        final _MatrixIteration2DImpl impl) {
      impl._setYCoordinateFromNumber(index, impl.m_endReplacement);
      return true;
    }
  };

  /** the default start mode */
  public static final EMissingValueMode DEFAULT_START_MODE = SKIP;
  /** the default end mode */
  public static final EMissingValueMode DEFAULT_END_MODE = USE_ITERATION_MODE;

  /**
   * Handle the situation where all {@code x} elements are larger than the
   * specified goal value, i.e., a start value is missing
   *
   * @param index
   *          the index
   * @param impl
   *          the iteration implementation
   * @return {@code true} if an element was added to the result matrix,
   *         {@code false} otherwise
   */
  abstract boolean _handleMissingStart(final int index,
      final _MatrixIteration2DImpl impl);

  /**
   * Handle the situation where all {@code x} elements are smaller than the
   * specified goal value, i.e., a end value is missing
   *
   * @param index
   *          the index
   * @param impl
   *          the iteration implementation
   * @return {@code true} if an element was added to the result matrix,
   *         {@code false} otherwise
   */
  abstract boolean _handleMissingEnd(final int index,
      final _MatrixIteration2DImpl impl);
}