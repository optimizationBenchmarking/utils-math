package test.junit.org.optimizationBenchmarking.utils.math.matrix.impl;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.DistanceMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.FloatDistanceMatrix1D;

/** test the float matrix */
public class FloatDistanceMatrix1DTest extends DistanceMatrixTest {

  /** the constructor */
  public FloatDistanceMatrix1DTest() {
    super();
  }

  /** test whether the matrix is an integer matrix */
  @Test(timeout = 3600000)
  public void testIsInteger() {
    Assert.assertFalse(this.getInstance().isIntegerMatrix());
  }

  /** {@inheritDoc} */
  @Override
  protected final AbstractMatrix createFullMatrix() {
    final Random r;
    final int m;

    r = new Random();
    m = (1 + r.nextInt(r.nextBoolean() ? 50 : 4));
    return FloatMatrix1DTest._create(m, m, r);
  }

  /** {@inheritDoc} */
  @Override
  protected DistanceMatrix createDistanceMatrix(
      final AbstractMatrix fullMatrix) {
    final int m;
    final float[] data;
    int i, j, k;

    m = fullMatrix.m();
    data = new float[(m * (m - 1)) >>> 1];
    k = (-1);
    for (i = 0; i < m; i++) {
      for (j = (i + 1); j < m; j++) {
        data[++k] = ((float) (fullMatrix.getDouble(i, j)));
      }
    }

    return new FloatDistanceMatrix1D(data, m);
  }
}
