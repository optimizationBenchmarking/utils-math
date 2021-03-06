package org.optimizationBenchmarking.utils.math;

import org.optimizationBenchmarking.utils.error.ErrorUtils;
import org.optimizationBenchmarking.utils.math.functions.arithmetic.AddN;

/** A set of utility methods to solve polynomials */
public final class Polynomials {

  /**
   * Find the one coefficient of a polynomial of degree 0, i.e.,
   * {@code y = f(x) = a0}.
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param dest
   *          the destination array
   * @return the error of the fitting
   */
  public static final double degree0FindCoefficients(final double x0,
      final double y0, final double[] dest) {
    if (MathUtils.isFinite(y0)) {
      dest[0] = ((y0 == 0d) ? 0d : y0);
      return 0d;
    }
    dest[0] = Double.NaN;
    return Double.POSITIVE_INFINITY;
  }

  /**
   * Compute the gradient at the given position {@code x} of a constant
   * function.
   *
   * @param x
   *          the {@code x} coordinate
   * @param gradient
   *          the gradient destination array (of length 1)
   */
  public static final void degree0Gradient(final double x,
      final double[] gradient) {
    gradient[0] = 1d;// a
  }

  /**
   * Find the two coefficients of a polynomial of degree 1, i.e.,
   * {@code y = f(x) = a0 + a1*x}.
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param x1
   *          the {@code x}-coordinate of the second point
   * @param y1
   *          the {@code y}-coordinate of the second point
   * @param dest
   *          the destination array
   * @return the error of the fitting
   */
  public static final double degree1FindCoefficients(final double x0,
      final double y0, final double x1, final double y1,
      final double[] dest) {
    double bestError;

    compute: {

      if (x0 == x1) {
        if (y0 == y1) {
          bestError = Polynomials.degree0FindCoefficients(x0, y0, dest);
          if (bestError < Double.POSITIVE_INFINITY) {
            dest[1] = 0d;
            return bestError;
          }
        }
        break compute;
      }

      bestError = Polynomials.__degree1GetCoefficients(x1, y1, x0, y0,
          dest, //
          Polynomials.__degree1GetCoefficients(x0, y0, x1, y1, //
              dest, Double.POSITIVE_INFINITY));
      if (bestError < Double.POSITIVE_INFINITY) {
        return bestError;
      }
    }

    dest[0] = dest[1] = Double.NaN;
    return Double.POSITIVE_INFINITY;
  }

  /**
   * Compute the value of a linear fitting
   *
   * @param x0
   *          the {@code x}-coordinate
   * @param a0
   *          the first coefficient
   * @param a1
   *          the second coefficient
   * @return the result
   */
  public static final double degree1Compute(final double x0,
      final double a0, final double a1) {
    return (a0 + (a1 * x0)); //
  }

  /**
   * Compute the gradient at the given position {@code x} of a linear
   * polynom.
   *
   * @param x
   *          the {@code x} coordinate
   * @param gradient
   *          the gradient destination array (of length 2)
   */
  public static final void degree1Gradient(final double x,
      final double[] gradient) {
    gradient[0] = 1d;// a
    gradient[1] = x;// b
  }

  /**
   * Compute the error of a linear fitting
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param x1
   *          the {@code x}-coordinate of the second point
   * @param y1
   *          the {@code y}-coordinate of the second point
   * @param a0
   *          the first coefficient
   * @param a1
   *          the second coefficient
   * @return the error
   */
  private static final double __degree1GetErrorForCoefficients(
      final double x0, final double y0, final double x1, final double y1,
      final double a0, final double a1) {
    final double result;
    result = Math.abs(AddN.destructiveSum(y0, -a0, -(a1 * x0))) + //
        Math.abs(AddN.destructiveSum(y1, -a0, -(a1 * x1)));
    return ((result != result) ? Double.POSITIVE_INFINITY
        : ((result <= 0d) ? 0d : result)); //
  }

