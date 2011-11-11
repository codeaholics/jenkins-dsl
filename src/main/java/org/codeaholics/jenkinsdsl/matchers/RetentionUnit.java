package org.codeaholics.jenkinsdsl.matchers;

public enum RetentionUnit {
    DAYS;
    // TODO: BUILDS

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
