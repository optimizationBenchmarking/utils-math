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
      final _MatrixIteration2DImpl impl) {
    final boolean increasing;
    boolean hasNot;
    long start, current;

    increasing = this.m_increasing;
    start = (increasing ? Long.MAX_VALUE : Long.MIN_VALUE);
    hasNot = true;
    for (final IMatrix matrix : impl.m_matrices) {
      if (matrix.m() > 0) {
        current = matrix.getLong(0, impl.m_xDimension);
        if (hasNot
            || (increasing ? (start < current) : (start > current))) {
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
      final _MatrixIteration2DImpl impl) {
    final boolean increasing;
    boolean hasNot;
    double start, current;

    increasing = this.m_increasing;
    start = (increasing ? Double.POSITIVE_INFINITY
        : Double.NEGATIVE_INFINITY);
    hasNot = true;
    for (final IMatrix matrix : impl.m_matrices) {
      if (matrix.m() > 0) {
        current = _MatrixIteration2DImpl
            ._d(matrix.getDouble(0, impl.m_xDimension));
        if (hasNot
            || (increasing ? (start < current) : (start > current))) {
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
      final _MatrixIteration2DImpl impl) {
    final IMatrix matrix;
    final int origPosition, max;
    final boolean isXIncreasing;
    long previousX, currentX;
    boolean hadXBefore, returnValue;
    int position;

    matrix = impl.m_matrices[index];
    max = matrix.m();

    if (max <= 0) {
      return false;
    }

    position = origPosition = impl.m_indexes[index];
    isXIncreasing = this.m_increasing;
    previousX = (isXIncreasing ? Long.MAX_VALUE : Long.MIN_VALUE);
    hadXBefore = false;

    // Comments here are based on the assumption that direction=increasing
    // There are 4 possible situations:
    // 1. the matrix has an x element equal to goalX
    // 2. the matrix has an x element < goalX and an x element > goalX
    // 3. all matrix elements are > goalX (can only happen at start)
    // 4. the matrix has no x element >= goalX (can only happen at end)

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

    findXValue: {
      if (hadXBefore) {

        if (previousX == goalX) {// case 1
          --position;
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

      if (position <= 0) {
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
      final _MatrixIteration2DImpl impl) {
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
      max = matrix.m();
      for (position = impl.m_indexes[index]; position < max; position++) {
        current = matrix.getLong(position, impl.m_xDimension);
        if (increasing ? (forbidden < current) : (forbidden > current)) {
          if (hasNot || //
              (increasing ? (start > current) : (start < current))) {
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
      final _MatrixIteration2DImpl impl) {
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
      max = matrix.m();
      for (position = impl.m_indexes[index]; position < max; position++) {
        current = _MatrixIteration2DImpl._d(//
            matrix.getDouble(position, impl.m_xDimension));
        if (increasing ? (forbidden < current) : (forbidden > current)) {
          if (hasNot || //
              (increasing ? (start > current) : (start < current))) {
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
      final double goalX, final _MatrixIteration2DImpl impl) {
    final IMatrix matrix;
    final int origPosition, max;
    final boolean isXIncreasing;
    double previousX, currentX;
    boolean hadXBefore, returnValue;
    int position;

    matrix = impl.m_matrices[index];
    max = matrix.m();

    if (max <= 0) {
      return false;
    }

    position = origPosition = impl.m_indexes[index];
    isXIncreasing = this.m_increasing;
    previousX = (isXIncreasing ? Double.POSITIVE_INFINITY
        : Double.NEGATIVE_INFINITY);
    hadXBefore = false;

    // Comments here are based on the assumption that direction=increasing
    // There are 4 possible situations:
    // 1. the matrix has an x element equal to goalX
    // 2. the matrix has an x element < goalX and an x element > goalX
    // 3. all matrix elements are > goalX (can only happen at start)
    // 4. the matrix has no x element >= goalX (can only happen at end)

    // find the largest x value <= goalX
    if (position < max) {
      loop: for (;;) {
        currentX = _MatrixIteration2DImpl
            ._d(matrix.getDouble(position, impl.m_xDimension));
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

    findXValue: {
      if (hadXBefore) {

        if (previousX == goalX) {// case 1
          --position;
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

      if (position <= 0) {
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