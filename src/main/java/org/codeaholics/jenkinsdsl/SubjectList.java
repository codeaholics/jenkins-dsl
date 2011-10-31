package org.codeaholics.jenkinsdsl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.codeaholics.jenkinsdsl.domain.Attribute;

public class SubjectList<T extends Subject> implements Iterable<T> {
    private final ArrayList<T> subjects;

    public SubjectList(final Collection<T> subjects) {
        this.subjects = new ArrayList<T>(subjects);
    }

    public <R> SubjectList<T> whose(final Attribute<T, R> attribute, final Filter<R> filter) {
        final List<T> results = new LinkedList<T>();
        for (final T subject : subjects) {
            if (filter.include(attribute.get(subject))) {
                results.add(subject);
            } else {
//                System.out.println(String.format("%s was excluded because %s" + filter.describeExcluded(subject)));
            }
        }
        return new SubjectList<T>(results);
    }

    public SubjectList<T> that(final Filter<T> filter) {
        final List<T> results = new LinkedList<T>();
        for (final T subject : subjects) {
            if (filter.include(subject)) {
                results.add(subject);
            } else {
                System.out.println(String.format("%s was excluded because %s", subject.describe(), filter.describeExcluded(subject)));
            }
        }
        return new SubjectList<T>(results);
    }

    public boolean hasSubjects() {
        return subjects.size() > 0;
    }

    @Override
    public Iterator<T> iterator() {
        return subjects.iterator();
    }

    protected final List<T> getSubjects() {
        return subjects;
    }
}
