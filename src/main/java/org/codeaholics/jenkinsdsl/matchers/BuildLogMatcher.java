package org.codeaholics.jenkinsdsl.matchers;

import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Attribute;
import org.codeaholics.jenkinsdsl.domain.Job;

public class BuildLogMatcher implements Matcher<Job> {
    private final int number;
    private final RetentionUnit unit;
    private Attribute<Job, Integer> retentionAttribute;

    public BuildLogMatcher(final int number, final RetentionUnit unit) {
        this.number = number;
        this.unit = unit;
        switch (unit) {
            case DAYS:
                retentionAttribute = new DaysToKeepAttribute();
        }
    }

    @Override
    public boolean matches(final Job item) {
        return retentionAttribute.get(item) == number;
    }

    @Override
    public String should() {
        return "have retained build logs for " + number + " " + unit;
    }

    @Override
    public String actually(final Job item) {
        return "retains build logs for " + retentionAttribute.get(item) + " " + unit;
    }
}
