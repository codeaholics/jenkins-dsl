package org.codeaholics.jenkinsdsl.plugins.notifier;

import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.xml.parsers.SimpleXPathAttribute;

public class NotificationUrlAttribute extends SimpleXPathAttribute<Job> {
    public NotificationUrlAttribute() {
        super("/project/properties/com.tikal.hudson.plugins.notification.HudsonNotificationProperty/endpoints/com.tikal.hudson.plugins.notification.Endpoint/url");
    }
}
