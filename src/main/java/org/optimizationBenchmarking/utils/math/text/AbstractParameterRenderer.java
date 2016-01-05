package org.optimizationBenchmarking.utils.math.text;

import org.optimizationBenchmarking.utils.document.spec.IParameterRenderer;

/** A base class for building parameter renderers. */
public abstract class AbstractParameterRenderer
    implements IParameterRenderer {

  /** create */
  protected AbstractParameterRenderer() {
    super();
  }

  /**
   * Construct an {@link IllegalArgumentException} describing the situation
   * that the parameter index {@code index} was invalid.
   *
   * @param index
   *          the index
   * @param max
   *          the maximum allowed index
   */
  protected static final void throwInvalidParameterIndex(final int index,
      final int max) {
    throw new IllegalArgumentException( //
        ((max > 0) ? ("Only parameter indexes from 0 to "//$NON-NLS-1$
            + max + //
            " are valid for this IParameterRenderer instance's render methods, but ") //$NON-NLS-1$
            : "Only parameter index 0 is valid for this IParameterRenderer instance's render methods, but ") //$NON-NLS-1$
            + index + " was provided." //$NON-NLS-1$
    );
  }
}
