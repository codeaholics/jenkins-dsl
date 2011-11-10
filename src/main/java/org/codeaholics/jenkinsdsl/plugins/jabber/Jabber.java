package org.codeaholics.jenkinsdsl.plugins.jabber;

import org.codeaholics.jenkinsdsl.Filter;
import org.codeaholics.jenkinsdsl.Matcher;
import org.codeaholics.jenkinsdsl.domain.Job;

public class Jabber {
    private Jabber() {
    }

    public static Filter<Job> notifiesJabber() {
        return new NotifiesJabberFilter();
    }

    public static Matcher<Job> haveJabberNotificationStrategy(NotificationStrategy strategy) {
        return new NotificationStrategyMatcher(strategy);
    }
}
