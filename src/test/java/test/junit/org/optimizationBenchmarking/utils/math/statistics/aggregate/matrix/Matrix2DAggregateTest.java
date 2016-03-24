package test.junit.org.optimizationBenchmarking.utils.math.statistics.aggregate.matrix;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.processing.iterator2D.CallableMatrixIteration2DBuilder;

import shared.junit.TestBase;

/** A basic test for matrix 2D aggregates */
@Ignore
public abstract class Matrix2DAggregateTest extends TestBase {

  /** create */
  protected Matrix2DAggregateTest() {
    super();
  }

  /**
   * setup the builder
   *
   * @param builder
   *          the builder
   */
  protected void setup(
      final CallableMatrixIteration2DBuilder<AbstractMatrix> builder) {
    builder.setXDimension(0);
    builder.setYDimension(1);
  }

  /**
   * get the expected result
   *
   * @return the expected result
   */
  protected abstract AbstractMatrix getExpectedResult();

  /** test whether the iteration proceeds as expected */
  @Test(timeout = 3600000)
  public void testAggregate() {
    final AbstractMatrix expected, result;
    CallableMatrixIteration2DBuilder<AbstractMatrix> builder;
    int i, j;

    builder = new CallableMatrixIteration2DBuilder<>();
    this.setup(builder);
    result = builder.create().call();
    builder = null;
    Assert.assertNotNull(result);

    expected = this.getExpectedResult();
    Assert.assertNotNull(expected);

    Assert.assertEquals(expected.m(), result.m());
    Assert.assertEquals(expected.n(), result.n());
    if (expected.isIntegerMatrix()) {
      Assert.assertTrue(result.isIntegerMatrix());
    } else {
      Assert.assertFalse(result.isIntegerMatrix());
    }

    if (expected.isIntegerMatrix()) {
      for (i = expected.m(); (--i) >= 0;) {
        for (j = expected.n(); (--j) >= 0;) {
          Assert.assertEquals(expected.getLong(i, j),
              result.getLong(i, j));
        }
      }
    } else {
      for (i = expected.m(); (--i) >= 0;) {
        for (j = expected.n(); (--j) >= 0;) {
          Assert.assertEquals(expected.getDouble(i, j),
              result.getDouble(i, j), Double.MIN_VALUE);
        }
      }
    }

    Assert.assertTrue(expected.equals(result));
    Assert.assertTrue(result.equals(expected));
  }
}
