package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.math.matrix.IMatrix;

/** the direction into which to iterate */
public enum EIterationDirection {

  /** are we iterating into increasing {@code x} direction? */
  INCREASING(true),
  /** are we iterating into decreasing {@code y} direction? */
  DECREASING(false);

  /** the default iteration direction */
  public static final EIterationDirection DEFAULT = INCREASING;

  /** are we increasing or decreasing? */
  private final boolean m_increasing;

  /**
   * create the iteration direction
   *
   * @param increasing
   *          are we increasing?
   */
  EIterationDirection(final boolean increasing) {
    this.m_increasing = increasing;
  }

  /**
   * Set the first {@code x} coordinate as {@code long} value
   *
   * @param impl
   *          the implementation object
   * @return {@code true} if everything went OK and we should continue
   *         visiting elements
   */
  final boolean _setFirstXCoordinateLong(
      final MatrixIteration2DState impl) {
    final boolean increasing;
    boolean hasNot;
    long start, current;
    int index, first;

    increasing = this.m_increasing;
    start = (increasing ? Long.MAX_VALUE : Long.MIN_VALUE);
    hasNot = true;
    for (index = impl.m_matrices.length; (--index) >= 0;) {
      if ((first = impl.m_indexes[index]) < impl.m_end[index]) {
        current = impl.m_matrices[index].getLong(first, impl.m_xDimension);
        if (hasNot
            || (increasing ? (current < start) : (current > start))) {
          start = current;
          hasNot = false;
        }
      }
    }

    if (hasNot) {
      return false;
    }
    return impl._setXCoordinateLong(start);
  }

  /**
   * Set the first {@code x} coordinate as {@code double} value
   *
   * @param impl
   *          the implementation object
   * @return {@code true} if everything went OK and we should continue
   *         visiting elements
   */
  final boolean _setFirstXCoordinateDouble(
      final MatrixIteration2DState impl) {
    final boolean increasing;
    boolean hasNot;
    double start, current;
    int index, first;

    increasing = this.m_increasing;
    start = (increasing ? Double.POSITIVE_INFINITY
        : Double.NEGATIVE_INFINITY);
    hasNot = true;
    for (index = impl.m_matrices.length; (--index) >= 0;) {
      if ((first = impl.m_indexes[index]) < impl.m_end[index]) {
        current = impl.m_matrices[index].getDouble(first,
            impl.m_xDimension);
        if (Double.isNaN(current)) {
          throw new IllegalStateException(//
              "Encountered unexpected NaN on x axis during matrix iteration when trying to find first x value in matrix "//$NON-NLS-1$
                  + index + " in row " + first + //$NON-NLS-1$
                  ". Maybe set skipping leading NaNs to true?");//$NON-NLS-1$
        }
        if (hasNot
            || (increasing ? (current < start) : (current > start))) {
          start = current;
          hasNot = false;
        }
      }
    }

    if (hasNot) {
      return false;
    }
    return impl._setXCoordinateDouble(start);
  }

  /**
   * Strictly compare two {@code longs} and return {@code true} if
   * {@code before} strictly comes before {@code after}.
   *
   * @param before
   *          the {@code long} of which we want to check whether it comes
   *          first
   * @param after
   *          the {@code long} of which we want to check whether it comes
   *          after
   * @return {@code true} if {@code before} strictly comes before
   *         {@code after} in this iteration direction
   */
  final boolean _strictlyBeforeLong(final long before, final long after) {
    return (this.m_increasing ? (before < after) : (before > after));
  }

  /**
   * Strictly compare two {@code doubles} and return {@code true} if
   * {@code before} strictly comes before {@code after}.
   *
   * @param before
   *          the {@code double} of which we want to check whether it comes
   *          first
   * @param after
   *          the {@code double} of which we want to check whether it comes
   *          after
   * @return {@code true} if {@code before} strictly comes before
   *         {@code after} in this iteration direction
   */
  final boolean _strictlyBeforeDouble(final double before,
      final double after) {
    return (this.m_increasing ? (before < after) : (before > after));
  }

