package test.junit.org.optimizationBenchmarking.utils.math.functions.power;

import org.optimizationBenchmarking.utils.math.functions.MathematicalFunction;
import org.optimizationBenchmarking.utils.math.functions.power.Sqrt;

import shared.junit.org.optimizationBenchmarking.utils.math.functions.FunctionTestCase;
import shared.junit.org.optimizationBenchmarking.utils.math.functions.MathematicalFunctionTest;

/** The test of the sqrt function */
public final class SqrtTest extends MathematicalFunctionTest {

  /** the test cases */
  private static final FunctionTestCase[] TEST_CASES = { //
      new FunctionTestCase(0, 0), //
      new FunctionTestCase(1, 1), //
      new FunctionTestCase(2, 4), //
      new FunctionTestCase(Math.sqrt(5), 5), //
      new FunctionTestCase(Math.sqrt(7), 7), //
      new FunctionTestCase(3, 9), //
      new FunctionTestCase(4, 16), //
      new FunctionTestCase(5, 25), //
      new FunctionTestCase(6, 36), //
      new FunctionTestCase(7, 49), //
      new FunctionTestCase(8, 64), //
      new FunctionTestCase(9, 81), //
      new FunctionTestCase(10, 100), //
      new FunctionTestCase(Math.sqrt(1000), 1000),
      new FunctionTestCase(3037000497L, (3037000497L * 3037000497L)), //
      new FunctionTestCase(3037000498L, (3037000498L * 3037000498L)), //
      new FunctionTestCase(3037000499L, (3037000499L * 3037000499L)), //

      // This test case does not work because the conversion from long to
      // double has a loss of precision
      // new TestCase(Math.sqrt((3037000499L * 3037000499L) - 1L),//
      // ((3037000499L * 3037000499L) - 1L)) //

      // Thus, we now use test cases which ensures that conversion without
      // loss of precision is possible.
      new FunctionTestCase(((long) (Math.sqrt(9007199254740992L))),
          (((long) (Math.sqrt(9007199254740992L))) * //
              ((long) (Math.sqrt(9007199254740992L))))), //
      new FunctionTestCase((((long) (Math.sqrt(9007199254740992L))) - 1L),
          ((((long) (Math.sqrt(9007199254740992L))) - 1L) * //
              (((long) (Math.sqrt(9007199254740992L))) - 1L))), //
      new FunctionTestCase((((long) (Math.sqrt(9007199254740992L))) - 2L),
          ((((long) (Math.sqrt(9007199254740992L))) - 2L) * //
              (((long) (Math.sqrt(9007199254740992L))) - 2L))), //
      new FunctionTestCase((((long) (Math.sqrt(9007199254740992L))) - 3L),
          ((((long) (Math.sqrt(9007199254740992L))) - 3L) * //
              (((long) (Math.sqrt(9007199254740992L))) - 3L))),//
  };

  /** create */
  public SqrtTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final MathematicalFunction getFunction() {
    return Sqrt.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean isCommutative() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final FunctionTestCase[] getTestCases() {
    return SqrtTest.TEST_CASES;
  }
}
