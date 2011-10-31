package org.codeaholics.jenkinsdsl.plugins.prioritysorter;

import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Job;

public class HaveAPriorityOfMatcher implements Matcher<Job> {
    private final PriorityAttribute priorityAttribute = new PriorityAttribute();
    private final int expectedPriority;

    public HaveAPriorityOfMatcher(final int priority) {
        this.expectedPriority = priority;
    }

    @Override
    public boolean matches(final Job item) {
        return priorityAttribute.get(item) == expectedPriority;
    }

    @Override
    public String should() {
        return String.format("have a priority of %d", expectedPriority);
    }

    @Override
    public String actually(final Job item) {
        return String.format("has a priority of %d", priorityAttribute.get(item));
    }
}
