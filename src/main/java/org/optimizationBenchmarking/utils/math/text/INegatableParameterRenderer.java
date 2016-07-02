package org.optimizationBenchmarking.utils.math.text;

import org.optimizationBenchmarking.utils.document.spec.IMath;
import org.optimizationBenchmarking.utils.document.spec.IParameterRenderer;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;

/**
 * A parameter renderer allowing also to render negated parameter values.
 */
public interface INegatableParameterRenderer extends IParameterRenderer {
  /**
   * Check whether a parameter is negative.
   *
   * @param index
   *          the index of the parameter
   * @return {@code true} if the parameter is negative, {@code false}
   *         otherwise
   */
  public abstract boolean isNegative(final int index);

  /**
   * Render the {@code index}th parameter to the given text output device
   *
   * @param index
   *          the {@code 0}-based index of parameter
   * @param out
   *          the text output device
   */
  public abstract void renderNegatedParameter(final int index,
      final ITextOutput out);

  /**
   * Render the {@code index}th parameter to the given math output device
   *
   * @param index
   *          the {@code 0}-based index of parameter
   * @param out
   *          the math output device
   */
  public abstract void renderNegatedParameter(final int index,
      final IMath out);
}
