package org.codeaholics.jenkinsdsl;

import java.util.LinkedList;
import java.util.List;

import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.xml.parsers.AllJobsHandler;

public class ServerConfiguration {
    private final String url;
    private final List<Job> jobs = new LinkedList<Job>();
    private final DocumentFetcher documentFetcher;

    public ServerConfiguration(final String url, final String userName, final String password) {
        this.url = url;
        documentFetcher = new DocumentFetcher(userName, password);
    }

    public List<Job> allJobs() {
        return getJobs();
    }

    private List<Job> getJobs() {
        if (jobs.size() == 0) {
            initialiseJobs();
        }

        return jobs;
    }

    private void initialiseJobs() {
        final AllJobsHandler jobsParser = new AllJobsHandler(documentFetcher);

        documentFetcher.parse(url, "api/xml", jobsParser);
        jobs.addAll(jobsParser.getJobs());
    }
}
