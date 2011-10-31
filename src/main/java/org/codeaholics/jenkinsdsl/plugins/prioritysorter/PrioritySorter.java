package org.codeaholics.jenkinsdsl.plugins.prioritysorter;

import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Job;

public final class PrioritySorter {
    private PrioritySorter() {}

    public static Matcher<Job> haveAPriorityOf(final int priority) {
        return new HaveAPriorityOfMatcher(priority);
    }
}
