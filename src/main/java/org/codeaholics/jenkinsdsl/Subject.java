package org.codeaholics.jenkinsdsl;

public interface Subject {
    /**
     * Describe the object being tested. Complete the blank in "____ should be coloured blue, but was actually red."
     */
    public String describe();
}
