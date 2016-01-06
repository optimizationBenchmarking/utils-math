package test.junit.org.optimizationBenchmarking.utils.math.functions.power;

import org.optimizationBenchmarking.utils.math.functions.MathematicalFunction;
import org.optimizationBenchmarking.utils.math.functions.power.Lg;

import shared.junit.org.optimizationBenchmarking.utils.math.functions.FunctionTestCase;
import shared.junit.org.optimizationBenchmarking.utils.math.functions.MathematicalFunctionTest;

/** The test of the lg function */
public final class LgTest extends MathematicalFunctionTest {

  /** the test cases */
  private static final FunctionTestCase[] TEST_CASES = { //
      new FunctionTestCase(0, 1), //
      new FunctionTestCase(1, 10), //
      new FunctionTestCase(2, 100), //
      new FunctionTestCase(3, 1_000), //
      new FunctionTestCase(4, 10_000), //
      new FunctionTestCase(5, 100_000), //
      new FunctionTestCase(6, 1_000_000), //
      new FunctionTestCase(7, 10_000_000), //
      new FunctionTestCase(8, 100_000_000), //
      new FunctionTestCase(9, 1_000_000_000), //
      new FunctionTestCase(10, 10_000_000_000L), //
      new FunctionTestCase(11, 100_000_000_000L), //
      new FunctionTestCase(12, 1_000_000_000_000L), //
      new FunctionTestCase(13, 10_000_000_000_000L), //
      new FunctionTestCase(14, 100_000_000_000_000L), //
      new FunctionTestCase(15, 1_000_000_000_000_000L), //
      new FunctionTestCase(16, 10_000_000_000_000_000L), //
      new FunctionTestCase(17, 100_000_000_000_000_000L), //
      new FunctionTestCase(18, 1_000_000_000_000_000_000L), //

      new FunctionTestCase(-1, (1d / 10)), //
      new FunctionTestCase(-2, (1d / 100)), //
      new FunctionTestCase(-3, (1d / 1_000)), //
      new FunctionTestCase(-4, (1d / 10_000)), //
      new FunctionTestCase(-5, (1d / 100_000)), //
      new FunctionTestCase(-6, (1d / 1_000_000)), //
      new FunctionTestCase(-7, (1d / 10_000_000)), //
      new FunctionTestCase(-8, (1d / 100_000_000)), //
      new FunctionTestCase(-9, (1d / 1_000_000_000)), //
      new FunctionTestCase(-10, (1d / 10_000_000_000L)), //
      new FunctionTestCase(-11, (1d / 100_000_000_000L)), //
      new FunctionTestCase(-12, (1d / 1_000_000_000_000L)), //
      new FunctionTestCase(-13, (1d / 10_000_000_000_000L)), //
      new FunctionTestCase(-14, (1d / 100_000_000_000_000L)), //
      new FunctionTestCase(-15, (1d / 1_000_000_000_000_000L)), //
      new FunctionTestCase(-16, (1d / 10_000_000_000_000_000L)), //
      new FunctionTestCase(-17, (1d / 100_000_000_000_000_000L)), //
      new FunctionTestCase(-18, (1d / 1_000_000_000_000_000_000L)), //

      new FunctionTestCase(Math.log10(23d), 23d), //
      new FunctionTestCase(Math.log10(100001d), 100001d), };

  /** create */
  public LgTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final MathematicalFunction getFunction() {
    return Lg.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean isCommutative() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final FunctionTestCase[] getTestCases() {
    return LgTest.TEST_CASES;
  }
}
