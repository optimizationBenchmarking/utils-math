package org.optimizationBenchmarking.utils.math.statistics.statisticInfo;

import org.optimizationBenchmarking.utils.error.ErrorUtils;
import org.optimizationBenchmarking.utils.math.statistics.IStatisticInfo;
import org.optimizationBenchmarking.utils.math.statistics.parameters.ArithmeticMean;
import org.optimizationBenchmarking.utils.math.statistics.parameters.CoefficientOfVariation;
import org.optimizationBenchmarking.utils.math.statistics.parameters.InterQuartileRange;
import org.optimizationBenchmarking.utils.math.statistics.parameters.Maximum;
import org.optimizationBenchmarking.utils.math.statistics.parameters.Median;
import org.optimizationBenchmarking.utils.math.statistics.parameters.Minimum;
import org.optimizationBenchmarking.utils.math.statistics.parameters.Quantile;
import org.optimizationBenchmarking.utils.math.statistics.parameters.StandardDeviation;
import org.optimizationBenchmarking.utils.text.ETextCase;
import org.optimizationBenchmarking.utils.text.numbers.NumberAppender;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;

/** Utility methods for printing statistic information records. */
public final class StatisticInfoPrinter {

  /** the median table row */
  private static final int TABLE_ROW_MEDIAN = 0;
  /** the inter-quartile range row */
  private static final int TABLE_ROW_INTER_QUARTILE_RANGE = (StatisticInfoPrinter.TABLE_ROW_MEDIAN
      + 1);
  /** the arithmetic mean row */
  private static final int TABLE_ROW_ARIHMETIC_MEAN = (StatisticInfoPrinter.TABLE_ROW_INTER_QUARTILE_RANGE
      + 1);
  /** the standard deviation row */
  private static final int TABLE_ROW_STANDARD_DEVIATION = (StatisticInfoPrinter.TABLE_ROW_ARIHMETIC_MEAN
      + 1);
  /** the coefficient of variation row */
  private static final int TABLE_ROW_COEFFICIENT_OF_VARIATION = (StatisticInfoPrinter.TABLE_ROW_STANDARD_DEVIATION
      + 1);
  /** the minimum */
  private static final int TABLE_ROW_MINIMUM = (StatisticInfoPrinter.TABLE_ROW_COEFFICIENT_OF_VARIATION
      + 1);
  /** the 5% quantile */
  private static final int TABLE_ROW_QUANTILE_05 = (StatisticInfoPrinter.TABLE_ROW_MINIMUM
      + 1);
  /** the 25% quantile */
  private static final int TABLE_ROW_QUANTILE_25 = (StatisticInfoPrinter.TABLE_ROW_QUANTILE_05
      + 1);
  /** the 75% quantile */
  private static final int TABLE_ROW_QUANTILE_75 = (StatisticInfoPrinter.TABLE_ROW_QUANTILE_25
      + 1);
  /** the 95% quantile */
  private static final int TABLE_ROW_QUANTILE_95 = (StatisticInfoPrinter.TABLE_ROW_QUANTILE_75
      + 1);
  /** themaximum */
  private static final int TABLE_ROW_MAXIMUM = (StatisticInfoPrinter.TABLE_ROW_QUANTILE_95
      + 1);

  /** the first table row */
  public static final int TABLE_FIRST_ROW = StatisticInfoPrinter.TABLE_ROW_MEDIAN;
  /** the last table row */
  public static final int TABLE_LAST_ROW = StatisticInfoPrinter.TABLE_ROW_MAXIMUM;

  /**
   * create a row error
   *
   * @param row
   *          the invalid row
   */
  private static final void __rowError(final int row) {
    throw new IllegalArgumentException(((((((//
    "Invalid row index " + row) + //$NON-NLS-1$
        ", must be in ") + StatisticInfoPrinter.TABLE_FIRST_ROW) + //$NON-NLS-1$
        '.') + '.') + StatisticInfoPrinter.TABLE_LAST_ROW) + '.');
  }

  /**
   * The descriptions of the table rows which might be unclear, suitable
   * for a caption.
   *
   * @param textOut
   *          the text output
   */
  public static final void tableRowDescriptions(
      final ITextOutput textOut) {
    Median.INSTANCE.printLongName(textOut, ETextCase.IN_SENTENCE);
    textOut.append(' ');
    Median.INSTANCE.printShortName(textOut, ETextCase.IN_SENTENCE);
    textOut.append(',');
    textOut.append(' ');
    InterQuartileRange.INSTANCE.printLongName(textOut,
        ETextCase.IN_SENTENCE);
    textOut.append(' ');
    InterQuartileRange.INSTANCE.printShortName(textOut,
        ETextCase.IN_SENTENCE);
    textOut.append(',');
    textOut.append(' ');
    StandardDeviation.INSTANCE.printLongName(textOut,
        ETextCase.IN_SENTENCE);
    textOut.append(' ');
    StandardDeviation.INSTANCE.printShortName(textOut,
        ETextCase.IN_SENTENCE);
    textOut.append(',');
    textOut.append(' ');
    CoefficientOfVariation.INSTANCE.printLongName(textOut,
        ETextCase.IN_SENTENCE);
    textOut.append(' ');
    CoefficientOfVariation.INSTANCE.printShortName(textOut,
        ETextCase.IN_SENTENCE);
    textOut.append(" in percent, x-quantil "); //$NON-NLS-1$
    textOut.append(Quantile.createShortName("x"));//$NON-NLS-1$
  }

