package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;

/** the base class for actual matrix iteration implementations */
class _MatrixIteration2DImpl extends MatrixIteration2DState {

  /** the indexes */
  final int[] m_indexes;

  /** the sources */
  final int[] m_sources;

  /** the {@code x}-coordinate */
  final _Number m_x;

  /** the {@code y}-coordinates */
  final _Matrix m_y;

  /** the {@code x}-coordinates are integers */
  final boolean m_xIsInteger;
  /** the {@code y}-coordinates are integers */
  final boolean m_yIsInteger;

  /**
   * create the matrix iteration 2d
   *
   * @param iteration
   *          the iteration
   * @param xIsInteger
   *          are the {@code x}-coordinates integers?
   * @param yIsInteger
   *          are the {@code y}-coordinates integers?
   */
  _MatrixIteration2DImpl(final _MatrixIteration2DBase iteration,
      final boolean xIsInteger, final boolean yIsInteger) {
    super(iteration);
    final int length;

    length = this.m_matrices.length;
    this.m_indexes = new int[length];
    this.m_sources = new int[length];
    this.m_xIsInteger = xIsInteger;
    this.m_x = (this.m_xIsInteger ? new _Long() : new _Double());
    this.m_yIsInteger = yIsInteger;
    this.m_y = (this.m_yIsInteger ? new _Longs(length)
        : new _Doubles(length));
  }

  /** {@inheritDoc} */
  @Override
  public final BasicNumber getX() {
    return this.m_x;
  }

  /** {@inheritDoc} */
  @Override
  public final AbstractMatrix getY() {
    return this.m_y;
  }

  /** {@inheritDoc} */
  @Override
  public final int getSourceMatrixIndex(final int yIndexN) {
    if ((yIndexN >= 0) && (yIndexN < this.m_y.m_currentN)) {
      return this.m_sources[yIndexN];
    }
    throw new IllegalArgumentException(//
        "Only " + this.m_y.m_currentN + //$NON-NLS-1$
            " matrices have corresponding y values for x-coordinate "//$NON-NLS-1$
            + this.m_x + ", so index " + yIndexN + //$NON-NLS-1$
            " is invalid."); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final int getSourceMatrixCount() {
    return this.m_y.m_currentN;
  }

  /** {@inheritDoc} */
  @Override
  public final IMatrix getSourceMatrix(final int yIndexN) {
    return this.m_matrices[this.getSourceMatrixIndex(yIndexN)];
  }

  /** run! */
  final void _run() {
    this.m_y._reset();

    if (this.m_xIsInteger) {

      if (this.m_xDirection._setFirstXCoordinateLong(this)) {
        for (;;) {
          this.m_y._reset();
          if (!(this.m_xDirection._setNextXCoordinateLong(this))) {
            return;
          }
        }
      }
    } else {
      if (this.m_xDirection._setFirstXCoordinateDouble(this)) {
        for (;;) {
          this.m_y._reset();
          if (!(this.m_xDirection._setNextXCoordinateDouble(this))) {
            return;
          }
        }
      }
    }
  }

  /**
   * Check a {@code double} and throw an exception if we encounter NaN
   *
   * @param d
   *          the double
   * @return {@code d}
   */
  static final double _d(final double d) {
    if (d != d) {
      throw new IllegalArgumentException("Encountered nan!"); //$NON-NLS-1$
    }
    return d;
  }

  /**
   * set the {@code x} coordinate as {@code long} value.
   *
   * @param value
   *          the coordinate value
   * @return {@code true} if everything went OK and we should continue
   *         visiting elements
   */
  final boolean _setXCoordinateLong(final long value) {
    boolean has;
    int index;

    has = false;
    for (index = 0; index < this.m_matrices.length; index++) {
      if (this.m_xDirection._setXCoordinateForMatrixLong(index, value,
          this)) {
        has = true;
      }
    }

    if (has) {
      this.m_x._setLongValue(value);
      return this.m_visitor.visit(this);
    }

    return false;
  }

  /**
   * Set the {@code y} coordinate for the matrix at the given {@code index}
   * to the given {@code value}.
   *
   * @param index
   *          the index
   * @param value
   *          the value
   */
  private final void __setYCoordinateLong(final int index,
      final long value) {
    this.m_sources[this.m_y._addLong(value)] = index;
  }

  /**
   * Set the {@code y} coordinate for the matrix at the given {@code index}
   * to the given {@code value}.
   *
   * @param index
   *          the index
   * @param value
   *          the value
   */
  private final void __setYCoordinateDouble(final int index,
      final double value) {
    this.m_sources[this.m_y._addDouble(value)] = index;
  }

  /**
   * Set the {@code y} coordinate for the matrix at the given {@code index}
   * from the given {@code position}.
   *
   * @param index
   *          the index
   * @param position
   *          the position
   */
  final void _setYCoordinateFromMatrix(final int index,
      final int position) {
    final IMatrix sourceMatrix;

    sourceMatrix = this.m_matrices[index];
    if (this.m_yIsInteger) {
      this.__setYCoordinateLong(index, //
          sourceMatrix.getLong(position, this.m_yDimension));
    } else {
      this.__setYCoordinateDouble(index, //
          _MatrixIteration2DImpl
              ._d(sourceMatrix.getDouble(position, this.m_yDimension)));
    }
  }

  /**
   * Set the {@code y} coordinate for the matrix at the given {@code index}
   * from the given {@code number}.
   *
   * @param index
   *          the index
   * @param number
   *          the number
   */
  final void _setYCoordinateFromNumber(final int index,
      final Number number) {

    if (this.m_yIsInteger) {
      this.__setYCoordinateLong(index, number.longValue());
    } else {
      this.__setYCoordinateDouble(index, number.doubleValue());
    }
  }

  /**
   * set the {@code x} coordinate as {@code double} value.
   *
   * @param value
   *          the coordinate value
   * @return {@code true} if everything went OK and we should continue
   *         visiting elements
   */
  boolean _setXCoordinateDouble(final double value) {
    boolean has;
    int index;

    has = false;
    for (index = this.m_matrices.length; (--index) >= 0;) {
      if (this.m_xDirection._setXCoordinateForMatrixDouble(index, value,
          this)) {
        has = true;
      }
    }

    if (has) {
      this.m_x._setDoubleValue(value);
      return this.m_visitor.visit(this);
    }

    return false;
  }
}
