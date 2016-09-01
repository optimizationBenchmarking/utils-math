package org.optimizationBenchmarking.utils.math.mathEngine.impl.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.optimizationBenchmarking.utils.math.mathEngine.impl.abstr.MathEngine;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;
import org.optimizationBenchmarking.utils.math.matrix.impl.MatrixBuilder;
import org.optimizationBenchmarking.utils.parsers.LooseBooleanParser;
import org.optimizationBenchmarking.utils.parsers.LooseDoubleParser;
import org.optimizationBenchmarking.utils.parsers.LooseLongParser;
import org.optimizationBenchmarking.utils.reflection.EPrimitiveType;
import org.optimizationBenchmarking.utils.text.ETextCase;
import org.optimizationBenchmarking.utils.text.numbers.SimpleNumberAppender;
import org.optimizationBenchmarking.utils.tools.impl.process.TextProcess;

/** The {@code R} Engine */
public final class REngine extends MathEngine {

  /** the {@code false} */
  private static final String FALSE = "FALSE"; //$NON-NLS-1$

  /** the nan */
  private static final String NAN = "NaN"; //$NON-NLS-1$

  /** the negative infinity */
  private static final String NEGATIVE_INFINITY = "-Inf"; //$NON-NLS-1$

  /** the positive infinity */
  private static final String POSITIVE_INFINITY = "Inf"; //$NON-NLS-1$

  /** the {@code true} */
  private static final String TRUE = "TRUE"; //$NON-NLS-1$

  /** the function to print a matrix */
  private static final String PRINT_FUNCTION_NAME = "safePrintMatrix";//$NON-NLS-1$

  /** the engine ID */
  private static final AtomicLong ENGINE_ID = new AtomicLong();

  /** the instance of {@code R} */
  private TextProcess m_process;

  /** the id of the engine */
  private final String m_id;

  /** have we been closed? */
  private boolean m_closed;

  /**
   * create
   *
   * @param process
   *          the process
   * @param logger
   *          the logger, or {@code null} if none should be used
   * @throws IOException
   *           if it must
   */
  REngine(final TextProcess process, final Logger logger)
      throws IOException {
    super(logger);

    this.m_process = process;

    this.m_id = (("REngine #") + //$NON-NLS-1$
        Long.toString(REngine.ENGINE_ID.incrementAndGet()));

    if ((logger != null) && (logger.isLoggable(Level.INFO))) {
      logger.info(this.m_id + " successfully started"); //$NON-NLS-1$
    }
  }

