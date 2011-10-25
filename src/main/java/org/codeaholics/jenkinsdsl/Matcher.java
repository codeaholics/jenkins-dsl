package org.codeaholics.jenkinsdsl;

public interface Matcher<T> {
    public boolean matches(T item);

    /**
     * Describe what should be observed. Complete the first blank in "Widget X should ____, but actually ____."
     */
    public String should();

    /**
     * Describe what was actually observed. Complete the second blank in "Widget X should ____, but actually ____."
     *
     * @param item
     * @return
     */
    public String actually(T item);
}