  /**
   * Compute the error of a linear fitting
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param x1
   *          the {@code x}-coordinate of the second point
   * @param y1
   *          the {@code y}-coordinate of the second point
   * @param a0
   *          the first coefficient
   * @param a1
   *          the second coefficient
   * @param dest
   *          the destination array
   * @param bestError
   *          the best error so far,
   *          {@link java.lang.Double#POSITIVE_INFINITY} if the problem has
   *          not yet been solved
   * @return the new error, {@link java.lang.Double#POSITIVE_INFINITY} if
   *         the problem has not been solved
   */
  private static final double __degree1CommitCoefficients(final double x0,
      final double y0, final double x1, final double y1, final double a0,
      final double a1, final double[] dest, final double bestError) {
    final double error1, a0t, a1t, error2;

    error1 = Polynomials.__degree1GetErrorForCoefficients(x0, y0, x1, y1,
        a0, a1);
    if (error1 < Double.POSITIVE_INFINITY) {
      if (bestError < Double.POSITIVE_INFINITY) {
        a0t = ((0.5d * a0) + (0.5d * dest[0]));
        a1t = ((0.5d * a1) + (0.5d * dest[1]));
        error2 = Polynomials.__degree1GetErrorForCoefficients(x0, y0, x1,
            y1, a0t, a1t);
        if ((error2 < bestError) && (error2 < error1)) {
          dest[0] = ((a0t == 0d) ? 0d : a0t);
          dest[1] = ((a1t == 0d) ? 0d : a1t);
          return error2;
        }
      }

      if (error1 < bestError) {
        dest[0] = ((a0 == 0d) ? 0d : a0);
        dest[1] = ((a1 == 0d) ? 0d : a1);
        return error1;
      }
    }

    return bestError;
  }

  /**
   * Compute the parameters of a polynomial of
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param x1
   *          the {@code x}-coordinate of the second point
   * @param y1
   *          the {@code y}-coordinate of the second point
   * @param dest
   *          the destination array
   * @param bestError
   *          the best error so far,
   *          {@link java.lang.Double#POSITIVE_INFINITY} if the problem has
   *          not yet been solved
   * @return the new error, {@link java.lang.Double#POSITIVE_INFINITY} if
   *         the problem has not been solved
   */
  private static final double __degree1GetCoefficients(final double x0,
      final double y0, final double x1, final double y1,
      final double[] dest, final double bestError) {
    final double a0, a1;

    if (MathUtils.isFinite(a1 = ((y1 - y0) / (x1 - x0)))) {
      if (MathUtils.isFinite(a0 = (y1 - (a1 * x1)))) {
        return Polynomials.__degree1CommitCoefficients(x0, y0, x1, y1, a0,
            a1, dest, bestError);
      }
    }

    return bestError;
  }

  /**
   * Compute the error of a quadratic fitting
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param x1
   *          the {@code x}-coordinate of the second point
   * @param y1
   *          the {@code y}-coordinate of the second point
   * @param x2
   *          the {@code x}-coordinate of the third point
   * @param y2
   *          the {@code y}-coordinate of the third point
   * @param a0
   *          the first coefficient
   * @param a1
   *          the second coefficient
   * @param a2
   *          the third coefficient
   * @return the error
   */
  private static final double __degree2GetErrorForCoefficients(
      final double x0, final double y0, final double x1, final double y1,
      final double x2, final double y2, final double a0, final double a1,
      final double a2) {
    final double result;
    result = AddN.destructiveSum(//
        Math.abs(
            AddN.destructiveSum(y0, -a0, -(a1 * x0), -(a2 * x0 * x0))), //
        Math.abs(
            AddN.destructiveSum(y1, -a0, -(a1 * x1), -(a2 * x1 * x1))), //
        Math.abs(
            AddN.destructiveSum(y2, -a0, -(a1 * x2), -(a2 * x2 * x2)))); //
    return ((result != result) ? Double.POSITIVE_INFINITY
        : ((result <= 0d) ? 0d : result));
  }

