package org.optimizationBenchmarking.utils.math.statistics.parameters;

import org.optimizationBenchmarking.utils.math.statistics.aggregate.CoefficientOfVariationAggregate;
import org.optimizationBenchmarking.utils.text.ETextCase;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;

/** A statistic parameter computing the coefficient of variation. */
public final class CoefficientOfVariation extends StatisticalParameter {

  /** the short name */
  private static final String SHORT = "CV"; //$NON-NLS-1$
  /** the short name */
  static final String SHORT_CMP = "cv"; //$NON-NLS-1$
  /** the long name */
  static final String LONG = "coefficient of variation"; //$NON-NLS-1$
  /** the short name */
  static final String OTHER_SHORT = "rsd"; //$NON-NLS-1$
  /** the long name */
  static final String OTHER_LONG = "relative standard deviation"; //$NON-NLS-1$

  /**
   * the globally shares instance of the {@linkplain CoefficientOfVariation
   * coefficient of variation} parameter
   */
  public static final CoefficientOfVariation INSTANCE = new CoefficientOfVariation();

  /** create the coefficient of variation parameter */
  private CoefficientOfVariation() {
    super(CoefficientOfVariation.SHORT, CoefficientOfVariation.LONG, false,
        true);
  }

  /** {@inheritDoc} */
  @Override
  public final CoefficientOfVariationAggregate createSampleAggregate() {
    return new CoefficientOfVariationAggregate();
  }

  /** {@inheritDoc} */
  @Override
  public final ETextCase printDescription(final ITextOutput textOut,
      final ETextCase textCase) {
    return textCase.appendWords(//
        "the coefficient of variation of a set of values, i.e., the ratio of the standard deviation to the absolute value of the arithmetic mean.", // //$NON-NLS-1$
        textOut);
  }
}
