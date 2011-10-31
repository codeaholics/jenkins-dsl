package org.codeaholics.jenkinsdsl;

public interface Filter<T> {
    public boolean include(T item);

    /**
     * Describe why an item was excluded by this filter. Complete the blank in "Widget X was excluded because ____."
     */
    public String describeExcluded(T item);
}
