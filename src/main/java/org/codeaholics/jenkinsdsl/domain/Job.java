package org.codeaholics.jenkinsdsl.domain;

import org.codeaholics.jenkinsdsl.Matchable;

public class Job implements Matchable {
    private final JobName name;

    public Job(final JobName name, final String url) {
        this.name = name;
    }

    @Override
    public String describe() {
        return "The job '" + name.asString() + "'";
    }

    public JobName getName() {
        return name;
    }
}
