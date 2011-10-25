package org.codeaholics.jenkinsdsl;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.domain.JobName;
import org.codeaholics.jenkinsdsl.xml.SaxHandler;
import org.codeaholics.jenkinsdsl.xml.WhitespaceFilter;
import org.codeaholics.jenkinsdsl.xml.parsers.AllJobsHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ServerConfiguration {
    private final List<Job> jobs = new LinkedList<Job>();
    private final DocumentFetcher documentFetcher;

    public ServerConfiguration(final String url) {
        documentFetcher = new DocumentFetcher(url);
    }

    public List<JobName> allJobNames() {
        if (jobs.size() == 0) {
            initialiseJobs();
        }

        final List<JobName> names = new LinkedList<JobName>();
        for (final Job job: jobs) {
            names.add(job.getName());
        }

        return names;
    }

    private void initialiseJobs() {
        final InputStream stream = documentFetcher.fetch("api/xml");

        try {
            final SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            parserFactory.setNamespaceAware(false);
            final SAXParser parser = parserFactory.newSAXParser();
            final XMLReader xmlReader = new WhitespaceFilter(parser.getXMLReader());

            final AllJobsHandler jobsParser = new AllJobsHandler();
            final SaxHandler saxHandler = new SaxHandler(jobsParser);

            xmlReader.setContentHandler(saxHandler);
            xmlReader.setEntityResolver(saxHandler);
            xmlReader.setErrorHandler(saxHandler);
            xmlReader.setDTDHandler(saxHandler);

            final InputSource inputSource = new InputSource(stream);

            xmlReader.parse(inputSource);

            jobs.addAll(jobsParser.getJobs());
        } catch (final ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