  /**
   * Compute the value of the quadratic function
   *
   * @param x0
   *          the {@code x}-coordinate
   * @param a0
   *          the first coefficient
   * @param a1
   *          the second coefficient
   * @param a2
   *          the third coefficient
   * @return the resut
   */
  public static final double degree2Compute(final double x0,
      final double a0, final double a1, final double a2) {
    return AddN.destructiveSum(a0, (a1 * x0), (a2 * x0 * x0));
  }

  /**
   * Compute the gradient at the given position {@code x} of a quadratic
   * function.
   *
   * @param x
   *          the {@code x} coordinate
   * @param gradient
   *          the gradient destination array (of length 3)
   */
  public static final void degree2Gradient(final double x,
      final double[] gradient) {
    gradient[0] = 1d;// a
    gradient[1] = x;// b
    gradient[2] = x * x;// c
  }

  /**
   * Commit a solution of degree 2
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param x1
   *          the {@code x}-coordinate of the second point
   * @param y1
   *          the {@code y}-coordinate of the second point
   * @param x2
   *          the {@code x}-coordinate of the third point
   * @param y2
   *          the {@code y}-coordinate of the third point
   * @param a0
   *          the first coefficient
   * @param a1
   *          the second coefficient
   * @param a2
   *          the third coefficient
   * @param dest
   *          the destination array
   * @param bestError
   *          the best error so far,
   *          {@link java.lang.Double#POSITIVE_INFINITY} if the problem has
   *          not yet been solved
   * @return the new error, {@link java.lang.Double#POSITIVE_INFINITY} if
   *         the problem has not been solved
   */
  private static final double __degree2CommitCoefficients(final double x0,
      final double y0, final double x1, final double y1, final double x2,
      final double y2, final double a0, final double a1, final double a2,
      final double[] dest, final double bestError) {
    final double error1, a0t, a1t, a2t, error2;

    error1 = Polynomials.__degree2GetErrorForCoefficients(x0, y0, x1, y1,
        x2, y2, a0, a1, a2);
    if (error1 < Double.POSITIVE_INFINITY) {
      if (bestError < Double.POSITIVE_INFINITY) {
        a0t = ((0.5d * a0) + (0.5d * dest[0]));
        a1t = ((0.5d * a1) + (0.5d * dest[1]));
        a2t = ((0.5d * a2) + (0.5d * dest[2]));
        error2 = Polynomials.__degree2GetErrorForCoefficients(x0, y0, x1,
            y1, x2, y2, a0t, a1t, a2t);
        if ((error2 < bestError) && (error2 < error1)) {
          dest[0] = ((a0t == 0d) ? 0d : a0t);
          dest[1] = ((a1t == 0d) ? 0d : a1t);
          dest[2] = ((a2t == 0d) ? 0d : a2t);
          return error2;
        }
      }
      if (error1 < bestError) {
        dest[0] = ((a0 == 0d) ? 0d : a0);
        dest[1] = ((a1 == 0d) ? 0d : a1);
        dest[2] = ((a2 == 0d) ? 0d : a2);
        return error1;
      }
    }

    return bestError;
  }

