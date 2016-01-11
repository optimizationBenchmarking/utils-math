package org.optimizationBenchmarking.utils.math.matrix.impl;

import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.NumericalTypes;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.IAggregate;
import org.optimizationBenchmarking.utils.reflection.EPrimitiveType;

/** A builder for arrays of numerical types. */
public class ArrayBasedMatrixBuilder implements IAggregate {

  /** the default matrix type */
  static final EPrimitiveType DEFAULT_MATRIX_TYPE = EPrimitiveType.INT;

  /** the size */
  private int m_size;

  /** the maximum allowed size */
  private int m_maxSize;

  /** the internal array */
  private _Array m_array;

  /**
   * create the array-based matrix builder
   *
   * @param expectedType
   *          the expected numerical type
   * @param expectedSize
   *          the expected size, {@code -1} for default
   */
  protected ArrayBasedMatrixBuilder(final EPrimitiveType expectedType,
      final int expectedSize) {
    super();

    final int size;

    this.m_maxSize = Integer.MAX_VALUE;

    size = ((expectedSize <= 0) ? 64 : expectedSize);

    switch (expectedType) {
      case BYTE: {
        this.m_array = new _ByteArray(new byte[size]);
        return;
      }
      case SHORT: {
        this.m_array = new _ShortArray(new short[size]);
        return;
      }
      case INT: {
        this.m_array = new _IntArray(new int[size]);
        return;
      }
      case LONG: {
        this.m_array = new _LongArray(new long[size]);
        return;
      }
      case FLOAT: {
        this.m_array = new _FloatArray(new float[size]);
        return;
      }
      case DOUBLE: {
        this.m_array = new _DoubleArray(new double[size]);
        return;
      }
      default: {
        throw new IllegalArgumentException(//
            "Unsupported matrix element type: " + //$NON-NLS-1$
                expectedType);
      }
    }
  }

  /**
   * create the array-based matrix builder
   *
   * @param expectedSize
   *          the expected size
   */
  public ArrayBasedMatrixBuilder(final int expectedSize) {
    this(ArrayBasedMatrixBuilder.DEFAULT_MATRIX_TYPE, expectedSize);
  }

  /** create the array-based matrix builder */
  public ArrayBasedMatrixBuilder() {
    this(-1);
  }

  /**
   * create the array-based matrix builder
   *
   * @param expectedType
   *          the expected numerical type
   */
  public ArrayBasedMatrixBuilder(final EPrimitiveType expectedType) {
    this(expectedType, (-1));
  }

  /**
   * Increase the size by {@code 1}
   *
   * @return the size
   */
  private final int __incSize() {
    final int s;
    if ((s = this.m_size) >= this.m_maxSize) {
      throw new IllegalStateException("Already reached maximum size " + s); //$NON-NLS-1$
    }
    this.m_size = (s + 1);
    return s;
  }

  /**
   * Get the current number of added elements
   *
   * @return the current number of added elements
   */
  protected final int getCurrentSize() {
    return this.m_size;
  }

  /**
   * Get the maximum number of added elements
   *
   * @return the maximum number of added elements
   */
  protected final int getMaximumSize() {
    return this.m_maxSize;
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final byte v) {
    this.m_array = this.m_array._append(v, this.__incSize());
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final short v) {
    this.m_array = this.m_array._append(v, this.__incSize());
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final int v) {
    this.m_array = this.m_array._append(v, this.__incSize());
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final long v) {
    this.m_array = this.m_array._append(v, this.__incSize());
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final float v) {
    this.m_array = this.m_array._append(v, this.__incSize());
  }

  /** {@inheritDoc} */
  @Override
  public final void append(final double v) {
    this.m_array = this.m_array._append(v, this.__incSize());
  }

  /**
   * Append a given string
   *
   * @param str
   *          the string
   */
  @SuppressWarnings("unused")
  public final void append(final String str) {
    try {
      this.append(Long.parseLong(str));
    } catch (final NumberFormatException nfe) {
      this.append(Double.parseDouble(str));
    }
  }

  /**
   * Append a basic number
   *
   * @param number
   *          the number
   */
  public final void append(final BasicNumber number) {
    if (number == null) {
      throw new IllegalArgumentException(//
          "Cannot append a null number."); //$NON-NLS-1$
    }
    switch (number.getState()) {
      case BasicNumber.STATE_EMPTY: {
        throw new IllegalArgumentException(//
            "Cannot append an empty number."); //$NON-NLS-1$
      }
      case BasicNumber.STATE_INTEGER: {
        this.append(number.longValue());
        return;
      }
      default: {
        this.append(number.doubleValue());
      }
    }
  }

