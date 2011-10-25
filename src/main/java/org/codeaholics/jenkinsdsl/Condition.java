package org.codeaholics.jenkinsdsl;

public interface Condition<T> {
    public void ensure(T subject) throws ConditionNotMetException;
}
