package test.junit.org.optimizationBenchmarking.utils.math.functions.arithmetic;

import org.junit.Ignore;
import org.optimizationBenchmarking.utils.math.functions.MathematicalFunction;
import org.optimizationBenchmarking.utils.math.functions.arithmetic.SaturatingAdd;

import shared.junit.org.optimizationBenchmarking.utils.math.functions.FunctionTestCase;
import shared.junit.org.optimizationBenchmarking.utils.math.functions.MathematicalFunctionTest;

/** The test of the saturating add function */
public final class SaturatingAddTest extends MathematicalFunctionTest {

  /** the test cases */
  private static final FunctionTestCase[] TEST_CASES = { //
      new FunctionTestCase(1, 1, 0), //
      new FunctionTestCase(1, 0, 1), //
      new FunctionTestCase(4, 2, 2), //
      new FunctionTestCase(5, 2, 3), //
      new FunctionTestCase(3, 4, -1), //
      new FunctionTestCase(2, 4, -2), //
      new FunctionTestCase(-1, -4, 3), //
      //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE, 0L), //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE, 1L), //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE, 2L), //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE), //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE,
          (Long.MAX_VALUE - 1L)), //
      //
      new FunctionTestCase(Long.MIN_VALUE, Long.MIN_VALUE, 0L), //
      new FunctionTestCase(Long.MIN_VALUE, Long.MIN_VALUE, -1L), //
      new FunctionTestCase(Long.MIN_VALUE, Long.MIN_VALUE, -2L), //
      new FunctionTestCase(Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE), //
      new FunctionTestCase(Long.MIN_VALUE, Long.MIN_VALUE,
          (Long.MIN_VALUE + 1L)), //
      //
      new FunctionTestCase((Long.MAX_VALUE - 1L), Long.MAX_VALUE, -1L), //
      new FunctionTestCase((Long.MAX_VALUE - 2L), Long.MAX_VALUE, -2L), //
      new FunctionTestCase((Long.MIN_VALUE + 1L), Long.MIN_VALUE, 1L), //
      new FunctionTestCase((Long.MIN_VALUE + 2L), Long.MIN_VALUE, 2L), //
      //
      new FunctionTestCase(-1L, Long.MAX_VALUE, Long.MIN_VALUE), //
      new FunctionTestCase(0L, Long.MAX_VALUE, (Long.MIN_VALUE + 1L)), //
      new FunctionTestCase(1L, Long.MAX_VALUE, (Long.MIN_VALUE + 2L)), //
      new FunctionTestCase(-2L, (Long.MAX_VALUE - 1L), Long.MIN_VALUE), //
      new FunctionTestCase(-3L, (Long.MAX_VALUE - 2L), Long.MIN_VALUE),//
  };

  /** create */
  public SaturatingAddTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final MathematicalFunction getFunction() {
    return SaturatingAdd.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean isCommutative() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public final FunctionTestCase[] getTestCases() {
    return SaturatingAddTest.TEST_CASES;
  }

  /** {@inheritDoc} */
  @Ignore
  @Override
  public void testTestCasesAllDouble() {
    //
  }

  /** {@inheritDoc} */
  @Ignore
  @Override
  public void testTestCasesAllFloat() {
    //
  }

  /** {@inheritDoc} */
  @Ignore
  @Override
  public void testTestCasesInLongOutDouble() {
    //
  }

  /** {@inheritDoc} */
  @Ignore
  @Override
  public void testTestCasesInIntOutDouble() {
    //
  }
}
