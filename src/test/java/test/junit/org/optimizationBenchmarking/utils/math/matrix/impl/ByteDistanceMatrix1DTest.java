package test.junit.org.optimizationBenchmarking.utils.math.matrix.impl;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.ByteDistanceMatrix1D;
import org.optimizationBenchmarking.utils.math.matrix.impl.DistanceMatrix;

/** test the byte matrix */
public class ByteDistanceMatrix1DTest extends DistanceMatrixTest {

  /** the constructor */
  public ByteDistanceMatrix1DTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final AbstractMatrix createFullMatrix() {
    final Random r;
    final int m;

    r = new Random();
    m = (1 + r.nextInt(r.nextBoolean() ? 50 : 4));
    return ByteMatrix1DTest._create(m, m, r);
  }

  /** {@inheritDoc} */
  @Override
  protected DistanceMatrix createDistanceMatrix(
      final AbstractMatrix fullMatrix) {
    final int m;
    final byte[] data;
    int i, j, k;

    m = fullMatrix.m();
    data = new byte[(m * (m - 1)) >>> 1];
    k = (-1);
    for (i = 0; i < m; i++) {
      for (j = (i + 1); j < m; j++) {
        data[++k] = ((byte) (fullMatrix.getLong(i, j)));
      }
    }

    return new ByteDistanceMatrix1D(data, m);
  }

  /** test whether the matrix is an integer matrix */
  @Test(timeout = 3600000)
  public void testIsInteger() {
    Assert.assertTrue(this.getInstance().isIntegerMatrix());
  }
}
