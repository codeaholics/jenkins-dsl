package org.codeaholics.jenkinsdsl.example;

import static org.codeaholics.jenkinsdsl.matchers.StandardMatchers.startWithAKnownPrefix;

import org.codeaholics.jenkinsdsl.JenkinsDSL;
import org.junit.Test;

public class JobsTest extends JenkinsDSL {
    @Override
    protected String getServerUrl() {
        return "http://jenkins:8080/";
    }

    @Test
    public void jobNamesShouldStartWithAKnownPrefix() throws Exception {
        every(jobName(), must(startWithAKnownPrefix("teama", "teamb", "systems")));
    }
}
