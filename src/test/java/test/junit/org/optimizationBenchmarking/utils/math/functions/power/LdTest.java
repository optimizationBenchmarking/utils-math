package test.junit.org.optimizationBenchmarking.utils.math.functions.power;

import org.optimizationBenchmarking.utils.math.functions.MathematicalFunction;
import org.optimizationBenchmarking.utils.math.functions.power.Ld;

import shared.junit.org.optimizationBenchmarking.utils.math.functions.FunctionTestCase;
import shared.junit.org.optimizationBenchmarking.utils.math.functions.MathematicalFunctionTest;

/** The test of the ld function */
public final class LdTest extends MathematicalFunctionTest {

  /** the test cases */
  private static final FunctionTestCase[] TEST_CASES = { //
      new FunctionTestCase(0, 1), //
      new FunctionTestCase(1, 2), //
      new FunctionTestCase(2, 4), //
      new FunctionTestCase(3, 8), //
      new FunctionTestCase(4, 16), //
      new FunctionTestCase(5, 32), //
      new FunctionTestCase(6, 64), //
      new FunctionTestCase(7, 128), //
      new FunctionTestCase(8, 256), //
      new FunctionTestCase(9, 512), //
      new FunctionTestCase(10, 1024), //
      new FunctionTestCase(11, 2048), //
      new FunctionTestCase(12, 4096), //
      new FunctionTestCase(13, 8192), //
      new FunctionTestCase(14, 16384), //
      new FunctionTestCase(15, (1 << 15)), //
      new FunctionTestCase(16, (1 << 16)), //
      new FunctionTestCase(17, (1 << 17)), //
      new FunctionTestCase(18, (1 << 18)), //
      new FunctionTestCase(19, (1 << 19)), //
      new FunctionTestCase(20, (1 << 20)), //
      new FunctionTestCase(21, (1 << 21)), //
      new FunctionTestCase(22, (1 << 22)), //
      new FunctionTestCase(23, (1 << 23)), //
      new FunctionTestCase(24, (1 << 24)), //
      new FunctionTestCase(25, (1 << 25)), //
      new FunctionTestCase(26, (1 << 26)), //
      new FunctionTestCase(27, (1 << 27)), //
      new FunctionTestCase(28, (1 << 28)), //
      new FunctionTestCase(29, (1 << 29)), //
      new FunctionTestCase(30, (1 << 30)), //
      new FunctionTestCase(31, (1L << 31)), //
      new FunctionTestCase(32, (1L << 32)), //
      new FunctionTestCase(33, (1L << 33)), //
      new FunctionTestCase(34, (1L << 34)), //
      new FunctionTestCase(35, (1L << 35)), //
      new FunctionTestCase(36, (1L << 36)), //
      new FunctionTestCase(37, (1L << 37)), //
      new FunctionTestCase(38, (1L << 38)), //
      new FunctionTestCase(39, (1L << 39)), //
      new FunctionTestCase(40, (1L << 40)), //
      new FunctionTestCase(59, (1L << 59)), //
      new FunctionTestCase(60, (1L << 60)), //
      new FunctionTestCase(61, (1L << 61)), //
      new FunctionTestCase(62, (1L << 62)), //

      new FunctionTestCase(-1, (1d / 2)), //
      new FunctionTestCase(-2, (1d / 4)), //
      new FunctionTestCase(-3, (1d / 8)), //
      new FunctionTestCase(-4, (1d / 16)), //
      new FunctionTestCase(-5, (1d / 32)), //
      new FunctionTestCase(-29, (1d / (1 << 29))), //
      new FunctionTestCase(-30, (1d / (1 << 30))), //
      new FunctionTestCase(-31, (1d / (1L << 31))), //
      new FunctionTestCase(-32, (1d / (1L << 32))), //
      new FunctionTestCase(-33, (1d / (1L << 33))),//
  };

  /** create */
  public LdTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final MathematicalFunction getFunction() {
    return Ld.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean isCommutative() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final FunctionTestCase[] getTestCases() {
    return LdTest.TEST_CASES;
  }
}
