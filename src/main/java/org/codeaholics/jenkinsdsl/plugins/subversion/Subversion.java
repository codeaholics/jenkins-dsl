package org.codeaholics.jenkinsdsl.plugins.subversion;

import org.codeaholics.jenkinsdsl.Filter;
import org.codeaholics.jenkinsdsl.domain.Job;

public final class Subversion {
    private Subversion() {}

    public static Filter<Job> usesSubversion() {
        return new UsesSubversionFilter();
    }
}
