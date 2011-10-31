package org.codeaholics.jenkinsdsl.plugins.notifier;

import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Job;

public final class Notifier {
    private Notifier() {}

    public static Matcher<Job> sendJobNotificationsTo(final String url) {
        return new SendJobNotificationsToMatcher(url);
    }
}
