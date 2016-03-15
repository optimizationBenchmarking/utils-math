package org.optimizationBenchmarking.utils.math.mathEngine.impl.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashSet;

import org.optimizationBenchmarking.utils.EmptyUtils;
import org.optimizationBenchmarking.utils.config.Configuration;
import org.optimizationBenchmarking.utils.error.ErrorUtils;
import org.optimizationBenchmarking.utils.error.RethrowMode;
import org.optimizationBenchmarking.utils.io.EOSFamily;
import org.optimizationBenchmarking.utils.io.paths.PathFinderBuilder;
import org.optimizationBenchmarking.utils.io.paths.PathUtils;
import org.optimizationBenchmarking.utils.math.mathEngine.impl.abstr.MathEngineTool;
import org.optimizationBenchmarking.utils.tools.impl.process.ExternalProcess;
import org.optimizationBenchmarking.utils.tools.impl.process.ExternalProcessBuilder;
import org.optimizationBenchmarking.utils.tools.impl.process.ExternalProcessExecutor;

/**
 * <p>
 * The entry point for using {@code R}.
 * </p>
 * <p>
 * Originally, this was intended to be a wrapper for
 * <a href="http://www.rforge.net/JRI/">JRI</a>, the open source and
 * (seemingly) default Java/R Interface. However, implementing such a
 * wrapper would have led to several complex problems: Not only do we need
 * to detect several different binaries or libraries, which itself would
 * have been OK (as we have to detect the {@code R} binary now anyway), we
 * also need to set environment variables. That was the killer argument
 * against it, since the goal of the Tool API is a to be close-to
 * "zero-configuration" and hide everything under the hood. Thus, we don't
 * want to have to set environment variables of {@code R} just to execute
 * our program. Another problem is that JRI is single-threaded.
 * </p>
 * <p>
 * Instead, we therefore run {@code R} as external process, which allows
 * for multiple instances running at the same time. Communication is done
 * via stdin and stdout of that process, and potentially via temporary
 * files when necessary. This has the downside that it is probably much,
 * much slower than what JRI can do.
 * </p>
 */
public final class R extends MathEngineTool {

  /** the parameter denoting the path of the {@code R} binary */
  public static final String PARAM_R_BINARY = "pathOfRBinary"; //$NON-NLS-1$

  /** the user path */
  static final String USER_LIB = "R_LIBS_USER"; //$NON-NLS-1$

  /** the path to the {@code R} executable */
  final Path m_rBinary;

  /**
   * the user library path of {@code R} - not needed under Windows Systems
   */
  final Path m_rUserLibPath;

  /** the parameters to use for running {@code R} */
  final String[] m_params;

  /** create */
  @SuppressWarnings("unused")
  R() {
    super();

    Path r;
    HashSet<String> params;
    int size;
    ExternalProcessExecutor exec;
    ExternalProcessBuilder builder;
    final String[] wantedParams;
    String s;

    r = null;
    params = null;

    exec = ExternalProcessExecutor.getInstance();

    if ((exec != null) && (exec.canUse())) {

      r = new PathFinderBuilder()//
          .addConfigArgVisitFirst(R.PARAM_R_BINARY)//
          .addVisitFirst("/usr/bin/R") //$NON-NLS-1$
          .addVisitFirst("C:\\Program Files\\R\\") //$NON-NLS-1$
          .addVisitFirst("C:\\Program Files (x86)\\R\\") //$NON-NLS-1$
          .addNamePredicate(true, "R", //$NON-NLS-1$
              "Rterm") //$NON-NLS-1$
          .mustBeExecutableFile()//
          .addPathPredicate(new _RAtLeastVersion3Criterion())//
          .addCanExecuteAsTextProcess("--help")//$NON-NLS-1$
          .create().call();

      if (r != null) {
        try {
          builder = exec.use();
          builder.setExecutable(r);
          builder.setMergeStdOutAndStdErr(true);

          builder.setDirectory(PathUtils.getTempDir());
          builder.addStringArgument("--help"); //$NON-NLS-1$

          try (final ExternalProcess ep = builder.create()) {
            try (final InputStreamReader isr = new InputStreamReader(
                ep.getStdOut())) {
              try (final BufferedReader br = new BufferedReader(isr)) {
                params = new HashSet<>();

                wantedParams = new String[] { //
                    "--vanilla", //$NON-NLS-1$
                    "--slave", //$NON-NLS-1$
                    "--no-readline", //$NON-NLS-1$
                    "--no-save", //$NON-NLS-1$
                    "--no-environ", //$NON-NLS-1$
                    "--no-site-file", //$NON-NLS-1$
                    "--no-init-file", //$NON-NLS-1$
                    "--no-restore-data", //$NON-NLS-1$
                    "--no-restore-history", //$NON-NLS-1$
                    "--no-restore", //$NON-NLS-1$
                };

                findParams: while ((s = br.readLine()) != null) {
                  s = s.trim();
                  for (final String t : wantedParams) {
                    if (s.startsWith(t)) {
                      if (params.add(t)) {
                        if (params.size() >= wantedParams.length) {
                          break findParams;
                        }
                      }
                    }
                  }
                }
              }
            }
            if (ep.waitFor() != 0) {
              r = null;
              params = null;
            }
          }

        } catch (final Throwable t) {
          r = null;
        }
      }
    }

    this.m_rBinary = r;
    this.m_params = (((params != null) && ((size = params.size()) > 0))//
        ? params.toArray(new String[size]) : EmptyUtils.EMPTY_STRINGS);
    this.m_rUserLibPath = ((r != null) ? R.__getUserLib() : null);
    System.out.println(this.m_rUserLibPath);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean canUse() {
    return (this.m_rBinary != null);
  }

  /** {@inheritDoc} */
  @Override
  public final REngineBuilder use() {
    this.checkCanUse();
    return new REngineBuilder();
  }

  /**
   * Get the globally shared instance of {@code R}
   *
   * @return the globally shared instance of {@code R}
   */
  public static final R getInstance() {
    return RLoader.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return "R Process Automator"; //$NON-NLS-1$
  }

  /**
   * get/create the path
   *
   * @return the path
   */
  private static final Path __getUserLib() {
    final Path userDir;
    Path path;

    if (EOSFamily.DETECTED == EOSFamily.Windows) {
      return null;
    }

    path = Configuration.getRoot().getPath(R.USER_LIB, null);
    if (path != null) {
      try {
        Files.createDirectories(path);
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
          return path;
        }
      } catch (final Throwable error) {
        ErrorUtils.logError(Configuration.getGlobalLogger(), //
            "Error while trying to create default user library for R.", //$NON-NLS-1$
            error, true, RethrowMode.DONT_RETHROW);
      }
    }

    userDir = PathUtils.getUserHomeDir();
    if (userDir != null) {
      path = PathUtils.createPathInside(userDir, "R/lib"); //$NON-NLS-1$
      if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
        return path;
      }

      path = PathUtils.createPathInside(userDir, "R/library"); //$NON-NLS-1$
      if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
        return path;
      }
    }

    path = PathUtils.createPathInside(PathUtils.getTempDir(), "R/lib"); //$NON-NLS-1$
    try {
      Files.createDirectories(path);
      if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
        return path;
      }
    } catch (final Throwable error) {
      ErrorUtils.logError(Configuration.getGlobalLogger(), //
          "Error while trying to create default user library for R.", //$NON-NLS-1$
          error, true, RethrowMode.DONT_RETHROW);
    }

    return PathUtils.getTempDir();
  }

  /** create the R engine */
  private static final class RLoader {
    /** the shared instance */
    static final R INSTANCE = new R();
  }
}