  /**
   * Print the row head of a statistic table.
   *
   * @param row
   *          the row instance
   * @param textOut
   *          the text output
   * @see #tableRowValue(int, IStatisticInfo, NumberAppender, ITextOutput)
   */
  public static final void tableRowHead(final int row,
      final ITextOutput textOut) {
    switch (row) {
      case TABLE_ROW_MEDIAN: {
        Median.INSTANCE.printShortName(textOut, ETextCase.IN_TITLE);
        return;
      }
      case TABLE_ROW_INTER_QUARTILE_RANGE: {
        InterQuartileRange.INSTANCE.printShortName(textOut,
            ETextCase.IN_TITLE);
        return;
      }
      case TABLE_ROW_ARIHMETIC_MEAN: {
        ArithmeticMean.INSTANCE.printShortName(textOut,
            ETextCase.IN_TITLE);
        return;
      }
      case TABLE_ROW_STANDARD_DEVIATION: {
        StandardDeviation.INSTANCE.printShortName(textOut,
            ETextCase.IN_TITLE);
        return;
      }
      case TABLE_ROW_COEFFICIENT_OF_VARIATION: {
        CoefficientOfVariation.INSTANCE.printShortName(textOut,
            ETextCase.IN_TITLE);
        textOut.append('(');
        textOut.append('%');
        textOut.append(')');
        return;
      }
      case TABLE_ROW_MINIMUM: {
        ETextCase.IN_TITLE.appendWord(Minimum.INSTANCE.getShortName(),
            textOut);
        return;
      }
      case TABLE_ROW_QUANTILE_05: {
        ETextCase.IN_TITLE.appendWord(Quantile.createShortName(0.05d),
            textOut);
        return;
      }
      case TABLE_ROW_QUANTILE_25: {
        ETextCase.IN_TITLE.appendWord(Quantile.createShortName(0.25d),
            textOut);
        return;
      }
      case TABLE_ROW_QUANTILE_75: {
        ETextCase.IN_TITLE.appendWord(Quantile.createShortName(0.75d),
            textOut);
        return;
      }
      case TABLE_ROW_QUANTILE_95: {
        ETextCase.IN_TITLE.appendWord(Quantile.createShortName(0.95d),
            textOut);
        return;
      }
      case TABLE_ROW_MAXIMUM: {
        ETextCase.IN_TITLE.appendWord(Maximum.INSTANCE.getShortName(),
            textOut);
        return;
      }
      default: {
        StatisticInfoPrinter.__rowError(row);
      }
    }
  }

  /**
   * Print the row head of a statistic table.
   *
   * @param row
   *          the row instance
   * @param info
   *          the statistic info record to print
   * @param appender
   *          the number appender
   * @param textOut
   *          the text output
   * @see #tableRowHead(int, ITextOutput)
   */
  public static final void tableRowValue(final int row,
      final IStatisticInfo info, final NumberAppender appender,
      final ITextOutput textOut) {
    double stddev, mean, coefficientOfVariation;
    switch (row) {
      case TABLE_ROW_MEDIAN: {
        appender.appendTo(info.getMedian(), ETextCase.IN_SENTENCE,
            textOut);
        return;
      }
      case TABLE_ROW_INTER_QUARTILE_RANGE: {
        appender.appendTo(info.getInterQuartileRange(),
            ETextCase.IN_SENTENCE, textOut);
        return;
      }
      case TABLE_ROW_ARIHMETIC_MEAN: {
        appender.appendTo(info.getArithmeticMean(), ETextCase.IN_SENTENCE,
            textOut);
        return;
      }
      case TABLE_ROW_STANDARD_DEVIATION: {
        appender.appendTo(info.getStandardDeviation(),
            ETextCase.IN_SENTENCE, textOut);
        return;
      }
      case TABLE_ROW_COEFFICIENT_OF_VARIATION: {
        stddev = info.getStandardDeviation().doubleValue();
        mean = Math.abs(info.getArithmeticMean().doubleValue());
        if (mean <= 0d) {
          if (stddev <= 0d) {
            coefficientOfVariation = 0d;
          } else {
            coefficientOfVariation = Double.POSITIVE_INFINITY;
          }
        } else {
          coefficientOfVariation = ((100d * stddev) / mean);
        }
        appender.appendTo(coefficientOfVariation, ETextCase.IN_SENTENCE,
            textOut);
        return;
      }
      case TABLE_ROW_MINIMUM: {
        appender.appendTo(info.getMinimum(), ETextCase.IN_SENTENCE,
            textOut);
        return;
      }
      case TABLE_ROW_QUANTILE_05: {
        appender.appendTo(info.get05Quantile(), ETextCase.IN_SENTENCE,
            textOut);
        return;
      }
      case TABLE_ROW_QUANTILE_25: {
        appender.appendTo(info.get25Quantile(), ETextCase.IN_SENTENCE,
            textOut);
        return;
      }
      case TABLE_ROW_QUANTILE_75: {
        appender.appendTo(info.get75Quantile(), ETextCase.IN_SENTENCE,
            textOut);
        return;
      }
      case TABLE_ROW_QUANTILE_95: {
        appender.appendTo(info.get95Quantile(), ETextCase.IN_SENTENCE,
            textOut);
        return;
      }
      case TABLE_ROW_MAXIMUM: {
        appender.appendTo(info.getMaximum(), ETextCase.IN_SENTENCE,
            textOut);
        return;
      }
      default: {
        StatisticInfoPrinter.__rowError(row);
      }
    }
  }

  /** the forbidden constructor */
  private StatisticInfoPrinter() {
    ErrorUtils.doNotCall();
  }
}
