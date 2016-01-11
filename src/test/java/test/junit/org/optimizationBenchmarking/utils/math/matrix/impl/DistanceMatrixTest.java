package test.junit.org.optimizationBenchmarking.utils.math.matrix.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.DistanceMatrix;

import shared.junit.org.optimizationBenchmarking.utils.math.matrix.MatrixTest;

/** test distance matrices */
@Ignore
public abstract class DistanceMatrixTest
    extends MatrixTest<DistanceMatrix> {

  /** the constructor */
  public DistanceMatrixTest() {
    super();
  }

  /**
   * test that the transposed distance matrix equals the distance matrix
   */
  @Test(timeout = 3600000)
  public void testMatrixTransposedEquals() {
    final DistanceMatrix a, b;
    int i, j;

    a = this.getInstance();
    b = a.transpose();

    Assert.assertEquals(a, b);
    for (i = a.m(); (--i) >= 0;) {
      for (j = a.m(); (--j) >= 0;) {
        Assert.assertEquals(a.getLong(i, j), b.getLong(i, j));
        Assert.assertEquals(a.getDouble(i, j), b.getDouble(i, j), 0d);
      }
    }
  }

  /**
   * test that the diagonal elements are 0
   */
  @Test(timeout = 3600000)
  public void testDiagonal0() {
    final DistanceMatrix a;
    int i;

    a = this.getInstance();

    for (i = a.m(); (--i) >= 0;) {
      Assert.assertEquals(a.getDouble(i, i), 0d, 0d);
      Assert.assertEquals(a.getLong(i, i), 0L);
    }
  }

  /**
   * test that the matrix is symmetric
   */
  @Test(timeout = 3600000)
  public void testSymetry() {
    final DistanceMatrix a;
    int i, j;

    a = this.getInstance();

    for (i = a.m(); (--i) >= 0;) {
      for (j = a.m(); (--j) >= 0;) {
        Assert.assertEquals(a.getLong(i, j), a.getLong(j, i));
        Assert.assertEquals(a.getDouble(i, j), a.getDouble(j, i), 0d);
      }
    }
  }

  /**
   * Create a full matrix.
   *
   * @return the full matrix
   */
  protected abstract AbstractMatrix createFullMatrix();

  /**
   * create the distance matrix from a given matrix
   *
   * @param fullMatrix
   *          the full matrix
   * @return the distance matrix
   */
  protected abstract DistanceMatrix createDistanceMatrix(
      final AbstractMatrix fullMatrix);

  /** {@inheritDoc} */
  @Override
  protected final DistanceMatrix getInstance() {
    return this.createDistanceMatrix(this.createFullMatrix());
  }

  /**
   * test that the matrix has correctly been created
   */
  @Test(timeout = 3600000)
  public void testCorrectCreation() {
    final DistanceMatrix a;
    final AbstractMatrix abst;
    int i, j;

    abst = this.createFullMatrix();
    a = this.createDistanceMatrix(abst);

    for (i = a.m(); (--i) >= 0;) {
      for (j = a.m(); (--j) >= 0;) {
        if (i < j) {
          Assert.assertEquals(a.getLong(i, j), abst.getLong(i, j));
          Assert.assertEquals(a.getDouble(i, j), abst.getDouble(i, j), 0d);
        } else {
          if (i > j) {
            Assert.assertEquals(a.getLong(j, i), abst.getLong(j, i));
            Assert.assertEquals(a.getDouble(j, i), abst.getDouble(j, i),
                0d);
          } else {
            Assert.assertEquals(a.getLong(j, i), 0L);
            Assert.assertEquals(a.getDouble(j, i), 0d, 0d);
          }
        }
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void validateInstance() {
    super.validateInstance();
    this.testMatrixTransposedEquals();
    this.testDiagonal0();
    this.testSymetry();
    this.testCorrectCreation();
  }
}