  /**
   * Make a matrix based upon {@code byte} data.
   *
   * @param data
   *          the data array
   * @return the matrix
   */
  protected AbstractMatrix make(final byte[] data) {
    throw new UnsupportedOperationException(//
        this.getClass().getSimpleName() + //
            " does not support building matrices based on byte[] data."); //$NON-NLS-1$
  }

  /**
   * Make a matrix based upon {@code short} data.
   *
   * @param data
   *          the data array
   * @return the matrix
   */
  protected AbstractMatrix make(final short[] data) {
    throw new UnsupportedOperationException(//
        this.getClass().getSimpleName() + //
            " does not support building matrices based on short[] data."); //$NON-NLS-1$
  }

  /**
   * Make a matrix based upon {@code int} data.
   *
   * @param data
   *          the data array
   * @return the matrix
   */
  protected AbstractMatrix make(final int[] data) {
    throw new UnsupportedOperationException(//
        this.getClass().getSimpleName() + //
            " does not support building matrices based on int[] data."); //$NON-NLS-1$
  }

  /**
   * Make a matrix based upon {@code long} data.
   *
   * @param data
   *          the data array
   * @return the matrix
   */
  protected AbstractMatrix make(final long[] data) {
    throw new UnsupportedOperationException(//
        this.getClass().getSimpleName() + //
            " does not support building matrices based on long[] data."); //$NON-NLS-1$
  }

  /**
   * Make a matrix based upon {@code float} data.
   *
   * @param data
   *          the data array
   * @return the matrix
   */
  protected AbstractMatrix make(final float[] data) {
    throw new UnsupportedOperationException(//
        this.getClass().getSimpleName() + //
            " does not support building matrices based on float[] data."); //$NON-NLS-1$
  }

  /**
   * Make a matrix based upon {@code double} data.
   *
   * @param data
   *          the data array
   * @return the matrix
   */
  protected AbstractMatrix make(final double[] data) {
    throw new UnsupportedOperationException(//
        this.getClass().getSimpleName() + //
            " does not support building matrices based on double[] data."); //$NON-NLS-1$
  }

  /**
   * set the maximum size
   *
   * @param maxSize
   *          the maximum size
   */
  protected void setMaxSize(final int maxSize) {
    if (maxSize < this.m_size) {
      throw new IllegalArgumentException(//
          "Maximum number of matrix elements cannot be less than the number of already-added elements (" //$NON-NLS-1$
              + this.m_size + "), but is " + maxSize);//$NON-NLS-1$
    }
    this.m_maxSize = maxSize;
    this.m_array._setMaxSize(this.m_size, maxSize);
  }

  /**
   * Make the matrix
   *
   * @return the matrix
   */
  public AbstractMatrix make() {
    final _Array array;
    array = this.m_array;
    this.m_array = null;
    return array._make(this.m_size);
  }

  /**
   * Append a series of {@code double}s
   *
   * @param data
   *          the series of {@code double}s
   */
  public final void append(final double[] data) {
    for (final double x : data) {
      this.append(x);
    }
  }

  /**
   * Append a series of {@code long}s
   *
   * @param data
   *          the series of {@code long}s
   */
  public final void append(final long[] data) {
    for (final long x : data) {
      this.append(x);
    }
  }

  /** the internal array class */
  abstract class _Array {

    /**
     * add a byte
     *
     * @param v
     *          the byte
     * @param size
     *          the size
     * @return an array
     */
    abstract _Array _append(byte v, final int size);

    /**
     * add a short
     *
     * @param v
     *          the short
     * @param size
     *          the size
     * @return an array
     */
    abstract _Array _append(short v, final int size);

    /**
     * add a int
     *
     * @param v
     *          the int
     * @param size
     *          the size
     * @return an array
     */
    abstract _Array _append(int v, final int size);

    /**
     * add a long
     *
     * @param v
     *          the long
     * @param size
     *          the size
     * @return an array
     */
    abstract _Array _append(long v, final int size);

    /**
     * add a float
     *
     * @param v
     *          the float
     * @param size
     *          the size
     * @return an array
     */
    abstract _Array _append(float v, final int size);

