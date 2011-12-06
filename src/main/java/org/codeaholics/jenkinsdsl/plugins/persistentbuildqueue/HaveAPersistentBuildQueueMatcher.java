package org.codeaholics.jenkinsdsl.plugins.persistentbuildqueue;

import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Job;

public class HaveAPersistentBuildQueueMatcher implements Matcher<Job> {
    private final PersistentBuildQueueAttribute attribute = new PersistentBuildQueueAttribute();

    @Override
    public boolean matches(final Job item) {
        return attribute.get(item);
    }

    @Override
    public String should() {
        return "have a persistent build queue";
    }

    @Override
    public String actually(final Job item) {
        return "doesn't";
    }

}
