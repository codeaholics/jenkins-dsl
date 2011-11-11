package org.codeaholics.jenkinsdsl.matchers;

import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.domain.JobName;

public class StandardMatchers {
    public static Matcher<JobName> startWithAKnownPrefix(final String... prefixes) {
        return new JobNameMustStartWithAKnownPrefixMatcher(prefixes);
    }

    public static Matcher<Job> retainBuildLogsFor(final int number, final RetentionUnit unit) {
        return new BuildLogMatcher(number, unit);
    }
}
