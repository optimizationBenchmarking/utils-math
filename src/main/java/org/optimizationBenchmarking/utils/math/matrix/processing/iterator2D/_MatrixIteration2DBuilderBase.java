package org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D;

import java.util.Collection;
import java.util.logging.Logger;

import org.optimizationBenchmarking.utils.collections.lists.ArrayListView;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;
import org.optimizationBenchmarking.utils.tools.spec.IToolJobBuilder;

/**
 * The base class for builders for the matrix iterations.
 *
 * @param <X>
 *          the builder type
 */
abstract class _MatrixIteration2DBuilderBase<X extends _MatrixIteration2DBuilderBase<X>>
    extends MatrixIteration2DSpec implements IToolJobBuilder {

  /** create */
  _MatrixIteration2DBuilderBase() {
    super();
  }

  /**
   * Set the matrices to be used when iterating over the matrix
   *
   * @param matrices
   *          the matrices
   * @return this builder
   */
  @SuppressWarnings("unchecked")
  private final X __setMatrices(final IMatrix[] matrices) {
    MatrixIteration2DSpec._checkMatricesNotNull(matrices);
    this.m_matrices = matrices;
    this.m_sourceMatrixList = null;
    return ((X) this);
  }

  /**
   * Set the matrices to be used when iterating over the matrix
   *
   * @param matrices
   *          the matrices
   * @return this builder
   */
  public final X setMatrices(final IMatrix... matrices) {
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
  public final X setMatrices(
      final Collection<? extends IMatrix> matrices) {
    final X ret;

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
  @SuppressWarnings("unchecked")
  public final X setIterationMode(final EIterationMode iterationMode) {
    MatrixIteration2DSpec._checkIterationMode(iterationMode);
    this.m_iterationMode = iterationMode;
    return ((X) this);
  }

  /**
   * Set the start mode.
   *
   * @param mode
   *          the mode
   * @return this iterator
   */
  @SuppressWarnings("unchecked")
  public final X setStartMode(final EMissingValueMode mode) {
    MatrixIteration2DSpec._checkStartMode(mode);
    if (mode != EMissingValueMode.SET_TO_VALUE) {
      this.m_startReplacement = null;
    }
    this.m_startMode = mode;
    return ((X) this);
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
  @SuppressWarnings("unchecked")
  public final X setStartReplacement(final Number value) {
    _MatrixIteration2DBuilderBase._checkStartModeReplacement(value);
    this.m_startMode = EMissingValueMode.SET_TO_VALUE;
    this.m_startReplacement = value;
    return ((X) this);
  }

  /**
   * Set the end mode.
   *
   * @param mode
   *          the mode
   * @return this iterator
   */
  @SuppressWarnings("unchecked")
  public final X setEndMode(final EMissingValueMode mode) {
    MatrixIteration2DSpec._checkEndMode(mode);
    if (mode != EMissingValueMode.SET_TO_VALUE) {
      this.m_endReplacement = null;
    }
    this.m_endMode = mode;
    return ((X) this);
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
  @SuppressWarnings("unchecked")
  public final X setEndReplacement(final Number value) {
    _MatrixIteration2DBuilderBase._checkEndModeReplacement(value);
    this.m_endMode = EMissingValueMode.SET_TO_VALUE;
    this.m_endReplacement = value;
    return ((X) this);
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
   * Set whether leading and trailing {@link Double#NaN}s along the
   * {@code x} axis should be skipped? if {@code true}, this mode takes
   * precedence over {@link #getStartMode()} and {@link #getEndMode()}.
   *
   * @param skip
   *          {@code true} to skip the {@link Double#NaN}s, {@code false}
   *          to not skip them
   * @return this builder
   * @see #areLeadingAndTrailingNaNsOnXAxisSkipped()
   */
  @SuppressWarnings("unchecked")
  public final X setSkipLeadingAndTrailingNaNsOnXAxis(final boolean skip) {
    this.m_skipLeadingAndTrailingXNaNs = skip;
    return ((X) this);
  }

  /**
   * Set whether leading and trailing {@link Double#NaN}s along the
   * {@code y} axis should be skipped? if {@code true}, this mode takes
   * precedence over {@link #getStartMode()}, {@link #getEndMode()}, and,
   * most notably, {@link #isNaNReplacementForYAxisUsed()} and
   * {@link #getNaNReplacementForYAxis()}.
   *
   * @param skip
   *          {@code true} to skip the {@link Double#NaN}s, {@code false}
   *          to not skip them
   * @return this builder
   * @see #areLeadingAndTrailingNaNsOnYAxisSkipped()
   */
  @SuppressWarnings("unchecked")
  public final X setSkipLeadingAndTrailingNaNsOnYAxis(final boolean skip) {
    this.m_skipLeadingAndTrailingYNaNs = skip;
    return ((X) this);
  }

  /**
   * Set the replacement for {@link Double#NaN} values along the {@code y}
   * axis.
   *
   * @param replacement
   *          the replacement
   * @return this builder
   * @see #areLeadingAndTrailingNaNsOnYAxisSkipped()
   * @see #unsetNaNReplacementForYAxis()
   * @see #getNaNReplacementForYAxis()
   * @see #isNaNReplacementForYAxisUsed()
   * @see #setSkipLeadingAndTrailingNaNsOnYAxis(boolean)
   */
  @SuppressWarnings("unchecked")
  public final X setNaNReplacementForYAxis(final double replacement) {
    this.m_yNaNReplacement = replacement;
    this.m_useYNaNReplacement = true;
    return ((X) this);
  }

  /**
   * Unset, i.e., remove the replacement for {@link Double#NaN} values
   * along the {@code y} axis.
   *
   * @return this builder
   * @see #areLeadingAndTrailingNaNsOnYAxisSkipped()
   * @see #setNaNReplacementForYAxis(double)
   * @see #getNaNReplacementForYAxis()
   * @see #isNaNReplacementForYAxisUsed()
   * @see #setSkipLeadingAndTrailingNaNsOnYAxis(boolean)
   */
  @SuppressWarnings("unchecked")
  public final X unsetNaNReplacementForYAxis() {
    this.m_useYNaNReplacement = false;
    return ((X) this);
  }

  /**
   * Set the {@code x}-dimension
   *
   * @param xDimension
   *          the dimension
   * @return this builder
   */
  @SuppressWarnings("unchecked")
  public final X setXDimension(final int xDimension) {
    MatrixIteration2DSpec._checkXDimension(xDimension);
    this.m_xDimension = xDimension;
    return ((X) this);
  }

  /**
   * Set the {@code y}-dimension
   *
   * @param yDimension
   *          the dimension
   * @return this builder
   */
  @SuppressWarnings("unchecked")
  public final X setYDimension(final int yDimension) {
    MatrixIteration2DSpec._checkYDimension(yDimension);
    this.m_yDimension = yDimension;
    return ((X) this);
  }

  /**
   * Set whether {@code x}-coordinates should be increasing or decreasing.
   *
   * @param direction
   *          the iteration direction
   * @return this builder
   */
  @SuppressWarnings("unchecked")
  public final X setXDirection(final EIterationDirection direction) {
    MatrixIteration2DSpec._checkXDirection(direction);
    this.m_xDirection = direction;
    return ((X) this);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final X setLogger(final Logger logger) {
    this.m_logger = logger;
    return ((X) this);
  }
}
