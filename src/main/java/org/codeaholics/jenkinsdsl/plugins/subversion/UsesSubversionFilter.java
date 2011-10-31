package org.codeaholics.jenkinsdsl.plugins.subversion;

import static org.codeaholics.jenkinsdsl.plugins.scm.Scm.scmProvider;

import org.codeaholics.jenkinsdsl.Filter;
import org.codeaholics.jenkinsdsl.domain.Job;

public class UsesSubversionFilter implements Filter<Job> {
    @Override
    public boolean include(final Job item) {
        return scmProvider().get(item).equals("hudson.scm.SubversionSCM");
    }

    @Override
    public String describeExcluded(final Job item) {
        return "it doesn't use Subversion";
    }
}
