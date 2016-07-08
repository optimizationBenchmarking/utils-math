package org.optimizationBenchmarking.utils.math.matrix.processing;

import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;

/**
 * A matrix which is based on applying a unary function to the columns of
 * another matrix.
 */
public class ColumnTransformedMatrix extends AbstractMatrix {

  /** the column holds floating point numbers */
  static final int COLUMN_FLOAT = 0;
  /**
   * the column holds integer numbers, but the transformationFunctions is
   * floating-point based
   */
  static final int COLUMN_INT_BUT_TRANSFORMATION_FLOAT = (ColumnTransformedMatrix.COLUMN_FLOAT
      + 1);
  /**
   * the column holds integer numbers and the transformationFunctions is
   * integer-based
   */
  static final int COLUMN_AND_TRANSFORMATION_INT = (ColumnTransformedMatrix.COLUMN_INT_BUT_TRANSFORMATION_FLOAT
      + 1);

  /** the functions used for transformationFunctions */
  private final UnaryFunction[] m_transformationFunctions;

  /** whether a given column is an integer */
  private final int[] m_columnIntegerState;

  /** the source matrix */
  private final IMatrix m_source;

  /**
   * Create the column-transformed matrix
   *
   * @param source
   *          the source matrix
   * @param transformationFunctions
   *          the transformations to be applied to the columns
   */
  public ColumnTransformedMatrix(final IMatrix source,
      final UnaryFunction... transformationFunctions) {
    final int n;
    int i;

    this.m_source = ColumnTransformedMatrix._checkSourceMatrix(source);
    n = source.n();
    this.m_transformationFunctions = ColumnTransformedMatrix
        ._checkTransformation(n, transformationFunctions).clone();

    this.m_columnIntegerState = new int[n];
    for (i = n; (--i) >= 0;) {
      this.m_columnIntegerState[i] = ColumnTransformedMatrix
          ._columnState(source, transformationFunctions[i], i);
    }
  }

  /**
   * Create the column-transformed matrix
   *
   * @param source
   *          the source matrix
   * @param columnIntegerState
   *          the integer column state
   * @param transformationFunctions
   *          the transformations to be applied to the columns
   */
  private ColumnTransformedMatrix(final IMatrix source,
      final int[] columnIntegerState,
      final UnaryFunction[] transformationFunctions) {
    super();
    this.m_source = source;
    this.m_columnIntegerState = columnIntegerState;
    this.m_transformationFunctions = transformationFunctions;
  }

  /**
   * check the source matrix
   *
   * @param source
   *          the source matrix
   * @return the matrix
   */
  static final IMatrix _checkSourceMatrix(final IMatrix source) {
    if (source == null) {
      throw new IllegalArgumentException(//
          "Source matrix must not be null."); //$NON-NLS-1$
    }
    return source;
  }

  /**
   * check the transformationFunctions
   *
   * @param n
   *          the expected number of functions
   * @param transformationFunctions
   *          the transformationFunctions functions
   * @return the transformationFunctions
   */
  static final UnaryFunction[] _checkTransformation(final int n,
      final UnaryFunction[] transformationFunctions) {
    if (transformationFunctions == null) {
      throw new IllegalArgumentException(//
          "Transformation must not be null."); //$NON-NLS-1$
    }
    if (n != transformationFunctions.length) {
      throw new IllegalArgumentException(//
          "Number of transformationFunctions functions (" //$NON-NLS-1$
              + transformationFunctions.length + //
              ") must equal number of columns ("//$NON-NLS-1$
              + n + //
              ") but does not.");//$NON-NLS-1$
    }
    return transformationFunctions;
  }

  /**
   * compute the column state
   *
   * @param matrix
   *          the matrix
   * @param transformationFunctions
   *          the transformationFunctions function
   * @param column
   *          the column to be transformed
   * @return the state (integer-wise)
   */
  static final int _columnState(final IMatrix matrix,
      final UnaryFunction transformationFunctions, final int column) {
    if (transformationFunctions == null) {
      throw new IllegalArgumentException(//
          "Transformation function is null at for column " + column); //$NON-NLS-1$
    }
    if (matrix.selectColumns(column).isIntegerMatrix()) {
      if (transformationFunctions.isLongArithmeticAccurate()) {
        return ColumnTransformedMatrix.COLUMN_AND_TRANSFORMATION_INT;
      }
      return ColumnTransformedMatrix.COLUMN_INT_BUT_TRANSFORMATION_FLOAT;

    }
    return ColumnTransformedMatrix.COLUMN_FLOAT;
  }

  /** {@inheritDoc} */
  @Override
  public final int m() {
    return this.m_source.m();
  }

  /** {@inheritDoc} */
  @Override
  public int n() {
    return this.m_source.n();
  }

  /** {@inheritDoc} */
  @Override
  public final double getDouble(final int row, final int column) {
    if ((column >= 0)
        && (column < this.m_transformationFunctions.length)) {
      switch (this.m_columnIntegerState[column]) {
        case COLUMN_FLOAT: {
          return this.m_transformationFunctions[column].computeAsDouble(//
              this.m_source.getDouble(row, column));
        }
        case COLUMN_INT_BUT_TRANSFORMATION_FLOAT: {
          return this.m_transformationFunctions[column].computeAsDouble(//
              this.m_source.getLong(row, column));
        }
        default: {
          return this.m_transformationFunctions[column].computeAsLong(//
              this.m_source.getLong(row, column));
        }
      }
    }
    return super.getDouble(row, column);
  }

  /** {@inheritDoc} */
  @Override
  public final long getLong(final int row, final int column) {
    if ((column >= 0)
        && (column < this.m_transformationFunctions.length)) {
      if (this.m_columnIntegerState[column] == 0) {
        return ((long) (this.m_transformationFunctions[column]
            .computeAsDouble(//
                this.m_source.getDouble(row, column))));
      }
      return this.m_transformationFunctions[column].computeAsLong(//
          this.m_source.getLong(row, column));
    }
    return super.getLong(row, column);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isIntegerMatrix() {
    for (final int i : this.m_columnIntegerState) {
      if (i != ColumnTransformedMatrix.COLUMN_AND_TRANSFORMATION_INT) {
        return false;
      }
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public final IMatrix selectColumns(final int... cols) {
    final UnaryFunction[] func;
    final int[] intCols;
    final IMatrix sel;
    int i;

    sel = this.m_source.selectColumns(cols);

    i = cols.length;
    func = new UnaryFunction[i];
    intCols = new int[i];
    for (; (--i) >= 0;) {
      func[i] = this.m_transformationFunctions[cols[i]];
      intCols[i] = this.m_columnIntegerState[i];
    }
    return new ColumnTransformedMatrix(sel, intCols, func);
  }
}