  /**
   * set the {@code x} coordinate for a given matrix as {@code long}.
   *
   * @param index
   *          the matrix index
   * @param impl
   *          the matrix iteration implementation
   * @param goalX
   *          the goal {@code x} value
   * @return {@code true} if setting was successful, {@code false} if
   *         nothing was set
   */
  boolean _setXCoordinateForMatrixLong(final int index, final long goalX,
      final MatrixIteration2DState impl) {
    final IMatrix matrix;
    final int origPosition, max;
    final boolean isXIncreasing, checkExactFit;
    long previousX, currentX;
    boolean hadXBefore, returnValue, noExactFit;
    int position;

    matrix = impl.m_matrices[index];
    max = impl.m_end[index];

    if (max <= impl.m_start[index]) {
      return false;
    }

    position = origPosition = impl.m_indexes[index];
    isXIncreasing = this.m_increasing;
    previousX = (isXIncreasing ? Long.MAX_VALUE : Long.MIN_VALUE);
    hadXBefore = false;
    checkExactFit = impl.m_iterationMode.m_useFirstEntry;
    noExactFit = true;

    // Comments here are based on the assumption that direction=increasing
    // There are 4 possible situations:
    // 1. the matrix has an x element equal to goalX
    // 2. the matrix has an x element < goalX and an x element > goalX
    // 3. all matrix elements are > goalX (can only happen at start)
    // 4. the matrix has no x element >= goalX (can only happen at end)
    // 5. we have an exact fit and need to stop at the first exact fit

    // find the largest x value <= goalX
    if (position < max) {
      loop: for (;;) {
        currentX = matrix.getLong(position, impl.m_xDimension);
        if (isXIncreasing ? (currentX > goalX) : (currentX < goalX)) {
          // we arrived at an element greater than goalX
          // this can either be case 1, 2, or 3
          // case 1: position>oldPosition, previousX==goalX,
          // hadXBefore==true, position is 1 too high
          // case 2: position>oldPosition, previousX<goalX,
          // hadXBefore==true, position is 1 too high
          // case 3: position=0, hadXBefore=false
          break loop;
        }
        previousX = currentX;
        hadXBefore = true;
        if (checkExactFit && (currentX == goalX)) {// case 5
          noExactFit = false;
          break loop;
        }
        if ((++position) >= max) {
          break loop;
        }
      }
    }

    // Now we arrive at the following choices
    // case 1: position>oldPosition, previousX==goalX, hadXBefore==true,
    // position is 1 too high
    // case 2: position>oldPosition, previousX<goalX, hadXBefore==true,
    // position is 1 too high, position<max
    // case 3: position==oldPosition==0, hadXBefore=false
    // case 4: otherwise: position>=max
    // case 5: noExactFit=false

    findXValue: {
      if (hadXBefore) {

        if (previousX == goalX) {// case 1 and 5
          if (noExactFit) {
            --position;
          }
          impl._setYCoordinateFromMatrix(index, position);
          returnValue = true;
          break findXValue;
        }

        if (position < max) {
          --position;
          impl.m_iterationMode._handleGoalXBetweenTwoValues(index,
              position, impl);
          returnValue = true;
          break findXValue;
        }

      }

      if (position <= impl.m_start[index]) {
        returnValue = impl.m_startMode._handleMissingStart(index, impl);
        break findXValue;
      }

      returnValue = impl.m_endMode._handleMissingEnd(index, impl);
    }

    if (position > origPosition) {
      impl.m_indexes[index] = position;
    }

    return returnValue;
  }

  /**
   * Set the next {@code x} coordinate as {@code long} value
   *
   * @param impl
   *          the implementation object
   * @return {@code true} if everything went OK and we should continue
   *         visiting elements
   */
  final boolean _setNextXCoordinateLong(
      final MatrixIteration2DState impl) {
    final boolean increasing;
    IMatrix matrix;
    boolean hasNot;
    int index, position, max;
    long forbidden, start, current;

    increasing = this.m_increasing;
    start = forbidden = impl.m_x.longValue();
    hasNot = true;
    outer: for (index = impl.m_matrices.length; (--index) >= 0;) {
      matrix = impl.m_matrices[index];
      max = impl.m_end[index];
      for (position = impl.m_indexes[index]; position < max; position++) {
        current = matrix.getLong(position, impl.m_xDimension);
        if (increasing ? (current > forbidden) : (current < forbidden)) {
          if (hasNot || //
              (increasing ? (current < start) : (current > start))) {
            hasNot = false;
            start = current;
          }
          continue outer;
        }
      }
    }

    if (hasNot) {
      return false;
    }
    return impl._setXCoordinateLong(start);
  }

