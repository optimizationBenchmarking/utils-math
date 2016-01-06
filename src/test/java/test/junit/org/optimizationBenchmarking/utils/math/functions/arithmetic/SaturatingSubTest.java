package test.junit.org.optimizationBenchmarking.utils.math.functions.arithmetic;

import org.junit.Ignore;
import org.optimizationBenchmarking.utils.math.functions.MathematicalFunction;
import org.optimizationBenchmarking.utils.math.functions.arithmetic.SaturatingSub;

import shared.junit.org.optimizationBenchmarking.utils.math.functions.FunctionTestCase;
import shared.junit.org.optimizationBenchmarking.utils.math.functions.MathematicalFunctionTest;

/** The test of the saturating subtract function */
public final class SaturatingSubTest extends MathematicalFunctionTest {

  /** the test cases */
  private static final FunctionTestCase[] TEST_CASES = { //
      new FunctionTestCase(1, 1, 0), //
      new FunctionTestCase(-1, 0, 1), //
      new FunctionTestCase(0, 2, 2), //
      new FunctionTestCase(-1, 2, 3), //
      new FunctionTestCase(5, 4, -1), //
      new FunctionTestCase(6, 4, -2), //
      new FunctionTestCase(-7, -4, 3), //
      //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE, 0L), //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE, -1L), //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE, -2L), //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE,
          -Long.MAX_VALUE), //
      new FunctionTestCase(Long.MAX_VALUE, Long.MAX_VALUE, Long.MIN_VALUE), //
      new FunctionTestCase(0L, Long.MAX_VALUE, Long.MAX_VALUE), //
      new FunctionTestCase(1L, Long.MAX_VALUE, (Long.MAX_VALUE - 1L)), //
      //
      new FunctionTestCase(Long.MIN_VALUE, Long.MIN_VALUE, 0L), //
      new FunctionTestCase(Long.MIN_VALUE, Long.MIN_VALUE, 1L), //
      new FunctionTestCase(Long.MIN_VALUE, Long.MIN_VALUE, 2L), //
      new FunctionTestCase(Long.MIN_VALUE, Long.MIN_VALUE, Long.MAX_VALUE), //
      new FunctionTestCase(0, Long.MIN_VALUE, Long.MIN_VALUE), //
      new FunctionTestCase(-1L, Long.MIN_VALUE, (-Long.MAX_VALUE)),//
  };

  /** create */
  public SaturatingSubTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final MathematicalFunction getFunction() {
    return SaturatingSub.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean isCommutative() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final FunctionTestCase[] getTestCases() {
    return SaturatingSubTest.TEST_CASES;
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
