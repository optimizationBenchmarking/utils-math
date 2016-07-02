package org.optimizationBenchmarking.utils.math.text;

import org.optimizationBenchmarking.utils.document.spec.IMath;
import org.optimizationBenchmarking.utils.document.spec.IText;
import org.optimizationBenchmarking.utils.text.ETextCase;
import org.optimizationBenchmarking.utils.text.numbers.NumberAppender;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;

/**
 * A parameter renderer which prints constant double values as parameters.
 */
public final class DoubleConstantParameters extends
    AbstractParameterRenderer implements INegatableParameterRenderer {

  /** the number appender to use */
  private final NumberAppender m_appender;
  /** the parameter values */
  private final double[] m_parameters;

  /**
   * Create the double constant parameter renderer
   *
   * @param parameters
   *          the constant parameter values
   */
  public DoubleConstantParameters(final double[] parameters) {
    this(null, parameters);
  }

  /**
   * Create the double constant parameter renderer
   *
   * @param appender
   *          the number appender to use
   * @param parameters
   *          the constant parameter values
   */
  public DoubleConstantParameters(final NumberAppender appender,
      final double[] parameters) {
    super();
    if (parameters == null) {
      throw new IllegalArgumentException(
          "Parameter array must not be null."); //$NON-NLS-1$
    }
    this.m_parameters = parameters;
    this.m_appender = appender;
  }

  /** {@inheritDoc} */
  @Override
  public final void renderParameter(final int index,
      final ITextOutput out) {
    if ((index >= 0) && (index < this.m_parameters.length)) {
      if (this.m_appender == null) {
        out.append(this.m_parameters[index]);
      } else {
        this.m_appender.appendTo(this.m_parameters[index],
            ETextCase.IN_SENTENCE, out);
      }
    } else {
      AbstractParameterRenderer.throwInvalidParameterIndex(index,
          this.m_parameters.length);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void renderParameter(final int index, final IMath out) {
    if ((index >= 0) && (index < this.m_parameters.length)) {
      try (final IText text = out.number()) {
        if (this.m_appender == null) {
          text.append(this.m_parameters[index]);
        } else {
          this.m_appender.appendTo(this.m_parameters[index],
              ETextCase.IN_SENTENCE, text);
        }
      }
    } else {
      AbstractParameterRenderer.throwInvalidParameterIndex(index,
          this.m_parameters.length);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isNegative(final int index) {
    if ((index >= 0) && (index < this.m_parameters.length)) {
      return (this.m_parameters[index] < 0);
    }
    AbstractParameterRenderer.throwInvalidParameterIndex(index,
        this.m_parameters.length);
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final void renderNegatedParameter(final int index,
      final ITextOutput out) {
    final double value;
    if ((index >= 0) && (index < this.m_parameters.length)) {
      value = (-this.m_parameters[index]);
      if (this.m_appender == null) {
        out.append((value == 0d) ? 0d : value);
      } else {
        this.m_appender.appendTo(((value == 0d) ? 0d : value),
            ETextCase.IN_SENTENCE, out);
      }
    } else {
      AbstractParameterRenderer.throwInvalidParameterIndex(index,
          this.m_parameters.length);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void renderNegatedParameter(final int index,
      final IMath out) {
    final double value;
    if ((index >= 0) && (index < this.m_parameters.length)) {
      value = (-this.m_parameters[index]);
      try (final IText text = out.number()) {
        if (this.m_appender == null) {
          text.append((value == 0d) ? 0d : value);
        } else {
          this.m_appender.appendTo(((value == 0d) ? 0d : value),
              ETextCase.IN_SENTENCE, text);
        }
      }
    } else {
      AbstractParameterRenderer.throwInvalidParameterIndex(index,
          this.m_parameters.length);
    }
  }
}
