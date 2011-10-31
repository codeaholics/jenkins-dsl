package org.codeaholics.jenkinsdsl.plugins.notifier;

import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Job;

public class SendJobNotificationsToMatcher implements Matcher<Job> {
    private final NotificationUrlAttribute urlAttribute = new NotificationUrlAttribute();
    private final String expectedUrl;

    public SendJobNotificationsToMatcher(final String url) {
        this.expectedUrl = url;
    }

    @Override
    public boolean matches(final Job item) {
        return urlAttribute.get(item).equals(expectedUrl);
    }

    @Override
    public String should() {
        return String.format("send job notifications to '%s'", expectedUrl);
    }

    @Override
    public String actually(final Job item) {
        final String actualUrl = urlAttribute.get(item);
        if (actualUrl == null || actualUrl.trim().length() == 0) {
            return "doesn't appear to send notifications at all";
        } else {
            return String.format("sends job notifications to '%s'", actualUrl);
        }
    }
}
