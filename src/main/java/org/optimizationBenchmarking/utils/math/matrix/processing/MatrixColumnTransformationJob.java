package org.optimizationBenchmarking.utils.math.matrix.processing;

import org.optimizationBenchmarking.utils.collections.ArrayUtils;
import org.optimizationBenchmarking.utils.math.NumericalTypes;
import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.ByteMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.impl.DoubleMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.impl.FloatMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.impl.IntMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.impl.LongMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.impl.ShortMatrix1D;
import org.optimizationBenchmarking.utils.tools.spec.ICallableToolJob;

/**
 * A job for transforming a matrix. Different from
 * {@link ColumnTransformedMatrix}, this function creates a copy of the
 * transformation of the given matrix.
 */
public final class MatrixColumnTransformationJob
    implements ICallableToolJob<AbstractMatrix> {

  /** the functions used for transformationFunctions */
  private UnaryFunction[] m_transformationFunctions;

  /** whether a given column is an integer */
  private int[] m_columnIntegerState;

  /** the source matrix */
  private IMatrix m_source;

  /** the selected columns */
  private int[] m_selectedColumns;

  /**
   * Create the column-transformed matrix
   *
   * @param source
   *          the source matrix
   * @param selectedColumns
   *          the selected columns
   * @param transformationFunctions
   *          the transformations to be applied to the columns
   * @param columnIntegerState
   *          the integer state of the columns
   */
  MatrixColumnTransformationJob(final IMatrix source,
      final int[] selectedColumns,
      final UnaryFunction[] transformationFunctions,
      final int[] columnIntegerState) {
    super();
    this.m_source = source;
    this.m_selectedColumns = selectedColumns;
    this.m_columnIntegerState = columnIntegerState;
    this.m_transformationFunctions = transformationFunctions;
  }

  /**
   * check the selected columns
   *
   * @param columns
   *          the columns
   * @return the columns
   */
  static final int[] _checkSelectedColumns(final int[] columns) {
    if (columns == null) {
      throw new IllegalArgumentException(
          "Selected columns cannot be null."); //$NON-NLS-1$
    }
    return columns;
  }

  /**
   * Create the column-transformed matrix
   *
   * @param source
   *          the source matrix
   * @param selectedColumns
   *          the selected columns
   * @param transformationFunctions
   *          the transformations to be applied to the columns
   */
  public MatrixColumnTransformationJob(final IMatrix source,
      final int[] selectedColumns,
      final UnaryFunction[] transformationFunctions) {

    this(ColumnTransformedMatrix._checkSourceMatrix(source), //
        MatrixColumnTransformationJob
            ._checkSelectedColumns(selectedColumns), //
        ColumnTransformedMatrix._checkTransformation(
            selectedColumns.length, transformationFunctions), //
        null);
  }

  /** {@inheritDoc} */
  @Override
  public final AbstractMatrix call() {
    final int[] selectedColumns;
    final IMatrix source;
    final UnaryFunction[] transformationFunctions;
    final int rows;
    int[] states;
    int minState;

    source = this.m_source;
    this.m_source = null;
    transformationFunctions = this.m_transformationFunctions;
    this.m_transformationFunctions = null;
    selectedColumns = this.m_selectedColumns;
    this.m_selectedColumns = null;

    states = this.m_columnIntegerState;
    this.m_columnIntegerState = null;
    if (states == null) {
      states = MatrixColumnTransformationJob._computeIntegerColumnState(
          source, selectedColumns, transformationFunctions);
    }

    minState = ColumnTransformedMatrix.COLUMN_AND_TRANSFORMATION_INT;
    for (final int state : states) {
      if (state < minState) {
        minState = state;
      }
    }

    rows = source.m();
    if (minState == ColumnTransformedMatrix.COLUMN_AND_TRANSFORMATION_INT) {
      return MatrixColumnTransformationJob.__createIntegerMatrix(source,
          rows, selectedColumns, transformationFunctions);
    }
    return MatrixColumnTransformationJob.__createDoubleMatrix(source, rows,
        selectedColumns, transformationFunctions, states);
  }

  /**
   * create an integer matrix
   *
   * @param source
   *          the source matrix
   * @param rows
   *          the number of rows
   * @param selectedColumns
   *          the selected columns
   * @param transformationFunctions
   *          the transformations to be applied to the columns
   * @return the integer matrix
   */
  @SuppressWarnings("fallthrough")
  private static final AbstractMatrix __createIntegerMatrix(
      final IMatrix source, final int rows, final int[] selectedColumns,
      final UnaryFunction[] transformationFunctions) {
    final int columns;
    long[] data;
    int index, row, col, numberState;
    long value;

    columns = selectedColumns.length;
    index = rows * columns;
    data = new long[index];

    numberState = NumericalTypes.IS_BYTE;

    for (row = rows; (--row) >= 0;) {
      loop: for (col = columns; (--col) >= 0;) {
        data[--index] = value = transformationFunctions[col]
            .computeAsLong(source.getLong(row, selectedColumns[col]));
        switch (numberState) {
          case NumericalTypes.IS_BYTE: {
            if ((value >= Byte.MIN_VALUE) && (value <= Byte.MAX_VALUE)) {
              continue loop;
            }
            numberState = NumericalTypes.IS_SHORT;
          }

          case NumericalTypes.IS_SHORT: {
            if ((value >= Short.MIN_VALUE) && (value <= Short.MAX_VALUE)) {
              continue loop;
            }
            numberState = NumericalTypes.IS_INT;
          }

          case NumericalTypes.IS_INT: {
            if ((value >= Integer.MIN_VALUE)
                && (value <= Integer.MAX_VALUE)) {
              continue loop;
            }
            numberState = NumericalTypes.IS_LONG;
          }

          default: {
            continue loop;
          }
        }
      }
    }

    switch (numberState) {
      case NumericalTypes.IS_BYTE: {
        return new ByteMatrix1D(ArrayUtils.longsToBytes(data), rows,
            columns);
      }

      case NumericalTypes.IS_SHORT: {
        return new ShortMatrix1D(ArrayUtils.longsToShorts(data), rows,
            columns);
      }

      case NumericalTypes.IS_INT: {
        return new IntMatrix1D(ArrayUtils.longsToInts(data), rows,
            columns);
      }

      default: {
        return new LongMatrix1D(data, rows, columns);
      }
    }
  }

  /**
   * create an double matrix
   *
   * @param source
   *          the source matrix
   * @param rows
   *          the number of rows
   * @param selectedColumns
   *          the selected columns
   * @param transformationFunctions
   *          the transformations to be applied to the columns
   * @param state
   *          the column state
   * @return the integer matrix
   */
  @SuppressWarnings("fallthrough")
  private static final AbstractMatrix __createDoubleMatrix(
      final IMatrix source, final int rows, final int[] selectedColumns,
      final UnaryFunction[] transformationFunctions, final int[] state) {
    final int columns;
    double[] data;
    int index, row, col, numberState;
    double value, longValue;
    float floatValue;

    columns = selectedColumns.length;
    index = rows * columns;
    data = new double[index];
    longValue = Long.MIN_VALUE;
    numberState = NumericalTypes.IS_BYTE;

    for (row = rows; (--row) >= 0;) {
      loop: for (col = columns; (--col) >= 0;) {

        switch (state[col]) {
          case ColumnTransformedMatrix.COLUMN_FLOAT: {
            value = transformationFunctions[col].computeAsDouble(//
                source.getDouble(row, selectedColumns[col]));
            break;
          }
          case ColumnTransformedMatrix.COLUMN_INT_BUT_TRANSFORMATION_FLOAT: {
            value = transformationFunctions[col].computeAsDouble(//
                source.getLong(row, selectedColumns[col]));
            break;
          }
          default: {
            value = transformationFunctions[col]
                .computeAsLong(source.getLong(row, selectedColumns[col]));
            break;
          }
        }

        data[--index] = value;

        if (numberState < NumericalTypes.IS_FLOAT) {
          if ((value >= Long.MIN_VALUE) && (value <= Long.MAX_VALUE)) {
            longValue = (long) value;
            if (longValue != value) {
              numberState = NumericalTypes.IS_FLOAT;
            }
          }
        }

        switch (numberState) {
          case NumericalTypes.IS_BYTE: {
            if ((longValue >= Byte.MIN_VALUE)
                && (longValue <= Byte.MAX_VALUE)) {
              continue loop;
            }
            numberState = NumericalTypes.IS_SHORT;
          }

          case NumericalTypes.IS_SHORT: {
            if ((longValue >= Short.MIN_VALUE)
                && (longValue <= Short.MAX_VALUE)) {
              continue loop;
            }
            numberState = NumericalTypes.IS_INT;
          }

          case NumericalTypes.IS_INT: {
            if ((longValue >= Integer.MIN_VALUE)
                && (longValue <= Integer.MAX_VALUE)) {
              continue loop;
            }
            numberState = NumericalTypes.IS_LONG;
          }

          case NumericalTypes.IS_LONG: {
            continue loop;
          }

          case NumericalTypes.IS_FLOAT: {
            floatValue = ((float) value);
            if ((floatValue == value) || (value != value)) {
              continue loop;
            }
            numberState = NumericalTypes.IS_DOUBLE;
          }

          default: {
            continue loop;
          }
        }
      }
    }

    switch (numberState) {
      case NumericalTypes.IS_BYTE: {
        return new ByteMatrix1D(ArrayUtils.doublesToBytes(data), rows,
            columns);
      }

      case NumericalTypes.IS_SHORT: {
        return new ShortMatrix1D(ArrayUtils.doublesToShorts(data), rows,
            columns);
      }

      case NumericalTypes.IS_INT: {
        return new IntMatrix1D(ArrayUtils.doublesToInts(data), rows,
            columns);
      }

      case NumericalTypes.IS_LONG: {
        return new LongMatrix1D(ArrayUtils.doublesToLongs(data), rows,
            columns);
      }

      case NumericalTypes.IS_FLOAT: {
        return new FloatMatrix1D(ArrayUtils.doublesToFloats(data), rows,
            columns);
      }

      default: {
        return new DoubleMatrix1D(data, rows, columns);
      }
    }
  }

  /**
   * get the column states
   *
   * @param source
   *          the source matrix
   * @param selectedColumns
   *          the selected columns
   * @param transformationFunctions
   *          the transformation functions
   * @return the column states
   */
  static final int[] _computeIntegerColumnState(final IMatrix source,
      final int[] selectedColumns,
      final UnaryFunction[] transformationFunctions) {
    final int[] columnIntegerState;
    int i;

    i = selectedColumns.length;
    columnIntegerState = new int[i];
    for (; (--i) >= 0;) {
      columnIntegerState[i] = ColumnTransformedMatrix._columnState(source,
          transformationFunctions[i], selectedColumns[i]);
    }
    return columnIntegerState;
  }
}
