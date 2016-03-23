package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import org.optimizationBenchmarking.utils.math.NumericalTypes;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;
import org.optimizationBenchmarking.utils.tools.spec.IRunnableToolJob;

/** The matrix iteration 2d. */
final class _MatrixIteration2D extends _MatrixIteration2DBase
    implements IRunnableToolJob {

  /**
   * create the matrix iteration 2d
   *
   * @param builder
   *          the builder
   */
  _MatrixIteration2D(final _MatrixIteration2DBase builder) {
    super(builder);
    _MatrixIteration2DBase._checkVisitor(this.m_visitor);
    _MatrixIteration2DBase._checkXDimension(this.m_xDimension);
    _MatrixIteration2DBase._checkXDirection(this.m_xDirection);
    _MatrixIteration2DBase._checkYDimension(this.m_yDimension);
    _MatrixIteration2DBase._checkMatrices(//
        this.m_matrices, //
        this.m_xDimension, //
        this.m_xDirection, //
        this.m_yDimension);
    _MatrixIteration2DBase._checkStart(//
        this.m_iterationMode, //
        this.m_startMode, //
        this.m_startReplacement);
    _MatrixIteration2DBase._checkEnd(this.m_iterationMode, //
        this.m_endMode, //
        this.m_endReplacement);
  }

  /** {@inheritDoc} */
  @Override
  public final void run() {
    boolean isXInteger, isYInteger;
    int m;

    isXInteger = isYInteger = true;

    if (this.m_startReplacement != null) {
      isYInteger = ((NumericalTypes.getTypes(this.m_startReplacement)
          & NumericalTypes.IS_LONG) != 0);
    }
    if (isYInteger && (this.m_endReplacement != null)) {
      isYInteger = ((NumericalTypes.getTypes(this.m_endReplacement)
          & NumericalTypes.IS_LONG) != 0);
    }

    for (final IMatrix matrix : this.m_matrices) {
      m = (matrix.m() - 1);
      if (m < 0) {
        continue;
      }

      // validate direction
      if (isXInteger) {
        isXInteger = matrix.selectColumns(this.m_xDimension)
            .isIntegerMatrix();
      }

      // update replacements
      if (isYInteger) {
        isYInteger = matrix.selectColumns(this.m_yDimension)
            .isIntegerMatrix();
      }
    }

    new MatrixIteration2DState(this, isXInteger, isYInteger)._run();
  }
}
