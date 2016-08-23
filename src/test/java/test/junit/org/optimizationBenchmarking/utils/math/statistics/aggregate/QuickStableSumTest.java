package test.junit.org.optimizationBenchmarking.utils.math.statistics.aggregate;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.optimizationBenchmarking.utils.math.BasicNumber;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.QuickStableSum;

/** A test of the stable sums */
public class QuickStableSumTest {

  /** test the summation of longs */
  @Test(timeout = 3600000)
  public void testSumUpLongs() {
    final Random rand;
    QuickStableSum ss;
    long sum, valueToAdd;
    int i, j;

    rand = new Random();

    for (j = 0; j < 10; j++) {
      ss = new QuickStableSum();

      Assert.assertTrue(ss.isInteger());
      Assert.assertEquals(ss.doubleValue(), 0d, 1e-16d);

      sum = 0l;
      innerLoop: for (i = 0; i <= 2000000; i++) {

        switch (rand.nextInt(2)) {
          case 0: {
            valueToAdd = (rand.nextInt(11) - 5);
            break;
          }
          default: {
            valueToAdd = (rand.nextInt(100001) - 50000);
            break;
          }
        }

        sum += valueToAdd;
        if (((sum <= (-(1L << 51L)))) || ((sum >= ((1L << 51L))))) {
          break innerLoop;
        }

        if (rand.nextBoolean()) {
          ss.append(valueToAdd);
        } else {
          ss.append(valueToAdd);
        }

        Assert.assertTrue(ss.isInteger());
        Assert.assertTrue(ss.isReal());
        Assert.assertNotEquals(ss.getState(), BasicNumber.STATE_EMPTY);
        Assert.assertEquals(ss.longValue(), sum, 1e-2d);
        Assert.assertEquals(sum, ss.doubleValue(), 0d);
      }
    }
  }

  /** test the summation of doubles */
  @Test(timeout = 3600000)
  public void testSumUpDoubles() {
    final QuickStableSum ss;
    final Random rand;
    double s, val, os;
    int i;

    ss = new QuickStableSum();
    rand = new Random();

    Assert.assertTrue(ss.isInteger());
    Assert.assertEquals(ss.doubleValue(), 0d, 1e-16d);
    s = 0d;

    for (i = 0; i < 10000000; i++) {
      val = rand.nextDouble();
      os = s;
      s += val;
      ss.append(val);
      Assert.assertEquals(s, ss.doubleValue(),
          (Math.ulp(os) + Math.ulp(s) + Math.abs((s - os) - val)));
      s = ss.doubleValue();
      Assert.assertTrue(ss.isReal());
    }
  }

}