  /**
   * The first method to find the three coefficients of a polynomial of
   * degree 2, i.e., {@code y = f(x) = a0 + a1*x + a2*x^2}.
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param x1
   *          the {@code x}-coordinate of the second point
   * @param y1
   *          the {@code y}-coordinate of the second point
   * @param x2
   *          the {@code x}-coordinate of the third point
   * @param y2
   *          the {@code y}-coordinate of the third point
   * @param dest
   *          the destination array
   * @param bestError
   *          the best error so far,
   *          {@link java.lang.Double#POSITIVE_INFINITY} if the problem has
   *          not yet been solved
   * @return the new error, {@link java.lang.Double#POSITIVE_INFINITY} if
   *         the problem has not been solved
   */
  private static final double __degree2GetCoefficientsA(final double x0,
      final double y0, final double x1, final double y1, final double x2,
      final double y2, final double[] dest, final double bestError) {
    final double x0mx1, x0mx2, x1mx2, t0, t1, t2, a2, a1, a0;

    x0mx1 = (x0 - x1);
    x0mx2 = (x0 - x2);
    x1mx2 = (x1 - x2);

    t0 = (y0 / (x0mx1 * x0mx2));
    t1 = (y1 / (-x0mx1 * x1mx2));
    t2 = (y2 / (x0mx2 * x1mx2));

    if (MathUtils.isFinite(//
        a2 = AddN.destructiveSum(t0, t1, t2))) {
      if (MathUtils.isFinite(a1 = (-AddN.destructiveSum(//
          (t0 * (x1 + x2)), (t1 * (x0 + x2)), (t2 * (x0 + x1)))))) {
        if (MathUtils.isFinite(a0 = AddN.destructiveSum(//
            (t0 * x1 * x2), (t1 * x0 * x2), (t2 * x0 * x1)))) {
          return Polynomials.__degree2CommitCoefficients(x0, y0, x1, y1,
              x2, y2, a0, a1, a2, dest, bestError);
        }
      }
    }

    return bestError;
  }

  /**
   * The second method to find the three coefficients of a polynomial of
   * degree 2, i.e., {@code y = f(x) = a0 + a1*x + a2*x^2}.
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param x1
   *          the {@code x}-coordinate of the second point
   * @param y1
   *          the {@code y}-coordinate of the second point
   * @param x2
   *          the {@code x}-coordinate of the third point
   * @param y2
   *          the {@code y}-coordinate of the third point
   * @param dest
   *          the destination array
   * @param bestError
   *          the best error so far,
   *          {@link java.lang.Double#POSITIVE_INFINITY} if the problem has
   *          not yet been solved
   * @return the new error, {@link java.lang.Double#POSITIVE_INFINITY} if
   *         the problem has not been solved
   */
  private static final double __degree2GetCoefficientsB(final double x0,
      final double y0, final double x1, final double y1, final double x2,
      final double y2, final double[] dest, final double bestError) {
    final double x0mx1, x0mx2, a2, a1, a0;

    x0mx1 = (x0 - x1);
    x0mx2 = (x0 - x2);

    if (MathUtils.isFinite(//
        a2 = ((((y1 - y0) * (x0mx2)) + ((y2 - y0) * ((-x0mx1)))) / //
            (((x0mx2) * ((x1 * x1) - (x0 * x0)))
                + (((-x0mx1)) * ((x2 * x2) - (x0 * x0))))))) {
      if (MathUtils.isFinite(a1 = (//
      ((y1 - y0) - (a2 * ((x1 * x1) - (x0 * x0)))) / ((-x0mx1))))) {
        if (MathUtils.isFinite(a0 = y0 - (a2 * x0 * x0) - (a1 * x0))) {
          return Polynomials.__degree2CommitCoefficients(x0, y0, x1, y1,
              x2, y2, a0, a1, a2, dest, bestError);
        }
      }
    }

    return bestError;
  }

