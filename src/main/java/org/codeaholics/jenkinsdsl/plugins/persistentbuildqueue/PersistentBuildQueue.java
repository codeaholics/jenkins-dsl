package org.codeaholics.jenkinsdsl.plugins.persistentbuildqueue;

import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Job;

public final class PersistentBuildQueue {
    private PersistentBuildQueue() {}

    public static Matcher<Job> haveAPersistentBuildQueue() {
        return new HaveAPersistentBuildQueueMatcher();
    }
}
