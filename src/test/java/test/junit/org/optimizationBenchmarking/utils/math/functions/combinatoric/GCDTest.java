package test.junit.org.optimizationBenchmarking.utils.math.functions.combinatoric;

import org.optimizationBenchmarking.utils.math.functions.MathematicalFunction;
import org.optimizationBenchmarking.utils.math.functions.combinatoric.GCD;

import shared.junit.org.optimizationBenchmarking.utils.math.functions.FunctionTestCase;
import shared.junit.org.optimizationBenchmarking.utils.math.functions.MathematicalFunctionTest;

/** The test of the GCD function */
public class GCDTest extends MathematicalFunctionTest {

  /** the test cases */
  private static final FunctionTestCase[] TEST_CASES = { //
      new FunctionTestCase(1, 1, 1), //
      new FunctionTestCase(1, 1, 2), //
      new FunctionTestCase(2, 2, 2), //
      new FunctionTestCase(1, 2, 3), //
      new FunctionTestCase(1, 4, 1), //
      new FunctionTestCase(2, 4, 2), //
      new FunctionTestCase(1, 4, 3), //
      new FunctionTestCase(4, 4, 4), //
      new FunctionTestCase(1, 1, 5), //
      new FunctionTestCase(1, 2, 5), //
      new FunctionTestCase(1, 3, 5), //
      new FunctionTestCase(1, 4, 5), //
      new FunctionTestCase(5, 5, 5), //
      new FunctionTestCase(1, 1, 6), //
      new FunctionTestCase(2, 2, 6), //
      new FunctionTestCase(2, 4, 6), //
      new FunctionTestCase(3, 3, 6), //
      new FunctionTestCase(1, 5, 6), //
      new FunctionTestCase(6, 6, 6), //
      new FunctionTestCase(1, 1243, 2000), //
      new FunctionTestCase(18, 203400, 1242234), //
      new FunctionTestCase(23, 46, 23), //
      new FunctionTestCase(23, 46, -23), //
      new FunctionTestCase(23, -46, -23), //
      new FunctionTestCase(23, -46, 23), //
      new FunctionTestCase(3, -456, 123), //
      new FunctionTestCase(4, 2223443512332L, -3455456), //
      new FunctionTestCase(14, -1245723466666L, 8237752234684L), //
      new FunctionTestCase(2, -1723466666L, 8237752234684L), //
      new FunctionTestCase(12, -17234666664L, 234684L), //
      new FunctionTestCase(1, 4181, 6765), //
      new FunctionTestCase(1, 4181, 2584), //
      new FunctionTestCase(1, 1597, 2584), //
      new FunctionTestCase(1, 24157817, 39088169),//
  };

  /** create */
  public GCDTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public MathematicalFunction getFunction() {
    return GCD.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  protected boolean isCommutative() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public FunctionTestCase[] getTestCases() {
    return GCDTest.TEST_CASES;
  }
}
