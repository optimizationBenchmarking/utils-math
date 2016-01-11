package test.junit.org.optimizationBenchmarking.utils.math.matrix.impl;

import org.optimizationBenchmarking.utils.math.matrix.AbstractMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.DistanceMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.DistanceMatrixBuilderJob;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.IAggregate;
import org.optimizationBenchmarking.utils.reflection.EPrimitiveType;

/** test the int distance matrix */
public class IntDistanceMatrix1DBuilderTest
    extends IntDistanceMatrix1DTest {

  /** the constructor */
  public IntDistanceMatrix1DBuilderTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final DistanceMatrix createDistanceMatrix(
      final AbstractMatrix fullMatrix) {
    return new __Builder(fullMatrix).call();
  }

  /** the builder test */
  private static final class __Builder extends DistanceMatrixBuilderJob {
    /** the full matrix */
    private final AbstractMatrix m_fullMatrix;

    /**
     * the matrix builder
     *
     * @param fullMatrix
     *          the full matrix
     */
    __Builder(final AbstractMatrix fullMatrix) {
      super();
      this.m_fullMatrix = fullMatrix;
    }

    /** {@inheritDoc} */
    @Override
    protected final int getElementCount() {
      return this.m_fullMatrix.m();
    }

    /** {@inheritDoc} */
    @Override
    protected final void setDistance(final int i, final int j,
        final IAggregate appendTo) {
      appendTo.append((int) (this.m_fullMatrix.getLong(i, j)));
    }

    /** {@inheritDoc} */
    @Override
    protected final EPrimitiveType getDistanceType() {
      return EPrimitiveType.BYTE;
    }
  }
}
