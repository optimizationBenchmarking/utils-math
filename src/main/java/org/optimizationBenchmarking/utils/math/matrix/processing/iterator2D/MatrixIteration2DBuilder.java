package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import java.util.Collection;
import java.util.logging.Logger;

import org.optimizationBenchmarking.utils.collections.lists.ArrayListView;
import org.optimizationBenchmarking.utils.collections.visitors.IVisitor;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;
import org.optimizationBenchmarking.utils.tools.spec.IToolJobBuilder;

/** The builder for the matrix iterations. */
public class MatrixIteration2DBuilder extends _MatrixIteration2DBase
    implements IToolJobBuilder {

  /** create */
  public MatrixIteration2DBuilder() {
    super();
  }

  /**
   * Set the visitor to receive the iteration data
   *
   * @param visitor
   *          the visitor to receive the iteration data
   * @return this builder
   */
  public final MatrixIteration2DBuilder setVisitor(
      final IVisitor<MatrixIteration2DState> visitor) {
    _MatrixIteration2DBase._checkVisitor(visitor);
    this.m_visitor = visitor;
    return this;
  }

  /**
   * Set the matrices to be used when iterating over the matrix
   *
   * @param matrices
   *          the matrices
   * @return this builder
   */
  private final MatrixIteration2DBuilder __setMatrices(
      final IMatrix[] matrices) {
    _MatrixIteration2DBase._checkMatricesNotNull(matrices);
    this.m_matrices = matrices;
    this.m_sourceMatrixList = null;
    return this;
  }

  /**
   * Set the matrices to be used when iterating over the matrix
   *
   * @param matrices
   *          the matrices
   * @return this builder
   */
  public final MatrixIteration2DBuilder setMatrices(
      final IMatrix... matrices) {
    return this.__setMatrices(matrices.clone());
  }

  /**
   * Set the matrices to be used when iterating over the matrix
   *
   * @param matrices
   *          the matrices
   * @return this builder
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public final MatrixIteration2DBuilder setMatrices(
      final Collection<IMatrix> matrices) {
    final MatrixIteration2DBuilder ret;

    if (matrices == null) {
      throw new IllegalArgumentException(
          "Matrix collection must not be null."); //$NON-NLS-1$
    }

    ret = this.__setMatrices(matrices.toArray(//
        new IMatrix[matrices.size()]));
    if (ret == this) {
      if (matrices instanceof ArrayListView) {
        this.m_sourceMatrixList = ((ArrayListView) matrices);
      }
    }
    return ret;
  }

  /**
   * Set the iteration mode
   *
   * @param iterationMode
   *          the iteration mode
   * @return this builder
   */
  public final MatrixIteration2DBuilder setIterationMode(
      final EIterationMode iterationMode) {
    _MatrixIteration2DBase._checkIterationMode(iterationMode);
    this.m_iterationMode = iterationMode;
    return this;
  }

  /**
   * Set the start mode.
   *
   * @param mode
   *          the mode
   * @return this iterator
   */
  public final MatrixIteration2DBuilder setStartMode(
      final EMissingValueMode mode) {
    _MatrixIteration2DBase._checkStartMode(mode);
    if (mode != EMissingValueMode.SET_TO_VALUE) {
      this.m_startReplacement = null;
    }
    this.m_startMode = mode;
    return this;
  }

  /**
   * Set the start replacement value. This will automatically set the
   * {@linkplain #setStartMode(EMissingValueMode) start mode} to
   * {@link EMissingValueMode#SET_TO_VALUE}.
   *
   * @param value
   *          the value
   * @return this builder
   */
  public final MatrixIteration2DBuilder setStartReplacement(
      final Number value) {
    MatrixIteration2DBuilder._checkStartModeReplacement(value);
    this.m_startMode = EMissingValueMode.SET_TO_VALUE;
    this.m_startReplacement = value;
    return this;
  }

  /**
   * Set the end mode.
   *
   * @param mode
   *          the mode
   * @return this iterator
   */
  public final MatrixIteration2DBuilder setEndMode(
      final EMissingValueMode mode) {
    _MatrixIteration2DBase._checkEndMode(mode);
    if (mode != EMissingValueMode.SET_TO_VALUE) {
      this.m_endReplacement = null;
    }
    this.m_endMode = mode;
    return this;
  }

  /**
   * Set the end replacement value. This will automatically set the
   * {@linkplain #setEndMode(EMissingValueMode) end mode} to
   * {@link EMissingValueMode#SET_TO_VALUE}.
   *
   * @param value
   *          the value
   * @return this builder
   */
  public final MatrixIteration2DBuilder setEndReplacement(
      final Number value) {
    MatrixIteration2DBuilder._checkEndModeReplacement(value);
    this.m_endMode = EMissingValueMode.SET_TO_VALUE;
    this.m_endReplacement = value;
    return this;
  }

  /**
   * check the start replacement value
   *
   * @param value
   *          the value
   */
  static final void _checkStartModeReplacement(final Number value) {
    if (value == null) {
      throw new IllegalArgumentException(
          "Start replacement number cannot be set to null."); //$NON-NLS-1$
    }
  }

  /**
   * check the end replacement value
   *
   * @param value
   *          the value
   */
  static final void _checkEndModeReplacement(final Number value) {
    if (value == null) {
      throw new IllegalArgumentException(
          "End replacement number cannot be set to null."); //$NON-NLS-1$
    }
  }

  /**
   * Set the {@code x}-dimension
   *
   * @param xDimension
   *          the dimension
   * @return this builder
   */
  public final MatrixIteration2DBuilder setXDimension(
      final int xDimension) {
    _MatrixIteration2DBase._checkXDimension(xDimension);
    this.m_xDimension = xDimension;
    return this;
  }

  /**
   * Set the {@code y}-dimension
   *
   * @param yDimension
   *          the dimension
   * @return this builder
   */
  public final MatrixIteration2DBuilder setYDimension(
      final int yDimension) {
    _MatrixIteration2DBase._checkYDimension(yDimension);
    this.m_yDimension = yDimension;
    return this;
  }

  /**
   * Set whether {@code x}-coordinates should be increasing or decreasing.
   *
   * @param direction
   *          the iteration direction
   * @return this builder
   */
  public final MatrixIteration2DBuilder setXDirection(
      final EIterationDirection direction) {
    _MatrixIteration2DBase._checkXDirection(direction);
    this.m_xDirection = direction;
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final MatrixIteration2D create() {
    return new MatrixIteration2D(this);
  }

  /** {@inheritDoc} */
  @Override
  public final MatrixIteration2DBuilder setLogger(final Logger logger) {
    this.m_logger = logger;
    return this;
  }
}
