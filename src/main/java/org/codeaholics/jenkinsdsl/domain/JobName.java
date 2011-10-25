package org.codeaholics.jenkinsdsl.domain;

import org.codeaholics.jenkinsdsl.Matchable;

public class JobName implements Matchable {
    private final String name;

    public JobName(final String name) {
        this.name = name;
    }

    public String asString() {
        return name;
    }

    @Override
    public String describe() {
        return "The name of job '" + name + "'";
    }
}
