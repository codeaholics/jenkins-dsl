package org.codeaholics.jenkinsdsl.matchers;

import java.util.Arrays;
import java.util.List;

import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.JobName;

public final class JobNameMustStartWithAKnownPrefixMatcher implements Matcher<JobName> {
    private final List<String> prefixes;

    public JobNameMustStartWithAKnownPrefixMatcher(final String... prefixes) {
        this.prefixes = Arrays.asList(prefixes);
    }

    @Override
    public boolean matches(final JobName item) {
        final String prefix = getPrefix(item);
        return prefix != null && prefixes.contains(prefix);
    }

    @Override
    public String should() {
        return "begin with a known prefix";
    }

    @Override
    public String actually(final JobName item) {
        final String prefix = getPrefix(item);
        if (prefix != null) {
            return "begins with the prefix '" + prefix + "'";
        } else {
            return "doesn't appear to have a prefix at all";
        }
    }

    private String getPrefix(final JobName item) {
        final String jobName = item.asString();
        final int i = jobName.indexOf('-');
        if (i >= 1) {
            return jobName.substring(0, i);
        } else {
            return null;
        }
    }
}