  /**
   * Check the engine's state.
   *
   * @throws IllegalStateException
   *           if the engine has already been closed
   */
  private final void __checkState() {
    if (this.m_closed) {
      throw new IllegalStateException("R engine "//$NON-NLS-1$
          + this.m_id + //
          " has already been closed."); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void close() {
    TextProcess proc;

    this.m_closed = true;

    proc = this.m_process;
    this.m_process = null;

    if (proc != null) {
      try {
        try {
          proc.waitFor();
        } finally {
          proc.close();
        }
      } catch (final Throwable error) {
        throw new IllegalStateException((((//
        "Error while closing R Engine ") //$NON-NLS-1$
            + this.m_id) + '.'), error);
      }
      proc = null;
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  public final void dispose(final String variable) {
    final BufferedWriter bw;

    this.__checkState();
    try {
      bw = this.m_process.getStdIn();
      bw.write("rm(\"");//$NON-NLS-1$
      bw.write(variable);
      bw.write("\");");//$NON-NLS-1$
      bw.flush();
    } catch (final Throwable error) {
      throw new IllegalStateException((((//
      "Error while disposing variable in R Engine ") //$NON-NLS-1$
          + this.m_id) + '.'), error);
    }
  }

  /**
   * Find the next non-empty line
   *
   * @return the line
   * @throws IOException
   *           if i/o fails
   */
  @SuppressWarnings("resource")
  private final String __nextLine() throws IOException {
    final BufferedReader reader;
    String line;
    int i, size;

    this.__checkState();
    reader = this.m_process.getStdOut();
    for (;;) {
      line = reader.readLine();
      if (line == null) {
        throw new IOException(((//
        "Prematurely reached end of output stream of the REngine ") //$NON-NLS-1$
            + this.m_id) + '.');
      }

      size = line.length();
      for (i = 0; i < size; i++) {
        if (line.charAt(i) > ' ') {
          return line;
        }
      }
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  public final IMatrix getMatrix(final String variable) {
    final BufferedWriter out;
    final BufferedReader in;
    int m, n;
    String line, token;
    MatrixBuilder mb;
    int i, j, index, last;

    this.__checkState();
    line = null;
    i = j = m = n = -1;
    try {
      out = this.m_process.getStdIn();
      out.write(REngine.PRINT_FUNCTION_NAME);
      out.write('(');
      out.write(variable);
      out.write(')');
      out.write(';');
      out.newLine();
      out.flush();

      mb = new MatrixBuilder(EPrimitiveType.BYTE);

      line = this.__nextLine();
      index = line.indexOf(' ');
      if ((index <= 0) || (index >= (line.length() - 1))) {
        throw new IllegalStateException((((//
        "Dimension line of matrix " + variable//$NON-NLS-1$
            + " read from R Engine " //$NON-NLS-1$
            + this.m_id) + " is '") + line) //$NON-NLS-1$
            + "' invalid: does not contain a space between two numbers.");//$NON-NLS-1$
      }
      m = Integer.parseInt(line.substring(0, index));
      mb.setM(m);
      n = Integer.parseInt(line.substring(index + 1));
      mb.setN(n);
      in = this.m_process.getStdOut();

      for (i = 0; i < m; i++) {
        line = in.readLine();
        index = -1;
        iterateTokens: for (j = 0; j < n; j++) {
          last = (index + 1);
          if (j >= (n - 1)) {
            index = line.length();
          } else {
            index = line.indexOf(' ', last);
          }
          token = line.substring(last, index);

          if (REngine.NAN.equalsIgnoreCase(token)) {
            mb.append(Double.NaN);
            continue iterateTokens;
          }
          if (REngine.NEGATIVE_INFINITY.equalsIgnoreCase(token)) {
            mb.append(Float.NEGATIVE_INFINITY);
            continue iterateTokens;
          }
          if (REngine.POSITIVE_INFINITY.equalsIgnoreCase(token)) {
            mb.append(Float.POSITIVE_INFINITY);
            continue iterateTokens;
          }
          if (REngine.TRUE.equalsIgnoreCase(token)) {
            mb.append((byte) 1);
            continue iterateTokens;
          }
          if (REngine.FALSE.equalsIgnoreCase(token)) {
            mb.append((byte) 0);
            continue iterateTokens;
          }
          mb.append(token);
        }
        mb.setN(n);
      }
      return mb.make();
    } catch (final Throwable error) {
      throw new IllegalStateException(((((((((((((((//
      "Error while reading the element at row " + //$NON-NLS-1$
          i) + " and column ") + //$NON-NLS-1$
          j) + " of") + m) + 'x') + n + //$NON-NLS-1$
          " matrix ") + variable) + //$NON-NLS-1$
          " from R Engine ") //$NON-NLS-1$
          + this.m_id) + ", encountered line '")//$NON-NLS-1$
          + line) + '\'') + '.'), error);
    }
  }

  /**
   * issue a command to get a scalar
   *
   * @param variable
   *          the variable
   * @return the string
   */
  @SuppressWarnings("resource")
  private final String __getScalar(final String variable) {
    final BufferedWriter out;

    this.__checkState();

    try {
      out = this.m_process.getStdIn();
      out.write("cat("); //$NON-NLS-1$
      out.write(variable);
      out.write(");cat('\\n');"); //$NON-NLS-1$
      out.newLine();
      out.flush();
    } catch (final Throwable error) {
      throw new IllegalStateException(((((//
      "Error while writing request to read scalar value "//$NON-NLS-1$
          + variable) + "from R Engine ") //$NON-NLS-1$
          + this.m_id) + '.'), error);
    }
    try {
      return this.__nextLine();
    } catch (final Throwable error) {
      throw new IllegalStateException(((((//
      "Error while reading scalar value "//$NON-NLS-1$
          + variable) + " from R Engine ") //$NON-NLS-1$
          + this.m_id) + '.'), error);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double getDouble(final String variable) {
    final String token;

    token = this.__getScalar(variable);
    if (REngine.NAN.equalsIgnoreCase(token)) {
      return Double.NaN;
    }
    if (REngine.NEGATIVE_INFINITY.equalsIgnoreCase(token)) {
      return Double.NEGATIVE_INFINITY;
    }
    if (REngine.POSITIVE_INFINITY.equalsIgnoreCase(token)) {
      return Double.POSITIVE_INFINITY;
    }
    if (REngine.TRUE.equalsIgnoreCase(token)) {
      return 1d;
    }
    if (REngine.FALSE.equalsIgnoreCase(token)) {
      return 0d;
    }
    try {
      return LooseDoubleParser.INSTANCE.parseDouble(token);
    } catch (final Throwable error) {
      throw new IllegalStateException((((((((//
      "Error while reading double " + variable)//$NON-NLS-1$
          + " from R Engine ") //$NON-NLS-1$
          + this.m_id) + ": encountered text '") + //$NON-NLS-1$
          token) + '\'') + '.'), error);
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  public final long getLong(final String variable) {
    final String token;

    token = this.__getScalar(variable);
    if (REngine.TRUE.equalsIgnoreCase(token)) {
      return 1L;
    }
    if (REngine.FALSE.equalsIgnoreCase(token)) {
      return 0L;
    }

    try {
      return LooseLongParser.INSTANCE.parseLong(token);
    } catch (final Throwable error) {
      try {
        return ((long) (LooseDoubleParser.INSTANCE.parseDouble(token)));
      } catch (final Throwable error2) {
        throw new IllegalStateException((((((((//
        "Error while reading long "//$NON-NLS-1$
            + variable) + " from R Engine ") //$NON-NLS-1$
            + this.m_id) + ": encountered text '") + //$NON-NLS-1$
            token) + '\'') + '.'), error);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final boolean getBoolean(final String variable) {
    final String token;

    token = this.__getScalar(variable);
    if (REngine.TRUE.equalsIgnoreCase(token)) {
      return true;
    }
    if (REngine.FALSE.equalsIgnoreCase(token)) {
      return false;
    }
    try {
      return LooseBooleanParser.INSTANCE.parseBoolean(token);
    } catch (final Throwable error) {
      throw new IllegalStateException((((((((//
      "Error while reading boolean " + //$NON-NLS-1$
          variable) + " from R Engine ") //$NON-NLS-1$
          + this.m_id) + ": encountered text '") + //$NON-NLS-1$
          token) + '\'') + '.'), error);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final IMatrix getVector(final String variable) {
    final IMatrix res;

    res = this.getMatrix(variable);
    if ((res.n() == 1) || (res.m() == 1)) {
      return res;
    }
    throw new IllegalStateException((((((((//
    "Error reading vector " + variable) //$NON-NLS-1$
        + " from R Engine") + this.m_id) + //$NON-NLS-1$
        ": vector must have either one column or one row, but we encountered a") //$NON-NLS-1$
        + res.m()) + 'x') + res.n()) + " matrix instead.");//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return this.m_id;
  }

  /**
   * Finalize an assignment.
   *
   * @param variable
   *          the variable name
   * @throws IOException
   *           if i/o fails
   */
  @SuppressWarnings("resource")
  private final void __assignmentEnd(final String variable)
      throws IOException {
    final BufferedWriter out;
    String line;

    out = this.m_process.getStdIn();

    out.write(';');
    out.newLine();
    out.flush();

    // block until assignment is completed
    out.write("exists(\""); //$NON-NLS-1$
    out.write(variable);
    out.write("\");"); //$NON-NLS-1$
    out.newLine();
    out.flush();

    line = this.m_process.getStdOut().readLine();
    if ((line == null) || (!(line.contains(REngine.TRUE)))) {
      throw new IllegalStateException("Assignment of variable '" //$NON-NLS-1$
          + variable + "' has failed, 'exists(\""//$NON-NLS-1$
          + variable + "\")' returns '" + line //$NON-NLS-1$
          + '\'' + '.');
    }
  }

  /**
   * Initialize an assignment.
   *
   * @param variable
   *          the variable name
   * @throws IOException
   *           if i/o fails
   */
  @SuppressWarnings("resource")
  private final void __assignmentBegin(final String variable)
      throws IOException {
    final BufferedWriter out;

    out = this.m_process.getStdIn();

    out.write(variable);
    out.write('<');
    out.write('-');
  }

  /** {@inheritDoc} */
  @Override
  public final void setBoolean(final String variable,
      final boolean value) {
    this.__checkState();
    try {
      this.__assignmentBegin(variable);
      this.m_process.getStdIn().write(//
          value ? REngine.TRUE : REngine.FALSE);
      this.__assignmentEnd(variable);
    } catch (final Throwable error) {
      throw new IllegalStateException(((((((//
      "Error while sending boolean " //$NON-NLS-1$
          + variable) + " with value ") + //$NON-NLS-1$
          value) + " to R Engine ") //$NON-NLS-1$
          + this.m_id) + '.'), error);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setLong(final String variable, final long value) {
    this.__checkState();
    try {
      this.__assignmentBegin(variable);
      REngine.__writeLong(value, this.m_process.getStdIn());
      this.__assignmentEnd(variable);
    } catch (final Throwable error) {
      throw new IllegalStateException(((((((//
      "Error while sending long " //$NON-NLS-1$
          + variable) + " with value ")//$NON-NLS-1$
          + value) + " to R Engine. ") //$NON-NLS-1$
          + this.m_id) + '.'), error);
    }
  }

  /**
   * convert a {@code double} to a string
   *
   * @param d
   *          the {@code double}
   * @param dest
   *          the destination writer
   * @throws IOException
   *           if i/o failse
   */
  private static final void __writeDouble(final double d,
      final BufferedWriter dest) throws IOException {
    if (d != d) {
      dest.write(REngine.NAN);
      return;
    }
    if (d <= Double.NEGATIVE_INFINITY) {
      dest.write(REngine.NEGATIVE_INFINITY);
      return;
    }
    if (d >= Double.POSITIVE_INFINITY) {
      dest.write(REngine.POSITIVE_INFINITY);
      return;
    }
    dest.write(
        SimpleNumberAppender.INSTANCE.toString(d, ETextCase.IN_SENTENCE));
  }

  /**
   * convert a {@code long} to a string
   *
   * @param d
   *          the {@code long}
   * @param dest
   *          the writer
   * @throws IOException
   *           if i/o fails
   */
  private static final void __writeLong(final long d,
      final BufferedWriter dest) throws IOException {
    if ((d >= Integer.MIN_VALUE) && (d <= Integer.MAX_VALUE)) {
      dest.write("as.integer("); //$NON-NLS-1$
      dest.write(Integer.toString((int) d));
      dest.write(')');
    } else {
      dest.write(Long.toString(d));
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setDouble(final String variable, final double value) {
    this.__checkState();
    try {
      this.__assignmentBegin(variable);
      REngine.__writeDouble(value, this.m_process.getStdIn());
      this.__assignmentEnd(variable);
    } catch (final Throwable error) {
      throw new IllegalStateException(((((((//
      "Error while sending double " //$NON-NLS-1$
          + variable) + " with value ") + //$NON-NLS-1$
          value) + " to R Engine ") //$NON-NLS-1$
          + this.m_id) + '.'), error);
    }
  }

  /**
   * Set a matrix or vector
   *
   * @param variable
   *          the variable
   * @param value
   *          the value
   * @param isVector
   *          is the value a vector ({@code true}) or a matrix (
   *          {@code false})?
   */
  @SuppressWarnings("resource")
  private final void __setMatrix(final String variable,
      final IMatrix value, final boolean isVector) {

    final BufferedWriter out;
    final int m, n;
    int i, j, q;
    boolean first;

    if (value == null) {
      throw new IllegalArgumentException((((//
      "Cannot send null matrix " + variable) + //$NON-NLS-1$
          " to R Engine ") //$NON-NLS-1$
          + this.m_id) + '.');
    }
    m = value.m();
    n = value.n();
    if (isVector && ((m != 1) && (n != 1))) {
      throw new IllegalArgumentException((((((((//
      "A vector " + variable) + //$NON-NLS-1$
          " to be sent to the R Engine ") //$NON-NLS-1$
          + this.m_id) + //
          " must be a matrix with either only one row or only one column, but you passed in a ")//$NON-NLS-1$
          + m) + 'x') + n) + " matrix.");//$NON-NLS-1$
    }

    out = this.m_process.getStdIn();
    i = j = (-1);
    try {
      this.__assignmentBegin(variable);
      out.write("matrix(c("); //$NON-NLS-1$

      first = true;
      q = 0;
      if (value.isIntegerMatrix()) {
        for (j = 0; j < n; j++) {
          for (i = 0; i < m; i++) {
            if (first) {
              first = false;
            } else {
              out.write(',');
              if (((++q) % 17) == 0) {
                out.newLine();
              }
            }
            REngine.__writeLong(value.getLong(i, j), out);
          }
        }
      } else {
        for (j = 0; j < n; j++) {
          for (i = 0; i < m; i++) {
            if (first) {
              first = false;
            } else {
              out.write(',');
              if (((++q) % 17) == 0) {
                out.newLine();
              }
            }
            REngine.__writeDouble(value.getDouble(i, j), out);
          }
        }
      }

      out.write("),nrow=");//$NON-NLS-1$
      out.write(Integer.toString(m));
      out.write(",ncol=");//$NON-NLS-1$
      out.write(Integer.toString(n));
      out.write(",byrow="); //$NON-NLS-1$
      out.write(REngine.FALSE);
      out.write(')');
      this.__assignmentEnd(variable);
    } catch (final Throwable error) {
      throw new IllegalStateException(((((((((((((//
      "Error appeared while while sending "//$NON-NLS-1$
          + m) + 'x') + n) + //
          " matrix ") + variable) + //$NON-NLS-1$
          " to R Engine ") //$NON-NLS-1$
          + this.m_id) + " at row ") + i) //$NON-NLS-1$
          + " and column ") + j) + '.'), error);//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setMatrix(final String variable, final IMatrix value) {
    this.__setMatrix(variable, value, false);
  }

  /** {@inheritDoc} */
  @Override
  public final void setVector(final String variable, final IMatrix value) {
    this.__setMatrix(variable, value, true);
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  public final void execute(final Iterable<String> script) {
    final BufferedWriter bw;
    String last, errorStr;
    int lineCount;

    bw = this.m_process.getStdIn();
    last = null;
    lineCount = 0;
    try {
      bw.newLine();
      ++lineCount;
      for (final String line : script) {
        bw.write(last = line);
        bw.newLine();
      }
      bw.newLine();
      bw.flush();
    } catch (final Throwable error) {
      errorStr = (((("Error while execuring script in R Engine ") //$NON-NLS-1$
          + this.m_id) + " around script line ") + lineCount); //$NON-NLS-1$
      throw new IllegalStateException(//
          ((last == null) ? (errorStr + '.')
              : ((((errorStr + ", last transmitted line was '") //$NON-NLS-1$
                  + last) + '\'') + '.')),
          error);
    }
  }
}
