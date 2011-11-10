package org.codeaholics.jenkinsdsl.plugins.jabber;

import org.codeaholics.jenkinsdsl.domain.Attribute;
import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.xml.parsers.AttributeEvaluationException;

import javax.xml.xpath.XPathExpressionException;

public class NotificationStrategyAttribute implements Attribute<Job, NotificationStrategy> {
    @Override
    public NotificationStrategy get(final Job target) {
        try {
            return NotificationStrategy.valueOf(target.evaluateXPath("/project/publishers/hudson.plugins.jabber.im.transport.JabberPublisher/strategy"));
        } catch (XPathExpressionException e) {
            throw new AttributeEvaluationException(e);
        }
    }
}
