package org.codeaholics.jenkinsdsl.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codeaholics.jenkinsdsl.SubjectList;

public class JobSubjectList extends SubjectList<Job> {
    public JobSubjectList(final Collection<Job> jobs) {
        super(jobs);
    }

    public SubjectList<JobName> name() {
        final List<Job> subjects = getSubjects();
        final List<JobName> results = new ArrayList<JobName>(subjects.size());

        for (final Job job : subjects) {
            results.add(job.getName());
        }

        return new SubjectList<JobName>(results);
    }
}
