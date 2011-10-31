package org.codeaholics.jenkinsdsl.xml.parsers;

import java.util.LinkedList;
import java.util.List;

import org.codeaholics.jenkinsdsl.DocumentFetcher;
import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.domain.JobName;
import org.codeaholics.jenkinsdsl.xml.IgnoreHandler;
import org.codeaholics.jenkinsdsl.xml.XmlHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;

public class AllJobsHandler extends XmlHandler {
    private final DocumentFetcher documentFetcher;
    private final List<Job> jobs = new LinkedList<Job>();

    public AllJobsHandler(final DocumentFetcher documentFetcher) {
        this.documentFetcher = documentFetcher;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    @Override
    public XmlHandler onStartChild(final String uri, final String tag, final String qname, final Attributes attrs) throws SAXParseException {
        if (qname.equals("hudson")) {
            return new RootHandler();
        } else {
            return null;
        }
    }

    private class RootHandler extends XmlHandler {
        @Override
        public XmlHandler onStartChild(final String uri, final String tag, final String qname, final Attributes attrs) throws SAXParseException {
            if (qname.equals("job")) {
                return new JobHandler();
            } else {
                return new IgnoreHandler();
            }
        }
    }

    private class JobHandler extends XmlHandler {
        private TextHandler nameHandler;
        private TextHandler urlHandler;

        @Override
        public XmlHandler onStartChild(final String uri, final String tag, final String qname, final Attributes attrs) throws SAXParseException {
            if (qname.equals("name")) {
                if (nameHandler == null) {
                    nameHandler = new TextHandler();
                    return nameHandler;
                } else {
                    return null; // duplicate name
                }
            } else if (qname.equals("url")) {
                if (urlHandler == null) {
                    urlHandler = new TextHandler();
                    return urlHandler;
                } else {
                    return null; // duplicate url
                }
            } else {
                return new IgnoreHandler();
            }
        }

        @Override
        public void onEndElement(final String uri, final String tag) throws SAXParseException {
            jobs.add(new Job(new JobName(nameHandler.text), urlHandler.text, documentFetcher));
        }
    }

    private class TextHandler extends XmlHandler {
        private String text;

        @Override
        public void characters(final char[] buf, final int start, final int count) throws SAXParseException {
            text = new String(buf, start, count);
        }
    }
}
