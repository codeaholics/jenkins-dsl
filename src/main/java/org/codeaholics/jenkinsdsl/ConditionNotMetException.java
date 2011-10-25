package org.codeaholics.jenkinsdsl;

public class ConditionNotMetException extends Exception {
    private static final long serialVersionUID = 2186957692669177815L;

    private final String explanation;

    public ConditionNotMetException(final String explanation) {
        super("Condition not met: " + explanation);
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }
}
