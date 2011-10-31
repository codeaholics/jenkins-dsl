package org.codeaholics.jenkinsdsl.domain;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.codeaholics.jenkinsdsl.DocumentFetcher;
import org.codeaholics.jenkinsdsl.Subject;
import org.w3c.dom.Node;

public class Job implements Subject, XPathable {
    private final JobName name;
    private final String url;
    private final DocumentFetcher documentFetcher;
    private final XPath xpathEngine = XPathFactory.newInstance().newXPath();

    private Node configuration;

    public Job(final JobName name, final String url, final DocumentFetcher documentFetcher) {
        this.name = name;
        this.url = url;
        this.documentFetcher = documentFetcher;
    }

    @Override
    public String describe() {
        return "The job '" + name.asString() + "'";
    }

    public JobName getName() {
        return name;
    }

    @Override
    public String evaluateXPath(final String xpath) throws XPathExpressionException {
        return xpathEngine.evaluate(xpath, getConfiguration());
    }

    private Node getConfiguration() {
        if (configuration == null) {
            configuration = documentFetcher.fetchDocument(url, "config.xml");
        }
        return configuration;
    }
}
