package org.codeaholics.jenkinsdsl.plugins.jabber;

import org.codeaholics.jenkinsdsl.Filter;
import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.xml.parsers.AttributeEvaluationException;

import javax.xml.xpath.XPathExpressionException;

public class NotifiesJabberFilter implements Filter<Job> {
    @Override
    public boolean include(final Job item) {
        try {
            return !"".equals(item.evaluateXPath("/project/publishers/hudson.plugins.jabber.im.transport.JabberPublisher"));
        } catch (XPathExpressionException e) {
            throw new AttributeEvaluationException(e);
        }
    }

    @Override
    public String describeExcluded(final Job item) {
        return "it doesn't notify Jabber";
    }
}
