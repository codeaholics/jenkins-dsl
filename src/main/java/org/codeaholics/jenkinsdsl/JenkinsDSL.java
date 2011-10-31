package org.codeaholics.jenkinsdsl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.codeaholics.jenkinsdsl.domain.JobSubjectList;
import org.junit.Assert;

public abstract class JenkinsDSL {
    private static Map<String, ServerConfiguration> serverConfigs = new HashMap<String, ServerConfiguration>();

    protected abstract String getServerUrl();

    protected String getUserName() {
        return null;
    }

    protected String getPassword() {
        return null;
    }

    protected final <T extends Subject> void every(final SubjectList<T> subjects, final Condition<T> condition) {
        final List<ConditionNotMetException> exceptions = new LinkedList<ConditionNotMetException>();

        if (subjects == null || !subjects.hasSubjects()) {
            Assert.fail("No subjects found for testing");
        }

        for (final T subject : subjects) {
            try {
                condition.ensure(subject);
            } catch (final ConditionNotMetException e) {
                exceptions.add(e);
            }
        }

        if (exceptions.size() > 0) {
            fail(exceptions);
        }
    }

    protected final JobSubjectList job() {
        return new JobSubjectList(getServerConfiguration().allJobs());
    }

    protected final <T extends Subject> Condition<T> must(final Matcher<T> matcher) {
        return new Condition<T>() {
            @Override
            public void ensure(final T subject) throws ConditionNotMetException {
                if (!matcher.matches(subject)) {
                    throw new ConditionNotMetException(describeFailure(matcher, subject));
                }
            }
        };
    }

    private <T extends Subject> String describeFailure(final Matcher<T> matcher, final T subject) {
        return String.format("%s should %s, but actually %s.", subject.describe(), matcher.should(), matcher.actually(subject));
    }

    private void fail(final List<ConditionNotMetException> exceptions) {
        final StringBuilder errorMessage = new StringBuilder();

        for (final ConditionNotMetException e : exceptions) {
            errorMessage.append(e.getExplanation()).append('\n');
        }

        Assert.fail(errorMessage.toString());
    }

    private ServerConfiguration getServerConfiguration() {
        final String url = getServerUrl();
        ServerConfiguration configuration = serverConfigs.get(url);
        if (configuration == null) {
            configuration = new ServerConfiguration(url, getUserName(), getPassword());
            serverConfigs.put(url, configuration);
        }
        return configuration;
    }
}
