package org.optimizationBenchmarking.utils.math.matrix.processing;

import java.util.Collection;
import java.util.concurrent.Future;

import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;
import org.optimizationBenchmarking.utils.parallel.Execute;
import org.optimizationBenchmarking.utils.tools.spec.ICallableToolJob;

/**
 * A job for transforming multiple matrices with the same features in the
 * same way. In particular, all matrices are assumed to have the same
 * integer state of their columns.
 */
public final class MultiMatrixColumnTransformationJob
    implements ICallableToolJob<AbstractMatrix[]> {
  /** the functions used for transformationFunctions */
  private UnaryFunction[] m_transformationFunctions;
  /** the source matrix */
  private Collection<? extends IMatrix> m_sources;
  /** the selected columns */
  private int[] m_selectedColumns;

  /**
   * Create the column-transformed matrix
   *
   * @param sources
   *          the collection of source matrices
   * @param selectedColumns
   *          the selected columns
   * @param transformationFunctions
   *          the transformations to be applied to the columns
   */
  public MultiMatrixColumnTransformationJob(
      final Collection<? extends IMatrix> sources,
      final int[] selectedColumns,
      final UnaryFunction[] transformationFunctions) {
    super();
    if (sources == null) {
      throw new IllegalArgumentException(
          "Source matrix collection cannot be null."); //$NON-NLS-1$
    }
    this.m_selectedColumns = MatrixColumnTransformationJob
        ._checkSelectedColumns(selectedColumns);
    this.m_transformationFunctions = ColumnTransformedMatrix
        ._checkTransformation(selectedColumns.length,
            transformationFunctions);
    this.m_sources = sources;
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unchecked")
  @Override
  public final AbstractMatrix[] call() {
    UnaryFunction[] transformationFunctions;
    Collection<? extends IMatrix> sources;
    int[] selectedColumns;
    final int size;
    int[] states;
    Future<AbstractMatrix>[] results;
    AbstractMatrix[] dest;
    int index;

    transformationFunctions = this.m_transformationFunctions;
    this.m_transformationFunctions = null;
    sources = this.m_sources;
    this.m_sources = null;
    selectedColumns = this.m_selectedColumns;
    this.m_selectedColumns = null;

    size = sources.size();
    if (size <= 0) {
      return new AbstractMatrix[0];
    }

    states = null;
    results = new Future[size];
    index = (-1);
    for (final IMatrix source : sources) {
      ColumnTransformedMatrix._checkSourceMatrix(source);
      if (states == null) {
        states = MatrixColumnTransformationJob._computeIntegerColumnState(
            source, selectedColumns, transformationFunctions);
      }
      results[++index] = Execute
          .parallel(new MatrixColumnTransformationJob(source,
              selectedColumns, transformationFunctions, states));
    }

    transformationFunctions = null;
    sources = null;
    selectedColumns = null;
    dest = new AbstractMatrix[size];

    Execute.join(results, dest, 0, false);
    return dest;
  }
}