  /**
   * Find the three coefficients of a polynomial of degree 2, i.e.,
   * {@code y = f(x) = a0 + a1*x + a2*x^2}. This is done according to my
   * comment to my own question at
   * http://stackoverflow.com/questions/33445756.
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param x1
   *          the {@code x}-coordinate of the second point
   * @param y1
   *          the {@code y}-coordinate of the second point
   * @param x2
   *          the {@code x}-coordinate of the third point
   * @param y2
   *          the {@code y}-coordinate of the third point
   * @param dest
   *          the destination array
   * @return the error of the fitting
   */
  public static final double degree2FindCoefficients(final double x0,
      final double y0, final double x1, final double y1, final double x2,
      final double y2, final double[] dest) {
    double bestError;

    compute: {
      // catch all special cases: quadratic function degenerated to linear
      // or constant function
      if (x0 == x1) {
        if (y0 == y1) {
          bestError = Polynomials.degree1FindCoefficients(x0, y0, x2, y2,
              dest);
          if (bestError < Double.POSITIVE_INFINITY) {
            dest[2] = 0d;
            return bestError;
          }
        }
        break compute;
      }

      if (x0 == x2) {
        if (y0 == y2) {
          bestError = Polynomials.degree1FindCoefficients(x0, y0, x1, y1,
              dest);
          if (bestError < Double.POSITIVE_INFINITY) {
            dest[2] = 0d;
            return bestError;
          }
        }
        break compute;
      }

      if (x1 == x2) {
        if (y1 == y2) {
          bestError = Polynomials.degree1FindCoefficients(x0, y0, x1, y1,
              dest);
          if (bestError < Double.POSITIVE_INFINITY) {
            dest[2] = 0d;
            return bestError;
          }
        }
        break compute;
      }

      if ((y0 == y1) && (y1 == y2)) {
        bestError = Polynomials.degree0FindCoefficients(x0, y0, dest);
        if (bestError < Double.POSITIVE_INFINITY) {
          dest[1] = dest[2] = 0d;
          return bestError;
        }
        break compute;
      }

      LinearEquations.linearEquationsSolve(//
          new double[][] { { 1d, x0, x0 * x0 }, //
              { 1d, x1, x1 * x1 }, //
              { 1d, x2, x2 * x2 } }, //
          new double[] { y0, y1, y2 }, //
          dest);
      bestError = Polynomials.__degree2GetErrorForCoefficients(x0, y0, x1,
          y1, x2, y2, dest[0], dest[1], dest[2]);

      bestError = Polynomials.__degree2GetCoefficientsA(x0, y0, x1, y1, x2,
          y2, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }
      bestError = Polynomials.__degree2GetCoefficientsB(x0, y0, x1, y1, x2,
          y2, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }

      bestError = Polynomials.__degree2GetCoefficientsA(x0, y0, x2, y2, x1,
          y1, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }
      bestError = Polynomials.__degree2GetCoefficientsB(x0, y0, x2, y2, x1,
          y1, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }

      bestError = Polynomials.__degree2GetCoefficientsA(x1, y1, x0, y0, x2,
          y2, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }
      bestError = Polynomials.__degree2GetCoefficientsB(x1, y1, x0, y0, x2,
          y2, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }

      bestError = Polynomials.__degree2GetCoefficientsA(x1, y1, x2, y2, x0,
          y0, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }
      bestError = Polynomials.__degree2GetCoefficientsB(x1, y1, x2, y2, x0,
          y0, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }

      bestError = Polynomials.__degree2GetCoefficientsA(x2, y2, x0, y0, x1,
          y1, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }
      bestError = Polynomials.__degree2GetCoefficientsB(x2, y2, x0, y0, x1,
          y1, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }

      bestError = Polynomials.__degree2GetCoefficientsA(x2, y2, x1, y1, x0,
          y0, dest, bestError);
      if (bestError == 0d) {
        return bestError;
      }
      bestError = Polynomials.__degree2GetCoefficientsB(x2, y2, x1, y1, x0,
          y0, dest, bestError);

      if (bestError < Double.POSITIVE_INFINITY) {
        return bestError;
      }
    }

    dest[0] = dest[1] = dest[2] = Double.NaN;
    return Double.POSITIVE_INFINITY;
  }

  /**
   * Compute the value of the cubix function
   *
   * @param x0
   *          the {@code x}-coordinate
   * @param a0
   *          the first coefficient
   * @param a1
   *          the second coefficient
   * @param a2
   *          the third coefficient
   * @param a3
   *          the fourth coefficient
   * @return the result
   */
  public static final double degree3Compute(final double x0,
      final double a0, final double a1, final double a2, final double a3) {
    final double xsqr;
    xsqr = (x0 * x0);
    return AddN.destructiveSum(a0, (a1 * x0), (a2 * xsqr),
        (a3 * xsqr * x0));
  }

