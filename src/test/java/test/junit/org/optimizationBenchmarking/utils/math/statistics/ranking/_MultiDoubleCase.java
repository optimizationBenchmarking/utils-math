package test.junit.org.optimizationBenchmarking.utils.math.statistics.ranking;

import java.util.Random;

import org.junit.Assert;
import org.optimizationBenchmarking.utils.collections.ArrayUtils;
import org.optimizationBenchmarking.utils.math.NumericalTypes;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.ArithmeticMeanAggregate;
import org.optimizationBenchmarking.utils.math.statistics.ranking.RankingStrategy;

/** A ranking use case based on {@code double} values */
final class _MultiDoubleCase {

  /** the strategy */
  final RankingStrategy m_strategy;

  /** the input */
  final double[][] m_input;

  /** the expected mean output */
  final double[] m_means;

  /**
   * the input data as {@code long}s, or {@code null} if it could not be
   * converted
   */
  final long[][] m_inputLong;

  /**
   * Create a case where we rank {@code double}s.
   *
   * @param strategy
   *          the strategy
   * @param in
   *          the input data
   * @param means
   *          the expected output means
   */
  _MultiDoubleCase(final RankingStrategy strategy, final double[][] in,
      final double[] means) {
    super();

    int index;

    Assert.assertNotNull(strategy);
    Assert.assertNotNull(in);
    Assert.assertNotNull(means);
    Assert.assertEquals(in.length, means.length);

    this.m_strategy = strategy;
    this.m_input = in;
    this.m_means = means;

    asLongs: {
      for (final double ds[] : in) {
        for (final double d : ds) {
          if (!(NumericalTypes.isLong(d))) {
            break asLongs;
          }
        }
      }
      index = in.length;
      this.m_inputLong = new long[index][];
      for (; (--index) >= 0;) {
        this.m_inputLong[index] = ArrayUtils.doublesToLongs(in[index]);
      }
      return;
    }
    this.m_inputLong = null;
  }

  /**
   * Assert that the {@code double}s are ranked correctly
   *
   * @param rand
   *          a random number generator
   */
  final void _assertDoubles(final Random rand) {
    final double[][] input;
    final double[] expected;
    final ArithmeticMeanAggregate[] out;
    int i, j, k, z;
    double t;

    out = new ArithmeticMeanAggregate[this.m_input.length];
    for (i = out.length; (--i) >= 0;) {
      out[i] = new ArithmeticMeanAggregate();
    }
    input = this.m_input.clone();
    expected = this.m_means;

    for (i = 0; i < (100 * input.length); i++) {
      this.m_strategy.rank(input, out);
      for (j = out.length; (--j) >= 0;) {
        Assert.assertEquals(expected[j], out[j].doubleValue(),
            Double.MIN_VALUE);
        out[j].reset();
      }

      if (input.length <= 1) {
        break;
      }

      z = rand.nextInt(input.length);
      if (input[z].length > 1) {
        j = rand.nextInt(input[z].length);
        do {
          k = rand.nextInt(input[z].length);
        } while (k == j);

        t = input[z][j];
        input[z][j] = input[z][k];
        input[z][k] = t;
      }
    }
  }
}
