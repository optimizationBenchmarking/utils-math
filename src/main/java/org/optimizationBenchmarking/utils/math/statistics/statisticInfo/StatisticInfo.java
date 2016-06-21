package org.optimizationBenchmarking.utils.math.statistics.statisticInfo;

import java.io.Serializable;

import org.optimizationBenchmarking.utils.IImmutable;
import org.optimizationBenchmarking.utils.math.statistics.IStatisticInfo;

/** The statistic information record */
public final class StatisticInfo
    implements IStatisticInfo, IImmutable, Serializable {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the sample size */
  private final long m_sampleSize;
  /** the minimum */
  private final Number m_minimum;
  /** the maximum */
  private final Number m_maximum;
  /** the arithmetic mean */
  private final Number m_arithmeticMean;
  /** the standard deviation */
  private final Number m_standardDeviation;
  /** the median */
  private final Number m_median;
  /** the inter-quartile range */
  private final Number m_interQuartileRange;
  /** the 5% quantile */
  private final Number m_q05;
  /** the 25% quantile */
  private final Number m_q25;
  /** the 75% quantile */
  private final Number m_q75;
  /** the 95% quantile */
  private final Number m_q95;

  /**
   * Create the statistic information record
   *
   * @param sampleSize
   *          the sample size, {@code -1} for undefined
   * @param minimum
   *          the minimum, {@code null} for undefined
   * @param maximum
   *          the maximum, {@code null} for undefined
   * @param arithmeticMean
   *          the arithmetic mean, {@code null} for undefined
   * @param standardDeviation
   *          the standard deviation, {@code null} for undefined
   * @param median
   *          the median, {@code null} for undefined
   * @param interQuartileRange
   *          the inter-quartile range, {@code null} for undefined
   * @param q05
   *          the 5% quantile, {@code null} for undefined
   * @param q25
   *          the 25% quantile, {@code null} for undefined
   * @param q75
   *          the 75% quantile, {@code null} for undefined
   * @param q95
   *          the 95% quantile, {@code null} for undefined
   */
  public StatisticInfo(final long sampleSize, final Number minimum,
      final Number maximum, final Number arithmeticMean,
      final Number standardDeviation, final Number median,
      final Number interQuartileRange, final Number q05, final Number q25,
      final Number q75, final Number q95) {
    super();

    Number value, negativeInfinity, positiveInfinity, nan;
    double dvalue;

    this.m_sampleSize = sampleSize;

    negativeInfinity = positiveInfinity = nan = null;

    // canonicalize the minimum
    value = minimum;
    if (value != null) {
      dvalue = value.doubleValue();
      if (dvalue <= Double.NEGATIVE_INFINITY) {
        if (value instanceof Double) {
          negativeInfinity = value;
        } else {
          value = negativeInfinity = Double
              .valueOf(Double.NEGATIVE_INFINITY);
        }
      } else {
        if (dvalue >= Double.POSITIVE_INFINITY) {
          if (value instanceof Double) {
            positiveInfinity = value;
          } else {
            value = positiveInfinity = Double
                .valueOf(Double.POSITIVE_INFINITY);
          }
        } else {
          if (dvalue != dvalue) {
            if (value instanceof Double) {
              nan = value;
            } else {
              value = nan = Double.valueOf(Double.NaN);
            }
          }
        }
      }
    } else {
      value = negativeInfinity = Double.valueOf(Double.NEGATIVE_INFINITY);
    }
    this.m_minimum = value;

    // canonicalize the maximum
    value = maximum;
    if (value != null) {
      dvalue = value.doubleValue();
      if (dvalue <= Double.NEGATIVE_INFINITY) {
        if (negativeInfinity != null) {
          value = negativeInfinity;
        } else {
          if (value instanceof Double) {
            negativeInfinity = value;
          } else {
            value = negativeInfinity = Double
                .valueOf(Double.NEGATIVE_INFINITY);
          }
        }
      } else {
        if (dvalue >= Double.POSITIVE_INFINITY) {
          if (positiveInfinity != null) {
            value = positiveInfinity;
          } else {
            if (value instanceof Double) {
              positiveInfinity = value;
            } else {
              value = positiveInfinity = Double
                  .valueOf(Double.POSITIVE_INFINITY);
            }
          }
        } else {
          if (dvalue != dvalue) {
            if (nan != null) {
              value = nan;
            } else {
              if (value instanceof Double) {
                nan = value;
              } else {
                value = nan = Double.valueOf(Double.NaN);
              }
            }
          }
        }
      }
    } else {
      if (positiveInfinity != null) {
        value = positiveInfinity;
      } else {
        value = positiveInfinity = Double
            .valueOf(Double.POSITIVE_INFINITY);
      }
    }
    this.m_maximum = value;

    if (this.m_maximum.doubleValue() < this.m_minimum.doubleValue()) {
      throw new IllegalArgumentException(//
          "Maximum (" + maximum + //$NON-NLS-1$
              ", canonicalized to " + this.m_maximum + //$NON-NLS-1$
              ") cannot be smaller than minium (" + minimum + //$NON-NLS-1$
              ", canonicalized to " + this.m_minimum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }

    // canonicalize the arithmetic mean
    value = arithmeticMean;
    if (value != null) {
      dvalue = value.doubleValue();
      if (dvalue <= Double.NEGATIVE_INFINITY) {
        if (negativeInfinity != null) {
          value = negativeInfinity;
        } else {
          if (value instanceof Double) {
            negativeInfinity = value;
          } else {
            value = negativeInfinity = Double
                .valueOf(Double.NEGATIVE_INFINITY);
          }
        }
      } else {
        if (dvalue >= Double.POSITIVE_INFINITY) {
          if (positiveInfinity != null) {
            value = positiveInfinity;
          } else {
            if (value instanceof Double) {
              positiveInfinity = value;
            } else {
              value = positiveInfinity = Double
                  .valueOf(Double.POSITIVE_INFINITY);
            }
          }
        } else {
          if (dvalue != dvalue) {
            if (nan != null) {
              value = nan;
            } else {
              if (value instanceof Double) {
                nan = value;
              } else {
                value = nan = Double.valueOf(Double.NaN);
              }
            }
          }
        }
      }
    } else {
      if (nan != null) {
        value = nan;
      } else {
        value = nan = Double.valueOf(Double.NaN);
      }
    }
    this.m_arithmeticMean = value;

    if (this.m_arithmeticMean.doubleValue() < this.m_minimum
        .doubleValue()) {
      throw new IllegalArgumentException(//
          "Arithmetic mean (" + arithmeticMean + //$NON-NLS-1$
              ", canonicalized to " + this.m_arithmeticMean + //$NON-NLS-1$
              ") cannot be smaller than minium (" + minimum + //$NON-NLS-1$
              ", canonicalized to " + this.m_minimum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }

    if (this.m_arithmeticMean.doubleValue() > this.m_maximum
        .doubleValue()) {
      throw new IllegalArgumentException(//
          "Arithmetic mean (" + arithmeticMean + //$NON-NLS-1$
              ", canonicalized to " + this.m_arithmeticMean + //$NON-NLS-1$
              ") cannot be larger than maxium (" + maximum + //$NON-NLS-1$
              ", canonicalized to " + this.m_maximum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }

    // canonicalize standard deviation
    value = standardDeviation;
    if (value != null) {
      dvalue = value.doubleValue();
      if (dvalue <= Double.NEGATIVE_INFINITY) {
        if (negativeInfinity != null) {
          value = negativeInfinity;
        } else {
          if (value instanceof Double) {
            negativeInfinity = value;
          } else {
            value = negativeInfinity = Double
                .valueOf(Double.NEGATIVE_INFINITY);
          }
        }
      } else {
        if (dvalue >= Double.POSITIVE_INFINITY) {
          if (positiveInfinity != null) {
            value = positiveInfinity;
          } else {
            if (value instanceof Double) {
              positiveInfinity = value;
            } else {
              value = positiveInfinity = Double
                  .valueOf(Double.POSITIVE_INFINITY);
            }
          }
        } else {
          if (dvalue != dvalue) {
            if (nan != null) {
              value = nan;
            } else {
              if (value instanceof Double) {
                nan = value;
              } else {
                value = nan = Double.valueOf(Double.NaN);
              }
            }
          }
        }
      }
    } else {
      if (nan != null) {
        value = nan;
      } else {
        value = nan = Double.valueOf(Double.NaN);
      }
    }
    this.m_standardDeviation = value;

    // canonicalize the median
    value = median;
    if (value != null) {
      dvalue = value.doubleValue();
      if (dvalue <= Double.NEGATIVE_INFINITY) {
        if (negativeInfinity != null) {
          value = negativeInfinity;
        } else {
          if (value instanceof Double) {
            negativeInfinity = value;
          } else {
            value = negativeInfinity = Double
                .valueOf(Double.NEGATIVE_INFINITY);
          }
        }
      } else {
        if (dvalue >= Double.POSITIVE_INFINITY) {
          if (positiveInfinity != null) {
            value = positiveInfinity;
          } else {
            if (value instanceof Double) {
              positiveInfinity = value;
            } else {
              value = positiveInfinity = Double
                  .valueOf(Double.POSITIVE_INFINITY);
            }
          }
        } else {
          if (dvalue != dvalue) {
            if (nan != null) {
              value = nan;
            } else {
              if (value instanceof Double) {
                nan = value;
              } else {
                value = nan = Double.valueOf(Double.NaN);
              }
            }
          }
        }
      }
    } else {
      if (nan != null) {
        value = nan;
      } else {
        value = nan = Double.valueOf(Double.NaN);
      }
    }
    this.m_median = value;

    if (this.m_median.doubleValue() < this.m_minimum.doubleValue()) {
      throw new IllegalArgumentException(//
          "Median (" + median + //$NON-NLS-1$
              ", canonicalized to " + this.m_median + //$NON-NLS-1$
              ") cannot be smaller than minium (" + minimum + //$NON-NLS-1$
              ", canonicalized to " + this.m_minimum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }

    if (this.m_median.doubleValue() > this.m_maximum.doubleValue()) {
      throw new IllegalArgumentException(//
          "Median (" + median + //$NON-NLS-1$
              ", canonicalized to " + this.m_median + //$NON-NLS-1$
              ") cannot be larger than maxium (" + maximum + //$NON-NLS-1$
              ", canonicalized to " + this.m_maximum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }

    // canonicalize the inter-quartile range
    value = interQuartileRange;
    if (value != null) {
      dvalue = value.doubleValue();
      if (dvalue <= Double.NEGATIVE_INFINITY) {
        if (negativeInfinity != null) {
          value = negativeInfinity;
        } else {
          if (value instanceof Double) {
            negativeInfinity = value;
          } else {
            value = negativeInfinity = Double
                .valueOf(Double.NEGATIVE_INFINITY);
          }
        }
      } else {
        if (dvalue >= Double.POSITIVE_INFINITY) {
          if (positiveInfinity != null) {
            value = positiveInfinity;
          } else {
            if (value instanceof Double) {
              positiveInfinity = value;
            } else {
              value = positiveInfinity = Double
                  .valueOf(Double.POSITIVE_INFINITY);
            }
          }
        } else {
          if (dvalue != dvalue) {
            if (nan != null) {
              value = nan;
            } else {
              if (value instanceof Double) {
                nan = value;
              } else {
                value = nan = Double.valueOf(Double.NaN);
              }
            }
          }
        }
      }
    } else {
      if (nan != null) {
        value = nan;
      } else {
        value = nan = Double.valueOf(Double.NaN);
      }
    }
    this.m_interQuartileRange = value;

    // canonicalize the 5% quantile
    value = q05;
    if (value != null) {
      dvalue = value.doubleValue();
      if (dvalue <= Double.NEGATIVE_INFINITY) {
        if (negativeInfinity != null) {
          value = negativeInfinity;
        } else {
          if (value instanceof Double) {
            negativeInfinity = value;
          } else {
            value = negativeInfinity = Double
                .valueOf(Double.NEGATIVE_INFINITY);
          }
        }
      } else {
        if (dvalue >= Double.POSITIVE_INFINITY) {
          if (positiveInfinity != null) {
            value = positiveInfinity;
          } else {
            if (value instanceof Double) {
              positiveInfinity = value;
            } else {
              value = positiveInfinity = Double
                  .valueOf(Double.POSITIVE_INFINITY);
            }
          }
        } else {
          if (dvalue != dvalue) {
            if (nan != null) {
              value = nan;
            } else {
              if (value instanceof Double) {
                nan = value;
              } else {
                value = nan = Double.valueOf(Double.NaN);
              }
            }
          }
        }
      }
    } else {
      if (nan != null) {
        value = nan;
      } else {
        value = nan = Double.valueOf(Double.NaN);
      }
    }
    this.m_q05 = value;

    if (this.m_q05.doubleValue() < this.m_minimum.doubleValue()) {
      throw new IllegalArgumentException(//
          "5% quantile (" + q05 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q05 + //$NON-NLS-1$
              ") cannot be smaller than minium (" + minimum + //$NON-NLS-1$
              ", canonicalized to " + this.m_minimum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q05.doubleValue() > this.m_median.doubleValue()) {
      throw new IllegalArgumentException(//
          "5% quantile (" + q05 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q05 + //$NON-NLS-1$
              ") cannot be larger than median (" + median + //$NON-NLS-1$
              ", canonicalized to " + this.m_median + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q05.doubleValue() > this.m_maximum.doubleValue()) {
      throw new IllegalArgumentException(//
          "5% quantile (" + q05 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q05 + //$NON-NLS-1$
              ") cannot be larger than maxium (" + maximum + //$NON-NLS-1$
              ", canonicalized to " + this.m_maximum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }

    // canonicalize the 25% quantile
    value = q25;
    if (value != null) {
      dvalue = value.doubleValue();
      if (dvalue <= Double.NEGATIVE_INFINITY) {
        if (negativeInfinity != null) {
          value = negativeInfinity;
        } else {
          if (value instanceof Double) {
            negativeInfinity = value;
          } else {
            value = negativeInfinity = Double
                .valueOf(Double.NEGATIVE_INFINITY);
          }
        }
      } else {
        if (dvalue >= Double.POSITIVE_INFINITY) {
          if (positiveInfinity != null) {
            value = positiveInfinity;
          } else {
            if (value instanceof Double) {
              positiveInfinity = value;
            } else {
              value = positiveInfinity = Double
                  .valueOf(Double.POSITIVE_INFINITY);
            }
          }
        } else {
          if (dvalue != dvalue) {
            if (nan != null) {
              value = nan;
            } else {
              if (value instanceof Double) {
                nan = value;
              } else {
                value = nan = Double.valueOf(Double.NaN);
              }
            }
          }
        }
      }
    } else {
      if (nan != null) {
        value = nan;
      } else {
        value = nan = Double.valueOf(Double.NaN);
      }
    }
    this.m_q25 = value;

    if (this.m_q25.doubleValue() < this.m_minimum.doubleValue()) {
      throw new IllegalArgumentException(//
          "25% quantile (" + q25 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q25 + //$NON-NLS-1$
              ") cannot be smaller than minium (" + minimum + //$NON-NLS-1$
              ", canonicalized to " + this.m_minimum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q25.doubleValue() < this.m_q05.doubleValue()) {
      throw new IllegalArgumentException(//
          "25% quantile (" + q25 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q25 + //$NON-NLS-1$
              ") cannot be smaller than 5% quantile (" + q05 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q05 + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q25.doubleValue() > this.m_median.doubleValue()) {
      throw new IllegalArgumentException(//
          "25% quantile (" + q25 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q25 + //$NON-NLS-1$
              ") cannot be larger than median (" + median + //$NON-NLS-1$
              ", canonicalized to " + this.m_median + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q25.doubleValue() > this.m_maximum.doubleValue()) {
      throw new IllegalArgumentException(//
          "25% quantile (" + q25 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q25 + //$NON-NLS-1$
              ") cannot be larger than maxium (" + maximum + //$NON-NLS-1$
              ", canonicalized to " + this.m_maximum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }

    // canonicalize the 75% quantile
    value = q75;
    if (value != null) {
      dvalue = value.doubleValue();
      if (dvalue <= Double.NEGATIVE_INFINITY) {
        if (negativeInfinity != null) {
          value = negativeInfinity;
        } else {
          if (value instanceof Double) {
            negativeInfinity = value;
          } else {
            value = negativeInfinity = Double
                .valueOf(Double.NEGATIVE_INFINITY);
          }
        }
      } else {
        if (dvalue >= Double.POSITIVE_INFINITY) {
          if (positiveInfinity != null) {
            value = positiveInfinity;
          } else {
            if (value instanceof Double) {
              positiveInfinity = value;
            } else {
              value = positiveInfinity = Double
                  .valueOf(Double.POSITIVE_INFINITY);
            }
          }
        } else {
          if (dvalue != dvalue) {
            if (nan != null) {
              value = nan;
            } else {
              if (value instanceof Double) {
                nan = value;
              } else {
                value = nan = Double.valueOf(Double.NaN);
              }
            }
          }
        }
      }
    } else {
      if (nan != null) {
        value = nan;
      } else {
        value = nan = Double.valueOf(Double.NaN);
      }
    }
    this.m_q75 = value;

    if (this.m_q75.doubleValue() < this.m_minimum.doubleValue()) {
      throw new IllegalArgumentException(//
          "75% quantile (" + q75 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q75 + //$NON-NLS-1$
              ") cannot be smaller than minium (" + minimum + //$NON-NLS-1$
              ", canonicalized to " + this.m_minimum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q75.doubleValue() < this.m_q05.doubleValue()) {
      throw new IllegalArgumentException(//
          "75% quantile (" + q75 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q75 + //$NON-NLS-1$
              ") cannot be smaller than 5% quantile (" + q05 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q05 + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q75.doubleValue() < this.m_q25.doubleValue()) {
      throw new IllegalArgumentException(//
          "75% quantile (" + q75 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q75 + //$NON-NLS-1$
              ") cannot be smaller than 25% quantile (" + q25 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q25 + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q75.doubleValue() < this.m_median.doubleValue()) {
      throw new IllegalArgumentException(//
          "75% quantile (" + q75 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q75 + //$NON-NLS-1$
              ") cannot be smaller than median (" + median + //$NON-NLS-1$
              ", canonicalized to " + this.m_median + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q75.doubleValue() > this.m_maximum.doubleValue()) {
      throw new IllegalArgumentException(//
          "75% quantile (" + q75 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q75 + //$NON-NLS-1$
              ") cannot be larger than maxium (" + maximum + //$NON-NLS-1$
              ", canonicalized to " + this.m_maximum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }

    // canonicalize the 95% quantile
    value = q95;
    if (value != null) {
      dvalue = value.doubleValue();
      if (dvalue <= Double.NEGATIVE_INFINITY) {
        if (negativeInfinity != null) {
          value = negativeInfinity;
        } else {
          if (!(value instanceof Double)) {
            value = Double.valueOf(Double.NEGATIVE_INFINITY);
          }
        }
      } else {
        if (dvalue >= Double.POSITIVE_INFINITY) {
          if (positiveInfinity != null) {
            value = positiveInfinity;
          } else {
            if (!(value instanceof Double)) {
              value = Double.valueOf(Double.POSITIVE_INFINITY);
            }
          }
        } else {
          if (dvalue != dvalue) {
            if (nan != null) {
              value = nan;
            } else {
              if (!(value instanceof Double)) {
                value = Double.valueOf(Double.NaN);
              }
            }
          }
        }
      }
    } else {
      if (nan != null) {
        value = nan;
      } else {
        value = Double.valueOf(Double.NaN);
      }
    }
    this.m_q95 = value;

    if (this.m_q95.doubleValue() < this.m_minimum.doubleValue()) {
      throw new IllegalArgumentException(//
          "95% quantile (" + q95 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q95 + //$NON-NLS-1$
              ") cannot be smaller than minium (" + minimum + //$NON-NLS-1$
              ", canonicalized to " + this.m_minimum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q95.doubleValue() < this.m_q05.doubleValue()) {
      throw new IllegalArgumentException(//
          "95% quantile (" + q95 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q95 + //$NON-NLS-1$
              ") cannot be smaller than 5% quantile (" + q05 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q05 + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q95.doubleValue() < this.m_q25.doubleValue()) {
      throw new IllegalArgumentException(//
          "75% quantile (" + q95 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q95 + //$NON-NLS-1$
              ") cannot be smaller than 25% quantile (" + q25 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q25 + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q95.doubleValue() < this.m_median.doubleValue()) {
      throw new IllegalArgumentException(//
          "95% quantile (" + q95 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q95 + //$NON-NLS-1$
              ") cannot be smaller than median (" + median + //$NON-NLS-1$
              ", canonicalized to " + this.m_median + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q95.doubleValue() < this.m_q75.doubleValue()) {
      throw new IllegalArgumentException(//
          "95% quantile (" + q95 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q95 + //$NON-NLS-1$
              ") cannot be smaller than 75% quantile (" + q75 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q75 + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
    if (this.m_q95.doubleValue() > this.m_maximum.doubleValue()) {
      throw new IllegalArgumentException(//
          "95% quantile (" + q95 + //$NON-NLS-1$
              ", canonicalized to " + this.m_q95 + //$NON-NLS-1$
              ") cannot be larger than maxium (" + maximum + //$NON-NLS-1$
              ", canonicalized to " + this.m_maximum + //$NON-NLS-1$
              ")."); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public final long getSampleSize() {
    return this.m_sampleSize;
  }

  /** {@inheritDoc} */
  @Override
  public final Number getMinimum() {
    return this.m_minimum;
  }

  /** {@inheritDoc} */
  @Override
  public final Number getMaximum() {
    return this.m_maximum;
  }

  /** {@inheritDoc} */
  @Override
  public final Number getArithmeticMean() {
    return this.m_arithmeticMean;
  }

  /** {@inheritDoc} */
  @Override
  public final Number getStandardDeviation() {
    return this.m_standardDeviation;
  }

  /** {@inheritDoc} */
  @Override
  public final Number getMedian() {
    return this.m_median;
  }

  /** {@inheritDoc} */
  @Override
  public final Number get05Quantile() {
    return this.m_q05;
  }

  /** {@inheritDoc} */
  @Override
  public final Number get25Quantile() {
    return this.m_q25;
  }

  /** {@inheritDoc} */
  @Override
  public final Number get75Quantile() {
    return this.m_q75;
  }

  /** {@inheritDoc} */
  @Override
  public final Number get95Quantile() {
    return this.m_q95;
  }

  /** {@inheritDoc} */
  @Override
  public final Number getInterQuartileRange() {
    return this.m_interQuartileRange;
  }
}
