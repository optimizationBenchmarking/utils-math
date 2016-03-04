package org.optimizationBenchmarking.utils.math.mathEngine.impl.R;

import java.io.BufferedReader;
import java.io.IOException;

import org.optimizationBenchmarking.utils.io.paths.predicates.TextProcessResultPredicate;
import org.optimizationBenchmarking.utils.text.TextUtils;

/**
 * check whether a program produces the output we'd expect from {@code R}
 */
final class _RAtLeastVersion3Criterion extends TextProcessResultPredicate {

  /** create */
  _RAtLeastVersion3Criterion() {
    super("--version");//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unused")
  @Override
  protected boolean processOutput(final BufferedReader output)
      throws IOException {
    String s;
    boolean hasRVersion, hasRFoundation;
    int i;

    hasRVersion = hasRFoundation = false;
    looper: while ((s = output.readLine()) != null) {
      s = TextUtils.prepare(s);
      if (s != null) {
        if (s.startsWith("R version ")) { //$NON-NLS-1$
          i = s.indexOf('.', 10);
          if (i > 0) {
            try {
              i = Integer.parseInt(s.substring(10, i));
              if (i >= 3) {
                hasRVersion = true;
                if (hasRFoundation) {
                  break;
                }
                continue looper;
              }
            } catch (final Throwable tt) {
              //
            }
          }
          hasRVersion = false;
          break looper;
        }
        if (s.contains(//
            "The R Foundation for Statistical Computing")) { //$NON-NLS-1$
          hasRFoundation = true;
          if (hasRVersion) {
            break;
          }
        }
      }
    }

    if (super.processOutput(output)) {
      return (hasRVersion && hasRFoundation);
    }
    return false;
  }
}
