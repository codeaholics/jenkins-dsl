package org.codeaholics.jenkinsdsl.domain;

import javax.xml.xpath.XPathExpressionException;

public interface XPathable {
    public String evaluateXPath(String xpath) throws XPathExpressionException;
}
