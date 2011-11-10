package org.codeaholics.jenkinsdsl.plugins.jabber;

public enum NotificationStrategy {
    ALL("All"),
    ANY_FAILURE("Failure"),
    FAILURE_AND_FIXED("Failure and Fixed"),
    STATECHANGE_ONLY("Change");
    private final String simpleName;

    private NotificationStrategy(final String simpleName) {
        this.simpleName = simpleName;
    }

    public String getText() {
        return simpleName;
    }
}
