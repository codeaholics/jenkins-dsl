package org.codeaholics.jenkinsdsl.xml.parsers;

import javax.xml.xpath.XPathExpressionException;

import org.codeaholics.jenkinsdsl.domain.Attribute;
import org.codeaholics.jenkinsdsl.domain.XPathable;

public class SimpleXPathAttribute<T extends XPathable> implements Attribute<T, String> {
    private final String xpath;

    public SimpleXPathAttribute(final String xpath) {
        this.xpath = xpath;
    }

    @Override
    public String get(final T item) {
        try {
            return item.evaluateXPath(xpath);
        } catch (final XPathExpressionException e) {
            throw new AttributeEvaluationException(e);
        }
    }
}
