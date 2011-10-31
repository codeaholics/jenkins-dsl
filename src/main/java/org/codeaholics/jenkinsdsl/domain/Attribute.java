package org.codeaholics.jenkinsdsl.domain;

public interface Attribute<T, R> {
    R get(T target);
}