  /**
   * Set the next {@code x} coordinate as {@code double} value
   *
   * @param impl
   *          the implementation object
   * @return {@code true} if everything went OK and we should continue
   *         visiting elements
   */
  final boolean _setNextXCoordinateDouble(
      final MatrixIteration2DState impl) {
    final boolean increasing;
    IMatrix matrix;
    boolean hasNot;
    int index, position, max;
    double forbidden, start, current;

    increasing = this.m_increasing;
    start = forbidden = impl.m_x.doubleValue();
    hasNot = true;
    outer: for (index = impl.m_matrices.length; (--index) >= 0;) {
      matrix = impl.m_matrices[index];
      max = impl.m_end[index];
      for (position = impl.m_indexes[index]; position < max; position++) {
        current = matrix.getDouble(position, impl.m_xDimension);
        if (Double.isNaN(current)) {
          throw new IllegalStateException(//
              "Encountered unexpected NaN on x axis during matrix iteration in matrix "//$NON-NLS-1$
                  + index + " in row " + position + '.'); //$NON-NLS-1$
        }
        if (increasing ? (current > forbidden) : (current < forbidden)) {
          if (hasNot || //
              (increasing ? (current < start) : (current > start))) {
            hasNot = false;
            start = current;
          }
          continue outer;
        }
      }
    }

    if (hasNot) {
      return false;
    }
    return impl._setXCoordinateDouble(start);
  }

  /**
   * set the {@code x} coordinate for a given matrix as {@code double}.
   *
   * @param index
   *          the matrix index
   * @param impl
   *          the matrix iteration implementation
   * @param goalX
   *          the goal {@code x} value
   * @return {@code true} if setting was successful, {@code false} if
   *         nothing was set
   */
  boolean _setXCoordinateForMatrixDouble(final int index,
      final double goalX, final MatrixIteration2DState impl) {
    final IMatrix matrix;
    final int origPosition, max;
    final boolean isXIncreasing, checkExactFit;
    double previousX, currentX;
    boolean hadXBefore, returnValue, noExactFit;
    int position;

    matrix = impl.m_matrices[index];
    max = impl.m_end[index];

    if (max <= impl.m_start[index]) {
      return false;
    }

    position = origPosition = impl.m_indexes[index];
    isXIncreasing = this.m_increasing;
    previousX = (isXIncreasing ? Double.POSITIVE_INFINITY
        : Double.NEGATIVE_INFINITY);
    hadXBefore = false;
    checkExactFit = impl.m_iterationMode.m_useFirstEntry;
    noExactFit = true;

    // Comments here are based on the assumption that direction=increasing
    // There are 4 possible situations:
    // 1. the matrix has an x element equal to goalX
    // 2. the matrix has an x element < goalX and an x element > goalX
    // 3. all matrix elements are > goalX (can only happen at start)
    // 4. the matrix has no x element >= goalX (can only happen at end)
    // 5. we have an exact fit and need to stop at the first exact fit

    // find the largest x value <= goalX
    if (position < max) {
      loop: for (;;) {
        currentX = matrix.getDouble(position, impl.m_xDimension);
        if (Double.isNaN(currentX)) {
          throw new IllegalStateException(//
              "Encountered a (very!) unexpected NaN on x axis during matrix iteration in matrix "//$NON-NLS-1$
                  + index + " in row " + position + '.'); //$NON-NLS-1$
        }

        if (isXIncreasing ? (currentX > goalX) : (currentX < goalX)) {
          // we arrived at an element greater than goalX
          // this can either be case 1, 2, or 3
          // case 1: position>oldPosition, previousX==goalX,
          // hadXBefore==true, position is 1 too high
          // case 2: position>oldPosition, previousX<goalX,
          // hadXBefore==true, position is 1 too high
          // case 3: position=0, hadXBefore=false
          break loop;
        }
        previousX = currentX;
        hadXBefore = true;
        if (checkExactFit && (currentX == goalX)) {// case 5
          noExactFit = false;
          break loop;
        }
        if ((++position) >= max) {
          break loop;
        }
      }
    }

    // Now we arrive at the following choices
    // case 1: position>oldPosition, previousX==goalX, hadXBefore==true,
    // position is 1 too high
    // case 2: position>oldPosition, previousX<goalX, hadXBefore==true,
    // position is 1 too high, position<max
    // case 3: position==oldPosition==0, hadXBefore=false
    // case 4: otherwise: position>=max
    // case 5: noExactFit=false

    findXValue: {
      if (hadXBefore) {

        if (previousX == goalX) {// case 1 and 5
          if (noExactFit) {
            --position;
          }
          impl._setYCoordinateFromMatrix(index, position);
          returnValue = true;
          break findXValue;
        }

        if (position < max) {
          --position;
          impl.m_iterationMode._handleGoalXBetweenTwoValues(index,
              position, impl);
          returnValue = true;
          break findXValue;
        }

      }

      if (position <= impl.m_start[index]) {
        returnValue = impl.m_startMode._handleMissingStart(index, impl);
        break findXValue;
      }

      returnValue = impl.m_endMode._handleMissingEnd(index, impl);
    }

    if (position > origPosition) {
      impl.m_indexes[index] = position;
    }

    return returnValue;
  }
}