package test.junit.org.optimizationBenchmarking.utils.math.functions.arithmetic;

import org.optimizationBenchmarking.utils.math.NumericalTypes;
import org.optimizationBenchmarking.utils.math.functions.MathematicalFunction;
import org.optimizationBenchmarking.utils.math.functions.arithmetic.Add3;

import shared.junit.org.optimizationBenchmarking.utils.math.functions.FunctionTestCase;
import shared.junit.org.optimizationBenchmarking.utils.math.functions.MathematicalFunctionTest;

/** The test of the add 3 function */
public final class Add3Test extends MathematicalFunctionTest {

  /** the test cases */
  private static final FunctionTestCase[] TEST_CASES = { //
      //
      new FunctionTestCase(1, 1, 0, 0), //
      //
      new FunctionTestCase(6, 1, 2, 3), //
      //
      new FunctionTestCase(Long.MAX_VALUE, 1, -1L, Long.MAX_VALUE), //
      //
      new FunctionTestCase(Long.MIN_VALUE, 1, -1L, Long.MIN_VALUE), //
      //
      new FunctionTestCase(Long.MIN_VALUE, Long.MIN_VALUE,
          (Long.MIN_VALUE + 1L), Long.MAX_VALUE), //
      //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE,
          (-Long.MAX_VALUE)), //
      //
      new FunctionTestCase(1, (NumericalTypes.MAX_DOUBLE_LONG + 1L),
          -(NumericalTypes.MAX_DOUBLE_LONG + 1L), 1), //
      //
      new FunctionTestCase(123, (NumericalTypes.MAX_DOUBLE_LONG + 23L),
          -(NumericalTypes.MAX_DOUBLE_LONG + 20L), 120), //
      //
      new FunctionTestCase(1, -Double.MAX_VALUE, 1, Double.MAX_VALUE), //
      //
      new FunctionTestCase(Double.POSITIVE_INFINITY, Double.MAX_VALUE,
          Double.MAX_VALUE, Double.POSITIVE_INFINITY),//
  };

  /** create */
  public Add3Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final MathematicalFunction getFunction() {
    return Add3.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean isCommutative() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public final FunctionTestCase[] getTestCases() {
    return Add3Test.TEST_CASES;
  }
}