    /**
     * add a double
     *
     * @param v
     *          the double
     * @param size
     *          the size
     * @return an array
     */
    abstract _Array _append(double v, final int size);

    /**
     * make the matrix
     *
     * @param size
     *          the matrix size
     * @return the matrix
     */
    abstract AbstractMatrix _make(final int size);

    /**
     * set the maximum size
     *
     * @param curSize
     *          the current size
     * @param maxSize
     *          the maximum size
     */
    abstract void _setMaxSize(final int curSize, final int maxSize);
  }

  /** the internal growable double array */
  private final class _DoubleArray extends _Array {

    /** the double data */
    private double[] m_data;

    /**
     * create the array
     *
     * @param data
     *          the data
     */
    _DoubleArray(final double[] data) {
      this.m_data = data;
    }

    /** {@inheritDoc} */
    @Override
    final void _setMaxSize(final int curSize, final int maxSize) {
      double[] data;
      if (this.m_data.length != maxSize) {
        data = new double[maxSize];
        System.arraycopy(this.m_data, 0, data, 0, curSize);
        this.m_data = data;
      }
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final byte v, final int size) {
      return this._append(((double) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final short v, final int size) {
      return this._append(((double) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final int v, final int size) {
      return this._append(((double) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final long v, final int size) {
      return this._append(((double) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final float v, final int size) {
      return this._append(((double) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final double v, final int size) {
      double[] data;

      data = this.m_data;
      if (data.length <= size) {
        data = new double[(size + 16) << 1];
        System.arraycopy(this.m_data, 0, data, 0, size);
        this.m_data = data;
      }
      data[size] = v;
      return this;
    }

    /** {@inheritDoc} */
    @Override
    final AbstractMatrix _make(final int size) {
      double[] data;

      data = this.m_data;
      if (data.length != size) {
        data = new double[size];
        System.arraycopy(this.m_data, 0, data, 0, size);
      }
      return ArrayBasedMatrixBuilder.this.make(data);
    }
  }

  /** the internal growable long array */
  private final class _LongArray extends _Array {

    /** the long data */
    private long[] m_data;

    /**
     * create the array
     *
     * @param data
     *          the data
     */
    _LongArray(final long[] data) {
      this.m_data = data;
    }

    /** {@inheritDoc} */
    @Override
    final void _setMaxSize(final int curSize, final int maxSize) {
      long[] data;
      if (this.m_data.length != maxSize) {
        data = new long[maxSize];
        System.arraycopy(this.m_data, 0, data, 0, curSize);
        this.m_data = data;
      }
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final byte v, final int size) {
      return this._append(((long) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final short v, final int size) {
      return this._append(((long) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final int v, final int size) {
      return this._append(((long) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final long v, final int size) {
      long[] data;

      data = this.m_data;
      if (data.length <= size) {
        data = new long[(size + 16) << 1];
        System.arraycopy(this.m_data, 0, data, 0, size);
        this.m_data = data;
      }
      data[size] = v;
      return this;
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final float v, final int size) {
      final long[] longData;
      final double[] doubleData;
      float[] floatData;
      long l;
      int i;

      if ((NumericalTypes.getTypes(v) & NumericalTypes.IS_LONG) != 0) {
        return this._append(((long) v), size);
      }

      longData = this.m_data;
      floatData = new float[(size < longData.length) ? longData.length
          : ((size + 16) << 1)];
      floatData[size] = v;
      tryFloats: {
        for (i = size; (--i) >= 0;) {
          l = longData[i];
          if ((NumericalTypes.getBestFloatingPointRepresentation(l) & //
              NumericalTypes.IS_FLOAT) == 0) {
            break tryFloats;
          }
          floatData[i] = l;
        }
        return new _FloatArray(floatData);
      }

      doubleData = new double[floatData.length];
      floatData = null;
      doubleData[size] = v;
      for (i = size; (--i) >= 0;) {
        doubleData[i] = longData[i];
      }
      return new _DoubleArray(doubleData);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final double v, final int size) {
      final long[] longData;
      final double[] doubleData;
      final int types;
      int i;

      types = NumericalTypes.getTypes(v);
      if ((types & NumericalTypes.IS_LONG) != 0) {
        return this._append(((long) v), size);
      }

      if (v != v) {
        return this._append(Float.NaN, size);
      }
      if (v >= Double.POSITIVE_INFINITY) {
        return this._append(Float.POSITIVE_INFINITY, size);
      }
      if (v <= Double.NEGATIVE_INFINITY) {
        return this._append(Float.NEGATIVE_INFINITY, size);
      }

      if ((types & NumericalTypes.IS_FLOAT) != 0) {
        return this._append(((float) v), size);
      }

      longData = this.m_data;
      doubleData = new double[(size < longData.length) ? longData.length
          : ((size + 16) << 1)];
      doubleData[size] = v;
      for (i = size; (--i) >= 0;) {
        doubleData[i] = longData[i];
      }
      return new _DoubleArray(doubleData);
    }

    /** {@inheritDoc} */
    @Override
    final AbstractMatrix _make(final int size) {
      long[] data;

      data = this.m_data;
      if (data.length != size) {
        data = new long[size];
        System.arraycopy(this.m_data, 0, data, 0, size);
      }
      return ArrayBasedMatrixBuilder.this.make(data);
    }
  }

  /** the internal growable long array */
  private final class _FloatArray extends _Array {

    /** the float data */
    private float[] m_data;

    /**
     * create the array
     *
     * @param data
     *          the data
     */
    _FloatArray(final float[] data) {
      this.m_data = data;
    }

    /** {@inheritDoc} */
    @Override
    final void _setMaxSize(final int curSize, final int maxSize) {
      float[] data;
      if (this.m_data.length != maxSize) {
        data = new float[maxSize];
        System.arraycopy(this.m_data, 0, data, 0, curSize);
        this.m_data = data;
      }
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final byte v, final int size) {
      return this._append(((long) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final short v, final int size) {
      return this._append(((long) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final int v, final int size) {
      return this._append(((long) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final long v, final int size) {
      if ((NumericalTypes.getTypes(v) & //
          NumericalTypes.IS_FLOAT) == 0) {
        return this._append(((double) v), size);
      }
      return this._append(((float) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final float v, final int size) {
      float[] data;

      data = this.m_data;
      if (data.length <= size) {
        data = new float[(size + 16) << 1];
        System.arraycopy(this.m_data, 0, data, 0, size);
        this.m_data = data;
      }
      data[size] = v;
      return this;
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final double v, final int size) {
      final float[] floatData;
      final double[] doubleData;
      int i;

      if (v != v) {
        return this._append(Float.NaN, size);
      }
      if (v >= Double.POSITIVE_INFINITY) {
        return this._append(Float.POSITIVE_INFINITY, size);
      }
      if (v <= Double.NEGATIVE_INFINITY) {
        return this._append(Float.NEGATIVE_INFINITY, size);
      }

      if ((NumericalTypes.getTypes(v) & //
          NumericalTypes.IS_FLOAT) != 0) {
        return this._append(((float) v), size);
      }

      floatData = this.m_data;
      doubleData = new double[(size < floatData.length) ? floatData.length
          : ((size + 16) << 1)];
      doubleData[size] = v;
      for (i = size; (--i) >= 0;) {
        doubleData[i] = floatData[i];
      }
      return new _DoubleArray(doubleData);
    }

    /** {@inheritDoc} */
    @Override
    final AbstractMatrix _make(final int size) {
      float[] data;

      data = this.m_data;
      if (data.length != size) {
        data = new float[size];
        System.arraycopy(this.m_data, 0, data, 0, size);
      }
      return ArrayBasedMatrixBuilder.this.make(data);
    }
  }

  /** the internal growable int array */
  private final class _IntArray extends _Array {

    /** the int data */
    private int[] m_data;

    /**
     * create the array
     *
     * @param data
     *          the data
     */
    _IntArray(final int[] data) {
      this.m_data = data;
    }

    /** {@inheritDoc} */
    @Override
    final void _setMaxSize(final int curSize, final int maxSize) {
      int[] data;
      if (this.m_data.length != maxSize) {
        data = new int[maxSize];
        System.arraycopy(this.m_data, 0, data, 0, curSize);
        this.m_data = data;
      }
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final byte v, final int size) {
      return this._append(((int) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final short v, final int size) {
      return this._append(((int) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final int v, final int size) {
      int[] data;

      data = this.m_data;
      if (data.length <= size) {
        data = new int[(size + 16) << 1];
        System.arraycopy(this.m_data, 0, data, 0, size);
        this.m_data = data;
      }
      data[size] = v;
      return this;
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final long v, final int size) {
      long[] longData;
      int[] intData;
      int i;

      if ((v >= Integer.MIN_VALUE) && (v <= Integer.MAX_VALUE)) {
        return this._append(((int) v), size);
      }

      intData = this.m_data;
      longData = new long[(size < intData.length) ? intData.length
          : ((size + 16) << 1)];
      longData[size] = v;
      for (i = size; (--i) >= 0;) {
        longData[i] = intData[i];
      }
      return new _LongArray(longData);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final float v, final int size) {
      final int[] intData;
      final double[] doubleData;
      final int types;
      float[] floatData;
      int i, val;

      types = NumericalTypes.getTypes(v);
      if ((types & NumericalTypes.IS_INT) != 0) {
        return this._append(((int) v), size);
      }

      if ((types & NumericalTypes.IS_LONG) != 0) {
        return this._append(((long) v), size);
      }

      intData = this.m_data;
      floatData = new float[(size < intData.length) ? intData.length
          : ((size + 16) << 1)];
      floatData[size] = v;
      tryFloats: {
        for (i = size; (--i) >= 0;) {
          val = intData[i];
          if ((NumericalTypes.getBestFloatingPointRepresentation(val) //
              & NumericalTypes.IS_FLOAT) == 0) {
            break tryFloats;
          }
          floatData[i] = val;
        }
        return new _FloatArray(floatData);
      }

      doubleData = new double[floatData.length];
      floatData = null;
      doubleData[size] = v;
      for (i = size; (--i) >= 0;) {
        doubleData[i] = intData[i];
      }
      return new _DoubleArray(doubleData);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final double v, final int size) {
      final int[] intData;
      final double[] doubleData;
      final int types;
      int i;

      types = NumericalTypes.getTypes(v);
      if ((types & NumericalTypes.IS_INT) != 0) {
        return this._append(((int) v), size);
      }

      if ((types & NumericalTypes.IS_LONG) != 0) {
        return this._append(((long) v), size);
      }

      if (v != v) {
        return this._append(Float.NaN, size);
      }
      if (v >= Double.POSITIVE_INFINITY) {
        return this._append(Float.POSITIVE_INFINITY, size);
      }
      if (v <= Double.NEGATIVE_INFINITY) {
        return this._append(Float.NEGATIVE_INFINITY, size);
      }

      if ((types & NumericalTypes.IS_FLOAT) != 0) {
        return this._append(((float) v), size);
      }

      intData = this.m_data;
      doubleData = new double[(size < intData.length) ? intData.length
          : ((size + 16) << 1)];
      doubleData[size] = v;
      for (i = size; (--i) >= 0;) {
        doubleData[i] = intData[i];
      }
      return new _DoubleArray(doubleData);
    }

    /** {@inheritDoc} */
    @Override
    final AbstractMatrix _make(final int size) {
      int[] data;

      data = this.m_data;
      if (data.length != size) {
        data = new int[size];
        System.arraycopy(this.m_data, 0, data, 0, size);
      }
      return ArrayBasedMatrixBuilder.this.make(data);
    }
  }

  /** the internal growable short array */
  private final class _ShortArray extends _Array {

    /** the short data */
    private short[] m_data;

    /**
     * create the array
     *
     * @param data
     *          the data
     */
    _ShortArray(final short[] data) {
      this.m_data = data;
    }

    /** {@inheritDoc} */
    @Override
    final void _setMaxSize(final int curSize, final int maxSize) {
      short[] data;
      if (this.m_data.length != maxSize) {
        data = new short[maxSize];
        System.arraycopy(this.m_data, 0, data, 0, curSize);
        this.m_data = data;
      }
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final byte v, final int size) {
      return this._append(((short) v), size);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final short v, final int size) {
      short[] data;

      data = this.m_data;
      if (data.length <= size) {
        data = new short[(size + 16) << 1];
        System.arraycopy(this.m_data, 0, data, 0, size);
        this.m_data = data;
      }
      data[size] = v;
      return this;
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final int v, final int size) {
      int[] intData;
      short[] shortData;
      int i;

      if ((v >= Short.MIN_VALUE) && (v <= Short.MAX_VALUE)) {
        return this._append(((short) v), size);
      }

      shortData = this.m_data;
      intData = new int[(size < shortData.length) ? shortData.length
          : ((size + 16) << 1)];
      intData[size] = v;
      for (i = size; (--i) >= 0;) {
        intData[i] = shortData[i];
      }
      return new _IntArray(intData);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final long v, final int size) {
      long[] longData;
      short[] shortData;
      int i;

      if ((v >= Short.MIN_VALUE) && (v <= Short.MAX_VALUE)) {
        return this._append(((short) v), size);
      }
      if ((v >= Integer.MIN_VALUE) && (v <= Integer.MAX_VALUE)) {
        return this._append(((int) v), size);
      }

      shortData = this.m_data;
      longData = new long[(size < shortData.length) ? shortData.length
          : ((size + 16) << 1)];
      longData[size] = v;
      for (i = size; (--i) >= 0;) {
        longData[i] = shortData[i];
      }
      return new _LongArray(longData);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final float v, final int size) {
      final short[] shortData;
      float[] floatData;
      final int types;
      int i;

      types = NumericalTypes.getTypes(v);
      if ((types & NumericalTypes.IS_SHORT) != 0) {
        return this._append(((short) v), size);
      }

      if ((types & NumericalTypes.IS_INT) != 0) {
        return this._append(((int) v), size);
      }

      if ((types & NumericalTypes.IS_LONG) != 0) {
        return this._append(((long) v), size);
      }

      shortData = this.m_data;
      floatData = new float[(size < shortData.length) ? shortData.length
          : ((size + 16) << 1)];
      floatData[size] = v;
      for (i = size; (--i) >= 0;) {
        floatData[i] = shortData[i];
      }
      return new _FloatArray(floatData);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final double v, final int size) {
      final short[] shortData;
      double[] doubleData;
      final int types;
      int i;

      types = NumericalTypes.getTypes(v);
      if ((types & NumericalTypes.IS_SHORT) != 0) {
        return this._append(((short) v), size);
      }

      if ((types & NumericalTypes.IS_INT) != 0) {
        return this._append(((int) v), size);
      }

      if ((types & NumericalTypes.IS_LONG) != 0) {
        return this._append(((long) v), size);
      }

      if (v != v) {
        return this._append(Float.NaN, size);
      }
      if (v >= Double.POSITIVE_INFINITY) {
        return this._append(Float.POSITIVE_INFINITY, size);
      }
      if (v <= Double.NEGATIVE_INFINITY) {
        return this._append(Float.NEGATIVE_INFINITY, size);
      }

      if ((types & NumericalTypes.IS_FLOAT) != 0) {
        return this._append(((float) v), size);
      }

      shortData = this.m_data;
      doubleData = new double[(size < shortData.length) ? shortData.length
          : ((size + 16) << 1)];
      doubleData[size] = v;
      for (i = size; (--i) >= 0;) {
        doubleData[i] = shortData[i];
      }
      return new _DoubleArray(doubleData);
    }

    /** {@inheritDoc} */
    @Override
    final AbstractMatrix _make(final int size) {
      short[] data;

      data = this.m_data;
      if (data.length != size) {
        data = new short[size];
        System.arraycopy(this.m_data, 0, data, 0, size);
      }
      return ArrayBasedMatrixBuilder.this.make(data);
    }
  }

  /** the internal growable byte array */
  private final class _ByteArray extends _Array {

    /** the byte data */
    private byte[] m_data;

    /**
     * create the array
     *
     * @param data
     *          the data
     */
    _ByteArray(final byte[] data) {
      this.m_data = data;
    }

    /** {@inheritDoc} */
    @Override
    final void _setMaxSize(final int curSize, final int maxSize) {
      byte[] data;
      if (this.m_data.length != maxSize) {
        data = new byte[maxSize];
        System.arraycopy(this.m_data, 0, data, 0, curSize);
        this.m_data = data;
      }
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final byte v, final int size) {
      byte[] data;

      data = this.m_data;
      if (data.length <= size) {
        data = new byte[(size + 16) << 1];
        System.arraycopy(this.m_data, 0, data, 0, size);
        this.m_data = data;
      }
      data[size] = v;
      return this;
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final short v, final int size) {
      short[] shortData;
      byte[] byteData;
      int i;

      if ((v >= Byte.MIN_VALUE) && (v <= Byte.MAX_VALUE)) {
        return this._append(((byte) v), size);
      }

      byteData = this.m_data;
      shortData = new short[(size < byteData.length) ? byteData.length
          : ((size + 16) << 1)];
      shortData[size] = v;
      for (i = size; (--i) >= 0;) {
        shortData[i] = byteData[i];
      }
      return new _ShortArray(shortData);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final int v, final int size) {
      int[] intData;
      byte[] byteData;
      int i;

      if ((v >= Byte.MIN_VALUE) && (v <= Byte.MAX_VALUE)) {
        return this._append(((byte) v), size);
      }
      if ((v >= Short.MIN_VALUE) && (v <= Short.MAX_VALUE)) {
        return this._append(((short) v), size);
      }

      byteData = this.m_data;
      intData = new int[(size < byteData.length) ? byteData.length
          : ((size + 16) << 1)];
      intData[size] = v;
      for (i = size; (--i) >= 0;) {
        intData[i] = byteData[i];
      }
      return new _IntArray(intData);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final long v, final int size) {
      long[] longData;
      byte[] byteData;
      int i;

      if ((v >= Byte.MIN_VALUE) && (v <= Byte.MAX_VALUE)) {
        return this._append(((byte) v), size);
      }
      if ((v >= Short.MIN_VALUE) && (v <= Short.MAX_VALUE)) {
        return this._append(((short) v), size);
      }
      if ((v >= Integer.MIN_VALUE) && (v <= Integer.MAX_VALUE)) {
        return this._append(((int) v), size);
      }

      byteData = this.m_data;
      longData = new long[(size < byteData.length) ? byteData.length
          : ((size + 16) << 1)];
      longData[size] = v;
      for (i = size; (--i) >= 0;) {
        longData[i] = byteData[i];
      }
      return new _LongArray(longData);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final float v, final int size) {
      final byte[] byteData;
      float[] floatData;
      final int types;
      int i;

      types = NumericalTypes.getTypes(v);
      if ((types & NumericalTypes.IS_BYTE) != 0) {
        return this._append(((byte) v), size);
      }

      if ((types & NumericalTypes.IS_SHORT) != 0) {
        return this._append(((short) v), size);
      }

      if ((types & NumericalTypes.IS_INT) != 0) {
        return this._append(((int) v), size);
      }

      if ((types & NumericalTypes.IS_LONG) != 0) {
        return this._append(((long) v), size);
      }

      byteData = this.m_data;
      floatData = new float[(size < byteData.length) ? byteData.length
          : ((size + 16) << 1)];
      floatData[size] = v;
      for (i = size; (--i) >= 0;) {
        floatData[i] = byteData[i];
      }
      return new _FloatArray(floatData);
    }

    /** {@inheritDoc} */
    @Override
    final _Array _append(final double v, final int size) {
      final byte[] byteData;
      final int types;
      double[] doubleData;
      int i;

      types = NumericalTypes.getTypes(v);
      if ((types & NumericalTypes.IS_BYTE) != 0) {
        return this._append(((byte) v), size);
      }

      if ((types & NumericalTypes.IS_SHORT) != 0) {
        return this._append(((short) v), size);
      }

      if ((types & NumericalTypes.IS_INT) != 0) {
        return this._append(((int) v), size);
      }

      if ((types & NumericalTypes.IS_LONG) != 0) {
        return this._append(((long) v), size);
      }

      if (v != v) {
        return this._append(Float.NaN, size);
      }
      if (v >= Double.POSITIVE_INFINITY) {
        return this._append(Float.POSITIVE_INFINITY, size);
      }
      if (v <= Double.NEGATIVE_INFINITY) {
        return this._append(Float.NEGATIVE_INFINITY, size);
      }

      if ((types & NumericalTypes.IS_FLOAT) != 0) {
        return this._append(((float) v), size);
      }

      byteData = this.m_data;
      doubleData = new double[(size < byteData.length) ? byteData.length
          : ((size + 16) << 1)];
      doubleData[size] = v;
      for (i = size; (--i) >= 0;) {
        doubleData[i] = byteData[i];
      }
      return new _DoubleArray(doubleData);
    }

    /** {@inheritDoc} */
    @Override
    final AbstractMatrix _make(final int size) {
      byte[] data;

      data = this.m_data;
      if (data.length != size) {
        data = new byte[size];
        System.arraycopy(this.m_data, 0, data, 0, size);
      }
      return ArrayBasedMatrixBuilder.this.make(data);
    }
  }
}
