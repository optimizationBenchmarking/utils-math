package org.optimizationBenchmarking.utils.math.combinatorics;

import java.util.Random;

import org.optimizationBenchmarking.utils.error.ErrorUtils;

/** Some utility methods to shuffle data. */
public final class Shuffle {

  /** the forbidden constructor */
  private Shuffle() {
    ErrorUtils.doNotCall();
  }

  /**
   * Randomize a sub-sequence of an array or permutation of {@code bytes}.
   * After this procedure, the {@code count} elements of the array
   * beginning at index {@code start} are uniformly randomly distributed.
   *
   * @param array
   *          the array of {@code byte}s whose sub-sequence to be
   *          randomized
   * @param start
   *          the start index
   * @param count
   *          the number of elements to be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final byte[] array, final int start,
      final int count, final Random random) {
    final int n;
    byte t;
    int i, j, k;

    if (count > 0) {
      n = array.length;
      for (i = count; i > 1;) {
        j = ((start + random.nextInt(i--)) % n);
        k = ((start + i) % n);
        t = array[k];
        array[k] = array[j];
        array[j] = t;
      }
    }
  }

  /**
   * Randomize an array or permutation of {@code bytes}. After this
   * procedure, the elements of the array are uniformly randomly
   * distributed.
   *
   * @param array
   *          the array of {@code byte}s that should be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final byte[] array,
      final Random random) {
    Shuffle.shuffle(array, 0, array.length, random);
  }

  /**
   * Randomize a sub-sequence of an array or permutation of {@code shorts}.
   * After this procedure, the {@code count} elements of the array
   * beginning at index {@code start} are uniformly randomly distributed.
   *
   * @param array
   *          the array of {@code short}s whose sub-sequence to be
   *          randomized
   * @param start
   *          the start index
   * @param count
   *          the number of elements to be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final short[] array, final int start,
      final int count, final Random random) {
    final int n;
    short t;
    int i, j, k;

    if (count > 0) {
      n = array.length;
      for (i = count; i > 1;) {
        j = ((start + random.nextInt(i--)) % n);
        k = ((start + i) % n);
        t = array[k];
        array[k] = array[j];
        array[j] = t;
      }
    }
  }

  /**
   * Randomize an array or permutation of {@code shorts}. After this
   * procedure, the elements of the array are uniformly randomly
   * distributed.
   *
   * @param array
   *          the array of {@code short}s that should be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final short[] array,
      final Random random) {
    Shuffle.shuffle(array, 0, array.length, random);
  }

  /**
   * Randomize a sub-sequence of an array or permutation of {@code ints}.
   * After this procedure, the {@code count} elements of the array
   * beginning at index {@code start} are uniformly randomly distributed.
   *
   * @param array
   *          the array of {@code int}s whose sub-sequence to be randomized
   * @param start
   *          the start index
   * @param count
   *          the number of elements to be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final int[] array, final int start,
      final int count, final Random random) {
    final int n;
    int t;
    int i, j, k;

    if (count > 0) {
      n = array.length;
      for (i = count; i > 1;) {
        j = ((start + random.nextInt(i--)) % n);
        k = ((start + i) % n);
        t = array[k];
        array[k] = array[j];
        array[j] = t;
      }
    }
  }

  /**
   * Randomize an array or permutation of {@code ints}. After this
   * procedure, the elements of the array are uniformly randomly
   * distributed.
   *
   * @param array
   *          the array of {@code int}s that should be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final int[] array,
      final Random random) {
    Shuffle.shuffle(array, 0, array.length, random);
  }

  /**
   * Randomize a sub-sequence of an array or permutation of {@code longs}.
   * After this procedure, the {@code count} elements of the array
   * beginning at index {@code start} are uniformly randomly distributed.
   *
   * @param array
   *          the array of {@code long}s whose sub-sequence to be
   *          randomized
   * @param start
   *          the start index
   * @param count
   *          the number of elements to be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final long[] array, final int start,
      final int count, final Random random) {
    final int n;
    long t;
    int i, j, k;

    if (count > 0) {
      n = array.length;
      for (i = count; i > 1;) {
        j = ((start + random.nextInt(i--)) % n);
        k = ((start + i) % n);
        t = array[k];
        array[k] = array[j];
        array[j] = t;
      }
    }
  }

  /**
   * Randomize an array or permutation of {@code longs}. After this
   * procedure, the elements of the array are uniformly randomly
   * distributed.
   *
   * @param array
   *          the array of {@code long}s that should be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final long[] array,
      final Random random) {
    Shuffle.shuffle(array, 0, array.length, random);
  }

  /**
   * Randomize a sub-sequence of an array or permutation of {@code floats}.
   * After this procedure, the {@code count} elements of the array
   * beginning at index {@code start} are uniformly randomly distributed.
   *
   * @param array
   *          the array of {@code float}s whose sub-sequence to be
   *          randomized
   * @param start
   *          the start index
   * @param count
   *          the number of elements to be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final float[] array, final int start,
      final int count, final Random random) {
    final int n;
    float t;
    int i, j, k;

    if (count > 0) {
      n = array.length;
      for (i = count; i > 1;) {
        j = ((start + random.nextInt(i--)) % n);
        k = ((start + i) % n);
        t = array[k];
        array[k] = array[j];
        array[j] = t;
      }
    }
  }

  /**
   * Randomize an array or permutation of {@code floats}. After this
   * procedure, the elements of the array are uniformly randomly
   * distributed.
   *
   * @param array
   *          the array of {@code float}s that should be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final float[] array,
      final Random random) {
    Shuffle.shuffle(array, 0, array.length, random);
  }

  /**
   * Randomize a sub-sequence of an array or permutation of {@code doubles}
   * . After this procedure, the {@code count} elements of the array
   * beginning at index {@code start} are uniformly randomly distributed.
   *
   * @param array
   *          the array of {@code double}s whose sub-sequence to be
   *          randomized
   * @param start
   *          the start index
   * @param count
   *          the number of elements to be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final double[] array, final int start,
      final int count, final Random random) {
    final int n;
    double t;
    int i, j, k;

    if (count > 0) {
      n = array.length;
      for (i = count; i > 1;) {
        j = ((start + random.nextInt(i--)) % n);
        k = ((start + i) % n);
        t = array[k];
        array[k] = array[j];
        array[j] = t;
      }
    }
  }

  /**
   * Randomize an array or permutation of {@code doubles}. After this
   * procedure, the elements of the array are uniformly randomly
   * distributed.
   *
   * @param array
   *          the array of {@code double}s that should be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final double[] array,
      final Random random) {
    Shuffle.shuffle(array, 0, array.length, random);
  }

  /**
   * Randomize a sub-sequence of an array or permutation of {@code chars}.
   * After this procedure, the {@code count} elements of the array
   * beginning at index {@code start} are uniformly randomly distributed.
   *
   * @param array
   *          the array of {@code char}s whose sub-sequence to be
   *          randomized
   * @param start
   *          the start index
   * @param count
   *          the number of elements to be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final char[] array, final int start,
      final int count, final Random random) {
    final int n;
    char t;
    int i, j, k;

    if (count > 0) {
      n = array.length;
      for (i = count; i > 1;) {
        j = ((start + random.nextInt(i--)) % n);
        k = ((start + i) % n);
        t = array[k];
        array[k] = array[j];
        array[j] = t;
      }
    }
  }

  /**
   * Randomize an array or permutation of {@code chars}. After this
   * procedure, the elements of the array are uniformly randomly
   * distributed.
   *
   * @param array
   *          the array of {@code char}s that should be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final char[] array,
      final Random random) {
    Shuffle.shuffle(array, 0, array.length, random);
  }

  /**
   * Randomize a sub-sequence of an array or permutation of
   * {@code booleans}. After this procedure, the {@code count} elements of
   * the array beginning at index {@code start} are uniformly randomly
   * distributed.
   *
   * @param array
   *          the array of {@code boolean}s whose sub-sequence to be
   *          randomized
   * @param start
   *          the start index
   * @param count
   *          the number of elements to be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final boolean[] array, final int start,
      final int count, final Random random) {
    final int n;
    boolean t;
    int i, j, k;

    if (count > 0) {
      n = array.length;
      for (i = count; i > 1;) {
        j = ((start + random.nextInt(i--)) % n);
        k = ((start + i) % n);
        t = array[k];
        array[k] = array[j];
        array[j] = t;
      }
    }
  }

  /**
   * Randomize an array or permutation of {@code booleans}. After this
   * procedure, the elements of the array are uniformly randomly
   * distributed.
   *
   * @param array
   *          the array of {@code boolean}s that should be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final boolean[] array,
      final Random random) {
    Shuffle.shuffle(array, 0, array.length, random);
  }

  /**
   * Randomize a sub-sequence of an array or permutation of
   * {@code java.lang.Objects}. After this procedure, the {@code count}
   * elements of the array beginning at index {@code start} are uniformly
   * randomly distributed.
   *
   * @param array
   *          the array of {@code java.lang.Object}s whose sub-sequence to
   *          be randomized
   * @param start
   *          the start index
   * @param count
   *          the number of elements to be randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final java.lang.Object[] array,
      final int start, final int count, final Random random) {
    final int n;
    java.lang.Object t;
    int i, j, k;

    if (count > 0) {
      n = array.length;
      for (i = count; i > 1;) {
        j = ((start + random.nextInt(i--)) % n);
        k = ((start + i) % n);
        t = array[k];
        array[k] = array[j];
        array[j] = t;
      }
    }
  }

  /**
   * Randomize an array or permutation of {@code java.lang.Objects}. After
   * this procedure, the elements of the array are uniformly randomly
   * distributed.
   *
   * @param array
   *          the array of {@code java.lang.Object}s that should be
   *          randomized
   * @param random
   *          the random number generator
   */
  public static final void shuffle(final java.lang.Object[] array,
      final Random random) {
    Shuffle.shuffle(array, 0, array.length, random);
  }
}
