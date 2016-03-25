package org.optimizationBenchmarking.utils.math.matrix.impl;

import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.NumericalTypes;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;

/**
 * A utility class for building a matrix which has two columns, one for
 * {@code x} and one for {@code y}. Each row represents a point. In other
 * words, once the matrix is built, it resembles a function. If the
 * function is to be plotted as stair function, you may set
 * {@link #m_onlyOnePointPerYCoordinate} to {@code true}.
 */
public final class MatrixFunctionBuilder {

  /** the builder */
  private MatrixBuilder m_builder;

  /**
   * print only one point per {@code y} coordinate and omit all following
   * points with the same {@code y} value (except the very last point added
   * to the function)
   */
  private final boolean m_onlyOnePointPerYCoordinate;

  /**
   * the state
   * <ul>
   * <li>0: beginning</li>
   * <li>1: last point was added to the function</li>
   * <li>2: last point was not added to the function</li>
   * </ul>
   */
  private int m_state;

  /** the last {@code x} coordinate as {@code long} value */
  private long m_lastXLong;
  /** the last {@code x} coordinate as {@code double} value */
  private double m_lastXDouble;

  /** the last {@code y} coordinate as {@code long} value */
  private long m_lastYLong;
  /** the last {@code y} coordinate as {@code double} value */
  private double m_lastYDouble;

  /**
   * Create the function builder, i.e., an object that can help you
   * represent a mathematical function as 2D matrix.
   *
   * @param expectedSize
   *          the expected size of the resulting matrix
   * @param onlyOnePointPerYCoordinate
   *          print only one point per {@code y} coordinate and omit all
   *          following points with the same {@code y} value (except the
   *          very last point added to the function)
   */
  public MatrixFunctionBuilder(final int expectedSize,
      final boolean onlyOnePointPerYCoordinate) {
    super();
    this.m_builder = new MatrixBuilder(expectedSize * 2);
    this.m_builder.setN(2);
    this.m_onlyOnePointPerYCoordinate = onlyOnePointPerYCoordinate;
  }

  /**
   * Create the function builder, i.e., an object that can help you
   * represent a mathematical function as 2D matrix.
   *
   * @param onlyOnePointPerYCoordinate
   *          print only one point per {@code y} coordinate and omit all
   *          following points with the same {@code y} value (except the
   *          very last point added to the function)
   */
  public MatrixFunctionBuilder(final boolean onlyOnePointPerYCoordinate) {
    this(-1, onlyOnePointPerYCoordinate);
  }

  /**
   * Add two long integer coordinates
   *
   * @param x
   *          the {@code x} coordinate
   * @param y
   *          the {@code y} coordinate
   */
  private final void __addPointLong(final long x, final long y) {
    if (this.m_state != 0) {
      if (y == this.m_lastYLong) {
        if (x == this.m_lastXLong) {
          return;
        }
        this.m_lastXLong = x;
        this.m_state = 2;
        return;
      }

      if ((this.m_state == 2) && (!(this.m_onlyOnePointPerYCoordinate))) {
        this.m_builder.append(this.m_lastXLong);
        this.m_builder.append(this.m_lastYLong);
      }
    }
    this.m_builder.append(x);
    this.m_builder.append(y);
    this.m_lastXLong = x;
    this.m_lastYLong = y;
    this.m_state = 1;
  }

  /**
   * Add two double coordinates
   *
   * @param x
   *          the {@code x} coordinate
   * @param y
   *          the {@code y} coordinate
   */
  private final void __addPointDouble(final double x, final double y) {
    if (this.m_state != 0) {
      if (y == this.m_lastYDouble) {
        if (x == this.m_lastXDouble) {
          return;
        }
        this.m_lastXDouble = x;
        this.m_state = 2;
        return;
      }

      if ((this.m_state == 2) && (!(this.m_onlyOnePointPerYCoordinate))) {
        this.m_builder.append(this.m_lastXDouble);
        this.m_builder.append(this.m_lastYDouble);
      }
    }
    this.m_builder.append(x);
    this.m_builder.append(y);
    this.m_lastXDouble = x;
    this.m_lastYDouble = y;
    this.m_state = 1;
  }

  /**
   * Add two long integer coordinates
   *
   * @param x
   *          the {@code x} coordinate
   * @param y
   *          the {@code y} coordinate
   */
  public final void addPoint(final long x, final long y) {
    if (this.m_builder.isBackingStoreInteger()) {
      this.__addPointLong(x, y);
    } else {
      this.__addPointDouble(x, y);
    }
  }

  /**
   * Add two coordinates
   *
   * @param x
   *          the {@code x} coordinate
   * @param y
   *          the {@code y} coordinate
   */
  public final void addPoint(final long x, final double y) {
    if (this.m_builder.isBackingStoreInteger()) {
      if (NumericalTypes.isLong(y)) {
        this.__addPointLong(x, ((long) y));
        return;
      }
      this.m_lastXDouble = this.m_lastXLong;
      this.m_lastYDouble = this.m_lastYLong;
    }
    this.__addPointDouble(x, y);
  }

  /**
   * Add two coordinates
   *
   * @param x
   *          the {@code x} coordinate
   * @param y
   *          the {@code y} coordinate
   */
  public final void addPoint(final double x, final long y) {
    if (this.m_builder.isBackingStoreInteger()) {
      if (NumericalTypes.isLong(x)) {
        this.__addPointLong(((long) x), y);
        return;
      }
      this.m_lastXDouble = this.m_lastXLong;
      this.m_lastYDouble = this.m_lastYLong;
    }
    this.__addPointDouble(x, y);
  }

  /**
   * Add two double coordinates
   *
   * @param x
   *          the {@code x} coordinate
   * @param y
   *          the {@code y} coordinate
   */
  public final void addPoint(final double x, final double y) {
    if (this.m_builder.isBackingStoreInteger()) {
      if (NumericalTypes.isLong(x) && NumericalTypes.isLong(y)) {
        this.__addPointLong(((long) x), ((long) y));
        return;
      }
      this.m_lastXDouble = this.m_lastXLong;
      this.m_lastYDouble = this.m_lastYLong;
    }
    this.__addPointDouble(x, y);
  }

  /**
   * Add a point
   *
   * @param x
   *          the {@code x} coordinate
   * @param y
   *          the {@code y} coordinate
   */
  public final void addPoint(final BasicNumber x, final BasicNumber y) {
    if (this.m_builder.isBackingStoreInteger()) {
      if (x.isInteger() && y.isInteger()) {
        this.__addPointLong(x.longValue(), y.longValue());
        return;
      }
      this.m_lastXDouble = this.m_lastXLong;
      this.m_lastYDouble = this.m_lastYLong;
    }
    this.__addPointDouble(x.doubleValue(), y.doubleValue());
  }

  /**
   * build the function
   *
   * @return the function matrix
   */
  public final AbstractMatrix build() {
    final AbstractMatrix result;

    if (this.m_state == 2) {
      if (this.m_builder.isBackingStoreInteger()) {
        this.m_builder.append(this.m_lastXLong);
        this.m_builder.append(this.m_lastYLong);
      } else {
        this.m_builder.append(this.m_lastXDouble);
        this.m_builder.append(this.m_lastYDouble);
      }
    }

    result = this.m_builder.make();
    this.m_builder = null;

    return result;
  }
}
