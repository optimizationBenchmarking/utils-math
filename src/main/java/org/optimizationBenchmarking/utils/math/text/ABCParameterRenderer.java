package org.optimizationBenchmarking.utils.math.text;

import org.optimizationBenchmarking.utils.document.spec.IMath;
import org.optimizationBenchmarking.utils.document.spec.IParameterRenderer;
import org.optimizationBenchmarking.utils.document.spec.IText;
import org.optimizationBenchmarking.utils.text.TextUtils;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;

/** A parameter renderer rendering parameters as 'A', 'B', 'C', ... */
public final class ABCParameterRenderer implements IParameterRenderer {

  /** the globally shared instance */
  public static final ABCParameterRenderer INSTANCE = new ABCParameterRenderer();

  /** create */
  private ABCParameterRenderer() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final void renderParameter(final int index,
      final ITextOutput out) {
    if (index > 25) {
      throw new IllegalArgumentException((((//
      "The " + TextUtils.className(this)) + //$NON-NLS-1$
          " cannot deal with parameter indices greater than 25, but you specified ") //$NON-NLS-1$
          + index) + '.');
    }
    out.append((char) ('A' + index));
  }

  /** {@inheritDoc} */
  @Override
  public final void renderParameter(final int index, final IMath out) {
    try (final IText param = out.name()) {
      this.renderParameter(index, param);
    }
  }
}
