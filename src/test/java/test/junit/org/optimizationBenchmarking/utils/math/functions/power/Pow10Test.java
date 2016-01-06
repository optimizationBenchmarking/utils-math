package test.junit.org.optimizationBenchmarking.utils.math.functions.power;

import org.optimizationBenchmarking.utils.math.functions.MathematicalFunction;
import org.optimizationBenchmarking.utils.math.functions.power.Pow10;

import shared.junit.org.optimizationBenchmarking.utils.math.functions.FunctionTestCase;
import shared.junit.org.optimizationBenchmarking.utils.math.functions.MathematicalFunctionTest;

/** The test of the pow10 function */
public final class Pow10Test extends MathematicalFunctionTest {

  /** the test cases */
  private static final FunctionTestCase[] TEST_CASES = { //
      new FunctionTestCase(1, 0), //
      new FunctionTestCase(10, 1), //
      new FunctionTestCase(100, 2), //
      new FunctionTestCase(1_000, 3), //
      new FunctionTestCase(10_000, 4), //
      new FunctionTestCase(100_000, 5), //
      new FunctionTestCase(1_000_000, 6), //
      new FunctionTestCase(10_000_000, 7), //
      new FunctionTestCase(100_000_000, 8), //
      new FunctionTestCase(1_000_000_000, 9), //
      new FunctionTestCase(10_000_000_000L, 10L), //
      new FunctionTestCase(100_000_000_000L, 11L), //
      new FunctionTestCase(1_000_000_000_000L, 12L), //
      new FunctionTestCase(10_000_000_000_000L, 13L), //
      new FunctionTestCase(100_000_000_000_000L, 14L), //
      new FunctionTestCase(1_000_000_000_000_000L, 15L), //
      new FunctionTestCase(10_000_000_000_000_000L, 16L), //
      new FunctionTestCase(100_000_000_000_000_000L, 17L), //
      new FunctionTestCase(1_000_000_000_000_000_000L, 18L), //

      new FunctionTestCase((1d / 10), -1), //
      new FunctionTestCase((1d / 100), -2), //
      new FunctionTestCase((1d / 1_000), -3), //
      new FunctionTestCase((1d / 10_000), -4), //
      new FunctionTestCase((1d / 100_000), -5), //
      new FunctionTestCase((1d / 1_000_000), -6), //
      new FunctionTestCase((1d / 10_000_000), -7), //
      new FunctionTestCase((1d / 100_000_000), -8), //
      new FunctionTestCase((1d / 1_000_000_000), -9), //
      new FunctionTestCase((1d / 10_000_000_000L), -10), //
      new FunctionTestCase((1d / 100_000_000_000L), -11), //
      new FunctionTestCase((1d / 1_000_000_000_000L), -12), //
      new FunctionTestCase((1d / 10_000_000_000_000L), -13), //
      new FunctionTestCase((1d / 100_000_000_000_000L), -14), //
      new FunctionTestCase((1d / 1_000_000_000_000_000L), -15), //
      new FunctionTestCase((1d / 10_000_000_000_000_000L), -16), //
      new FunctionTestCase((1d / 100_000_000_000_000_000L), -17), //
      new FunctionTestCase((1d / 1_000_000_000_000_000_000L), -18), //

      new FunctionTestCase(Math.pow(10, Math.PI), Math.PI),//
  };

  /** create */
  public Pow10Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final MathematicalFunction getFunction() {
    return Pow10.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean isCommutative() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final FunctionTestCase[] getTestCases() {
    return Pow10Test.TEST_CASES;
  }
}
