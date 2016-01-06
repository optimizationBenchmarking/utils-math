package test.junit.org.optimizationBenchmarking.utils.math.functions.combinatoric;

import org.optimizationBenchmarking.utils.math.functions.MathematicalFunction;
import org.optimizationBenchmarking.utils.math.functions.combinatoric.BinomialCoefficient;

import shared.junit.org.optimizationBenchmarking.utils.math.functions.FunctionTestCase;
import shared.junit.org.optimizationBenchmarking.utils.math.functions.MathematicalFunctionTest;

/**
 * The binomial coefficient test.
 */
public class BinomialCoefficientTest extends MathematicalFunctionTest {

  /** the test cases */
  private static final FunctionTestCase[] TEST_CASES = { //
      new FunctionTestCase(1, 0, 0), //

      new FunctionTestCase(1, 1, 0), //
      new FunctionTestCase(1, 1, 1), //

      new FunctionTestCase(1, 2, 0), //
      new FunctionTestCase(2, 2, 1), //

      new FunctionTestCase(1, 3, 0), //
      new FunctionTestCase(3, 3, 1), //
      new FunctionTestCase(3, 3, 2), //
      new FunctionTestCase(1, 3, 3), //

      new FunctionTestCase(1, 4, 0), //
      new FunctionTestCase(4, 4, 1), //
      new FunctionTestCase(6, 4, 2), //
      new FunctionTestCase(4, 4, 3), //
      new FunctionTestCase(1, 4, 4), //

      new FunctionTestCase(1, 5, 0), //
      new FunctionTestCase(5, 5, 1), //
      new FunctionTestCase(10, 5, 2), //
      new FunctionTestCase(10, 5, 3), //
      new FunctionTestCase(5, 5, 4), //
      new FunctionTestCase(1, 5, 5), //

      new FunctionTestCase(1, 6, 0), //
      new FunctionTestCase(6, 6, 1), //
      new FunctionTestCase(15, 6, 2), //
      new FunctionTestCase(20, 6, 3), //
      new FunctionTestCase(15, 6, 4), //
      new FunctionTestCase(6, 6, 5), //
      new FunctionTestCase(1, 6, 6), //

      new FunctionTestCase(1, 7, 0), //
      new FunctionTestCase(7, 7, 1), //
      new FunctionTestCase(21, 7, 2), //
      new FunctionTestCase(35, 7, 3), //
      new FunctionTestCase(35, 7, 4), //
      new FunctionTestCase(21, 7, 5), //
      new FunctionTestCase(7, 7, 6), //
      new FunctionTestCase(1, 7, 7), //

      new FunctionTestCase(1, 8, 0), //
      new FunctionTestCase(8, 8, 1), //
      new FunctionTestCase(28, 8, 2), //
      new FunctionTestCase(56, 8, 3), //
      new FunctionTestCase(70, 8, 4), //
      new FunctionTestCase(56, 8, 5), //
      new FunctionTestCase(28, 8, 6), //
      new FunctionTestCase(8, 8, 7), //
      new FunctionTestCase(1, 8, 8), //

      new FunctionTestCase(1, 9, 0), //
      new FunctionTestCase(9, 9, 1), //
      new FunctionTestCase(36, 9, 2), //
      new FunctionTestCase(84, 9, 3), //
      new FunctionTestCase(126, 9, 4), //
      new FunctionTestCase(126, 9, 5), //
      new FunctionTestCase(84, 9, 6), //
      new FunctionTestCase(36, 9, 7), //
      new FunctionTestCase(9, 9, 8), //
      new FunctionTestCase(1, 9, 9), //

      new FunctionTestCase(1, 10, 0), //
      new FunctionTestCase(10, 10, 1), //
      new FunctionTestCase(45, 10, 2), //
      new FunctionTestCase(120, 10, 3), //
      new FunctionTestCase(210, 10, 4), //
      new FunctionTestCase(252, 10, 5), //
      new FunctionTestCase(210, 10, 6), //
      new FunctionTestCase(120, 10, 7), //
      new FunctionTestCase(45, 10, 8), //
      new FunctionTestCase(10, 10, 9), //
      new FunctionTestCase(1, 10, 10), //

      new FunctionTestCase(1, 11, 0), //
      new FunctionTestCase(11, 11, 1), //
      new FunctionTestCase(55, 11, 2), //
      new FunctionTestCase(165, 11, 3), //
      new FunctionTestCase(330, 11, 4), //
      new FunctionTestCase(462, 11, 5), //
      new FunctionTestCase(462, 11, 6), //
      new FunctionTestCase(330, 11, 7), //
      new FunctionTestCase(165, 11, 8), //
      new FunctionTestCase(55, 11, 9), //
      new FunctionTestCase(11, 11, 10), //
      new FunctionTestCase(1, 11, 11), //

      new FunctionTestCase(1, 12, 0), //
      new FunctionTestCase(12, 12, 1), //
      new FunctionTestCase(66, 12, 2), //
      new FunctionTestCase(220, 12, 3), //
      new FunctionTestCase(495, 12, 4), //
      new FunctionTestCase(792, 12, 5), //
      new FunctionTestCase(924, 12, 6), //
      new FunctionTestCase(792, 12, 7), //
      new FunctionTestCase(495, 12, 8), //
      new FunctionTestCase(220, 12, 9), //
      new FunctionTestCase(66, 12, 10), //
      new FunctionTestCase(12, 12, 11), //
      new FunctionTestCase(1, 12, 12), //

      new FunctionTestCase(1, 13, 0), //
      new FunctionTestCase(13, 13, 1), //
      new FunctionTestCase(78, 13, 2), //
      new FunctionTestCase(286, 13, 3), //
      new FunctionTestCase(715, 13, 4), //
      new FunctionTestCase(1287, 13, 5), //
      new FunctionTestCase(1716, 13, 6), //
      new FunctionTestCase(1716, 13, 7), //
      new FunctionTestCase(1287, 13, 8), //
      new FunctionTestCase(715, 13, 9), //
      new FunctionTestCase(286, 13, 10), //
      new FunctionTestCase(78, 13, 11), //
      new FunctionTestCase(13, 13, 12), //
      new FunctionTestCase(1, 13, 13), //

      new FunctionTestCase(1, 14, 0), //
      new FunctionTestCase(14, 14, 1), //
      new FunctionTestCase(91, 14, 2), //
      new FunctionTestCase(364, 14, 3), //
      new FunctionTestCase(1001, 14, 4), //
      new FunctionTestCase(2002, 14, 5), //
      new FunctionTestCase(3003, 14, 6), //
      new FunctionTestCase(3432, 14, 7), //
      new FunctionTestCase(3003, 14, 8), //
      new FunctionTestCase(2002, 14, 9), //
      new FunctionTestCase(1001, 14, 10), //
      new FunctionTestCase(364, 14, 11), //
      new FunctionTestCase(91, 14, 12), //
      new FunctionTestCase(14, 14, 13), //
      new FunctionTestCase(1, 14, 14),//
  };

  /** create */
  public BinomialCoefficientTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public MathematicalFunction getFunction() {
    return BinomialCoefficient.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  protected boolean isCommutative() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public FunctionTestCase[] getTestCases() {
    return BinomialCoefficientTest.TEST_CASES;
  }

}