  /**
   * Compute the gradient at the given position {@code x} of a cubic
   * function.
   *
   * @param x
   *          the {@code x} coordinate
   * @param gradient
   *          the gradient destination array (of length 4)
   */
  public static final void degree3Gradient(final double x,
      final double[] gradient) {
    final double sqrx;
    gradient[0] = 1d;// a
    gradient[1] = x;// b
    gradient[2] = sqrx = x * x;// c
    gradient[3] = sqrx * x;// c
  }

  /**
   * Find the three coefficients of a polynomial of degree 2, i.e.,
   * {@code y = f(x) = a0 + a1*x + a2*x^2+a3*x^3}..
   *
   * @param x0
   *          the {@code x}-coordinate of the first point
   * @param y0
   *          the {@code y}-coordinate of the first point
   * @param x1
   *          the {@code x}-coordinate of the second point
   * @param y1
   *          the {@code y}-coordinate of the second point
   * @param x2
   *          the {@code x}-coordinate of the third point
   * @param y2
   *          the {@code y}-coordinate of the third point
   * @param x3
   *          the {@code x}-coordinate of the fourth point
   * @param y3
   *          the {@code y}-coordinate of the fourth point
   * @param dest
   *          the destination array
   * @return the error of the fitting
   */
  public static final double degree3FindCoefficients(final double x0,
      final double y0, final double x1, final double y1, final double x2,
      final double y2, final double x3, final double y3,
      final double[] dest) {
    double bestError, t;

    compute: {
      // catch all special cases: cubic functions can degenerate to
      // quadratic or linear
      // or constant function
      if (x0 == x1) {
        if (y0 == y1) {
          bestError = Polynomials.degree2FindCoefficients(x0, y0, x2, y2,
              x3, y3, dest);
          if (bestError < Double.POSITIVE_INFINITY) {
            dest[3] = 0d;
            return bestError;
          }
        }
        break compute;
      }

      if (x0 == x2) {
        if (y0 == y2) {
          bestError = Polynomials.degree2FindCoefficients(x0, y0, x1, y1,
              x3, x3, dest);
          if (bestError < Double.POSITIVE_INFINITY) {
            dest[3] = 0d;
            return bestError;
          }
        }
        break compute;
      }

      if (x0 == x3) {
        if (y0 == y3) {
          bestError = Polynomials.degree2FindCoefficients(x0, y0, x1, y1,
              x2, x2, dest);
          if (bestError < Double.POSITIVE_INFINITY) {
            dest[3] = 0d;
            return bestError;
          }
        }
        break compute;
      }

      if (x1 == x2) {
        if (y1 == y2) {
          bestError = Polynomials.degree2FindCoefficients(x0, y0, x1, y1,
              x3, y3, dest);
          if (bestError < Double.POSITIVE_INFINITY) {
            dest[3] = 0d;
            return bestError;
          }
        }
        break compute;
      }

      if (x1 == x3) {
        if (y1 == y3) {
          bestError = Polynomials.degree2FindCoefficients(x0, y0, x1, y1,
              x2, y2, dest);
          if (bestError < Double.POSITIVE_INFINITY) {
            dest[3] = 0d;
            return bestError;
          }
        }
        break compute;
      }

      if (x2 == x3) {
        if (y2 == y3) {
          bestError = Polynomials.degree2FindCoefficients(x0, y0, x1, y1,
              x2, y2, dest);
          if (bestError < Double.POSITIVE_INFINITY) {
            dest[3] = 0d;
            return bestError;
          }
        }
        break compute;
      }

      return LinearEquations.linearEquationsSolve(//
          new double[][] { { 1d, x0, (t = (x0 * x0)), (t * x0) }, //
              { 1d, x1, (t = (x1 * x1)), (t * x1) }, //
              { 1d, x2, (t = (x2 * x2)), (t * x2) }, //
              { 1d, x3, (t = (x3 * x3)), (t * x3) } }, //
          new double[] { y0, y1, y2, y3 }, //
          dest);
    }

    dest[0] = dest[1] = dest[2] = Double.NaN;
    return Double.POSITIVE_INFINITY;
  }

  /** the forbidden constructor */
  private Polynomials() {
    ErrorUtils.doNotCall();
  }
}
