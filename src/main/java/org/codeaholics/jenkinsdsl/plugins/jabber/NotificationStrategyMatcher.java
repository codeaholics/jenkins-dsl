package org.codeaholics.jenkinsdsl.plugins.jabber;

import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Job;

public class NotificationStrategyMatcher implements Matcher<Job> {
    private final NotificationStrategyAttribute notificationStrategyAttribute = new NotificationStrategyAttribute();
    private final NotificationStrategy expectedStrategy;

    public NotificationStrategyMatcher(final NotificationStrategy expectedStrategy) {
        this.expectedStrategy = expectedStrategy;
    }

    @Override
    public boolean matches(final Job item) {
        return notificationStrategyAttribute.get(item) == expectedStrategy;
    }

    @Override
    public String should() {
        return "have the notification strategy " + expectedStrategy.getText();
    }

    @Override
    public String actually(final Job item) {
        return "has the notification strategy " + notificationStrategyAttribute.get(item).getText();
    }
}
