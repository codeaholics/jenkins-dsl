package org.codeaholics.jenkinsdsl.plugins.jabber;

import org.codeaholics.jenkinsdsl.Filter;
import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Job;

public final class Jabber {
    private Jabber() {}

    public static Filter<Job> notifiesJabber() {
        return new NotifiesJabberFilter();
    }

    public static Matcher<Job> haveJabberNotificationStrategy(final NotificationStrategy strategy) {
        return new NotificationStrategyMatcher(strategy);
    }
}
