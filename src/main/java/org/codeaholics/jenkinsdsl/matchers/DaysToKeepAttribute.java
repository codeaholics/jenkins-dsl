package org.codeaholics.jenkinsdsl.matchers;

import org.codeaholics.jenkinsdsl.domain.Attribute;
import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.xml.parsers.AttributeEvaluationException;

import javax.xml.xpath.XPathExpressionException;

import static java.lang.Integer.parseInt;

public class DaysToKeepAttribute implements Attribute<Job, Integer> {
    @Override
    public Integer get(final Job target) {
        try {
            return parseInt(target.evaluateXPath("/project/logRotator/daysToKeep"));
        } catch (NumberFormatException e) {
            return -1;
        } catch (XPathExpressionException e) {
            throw new AttributeEvaluationException(e);
        }
    }
}
