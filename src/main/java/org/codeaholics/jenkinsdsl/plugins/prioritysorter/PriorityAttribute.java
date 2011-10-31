package org.codeaholics.jenkinsdsl.plugins.prioritysorter;

import org.codeaholics.jenkinsdsl.domain.Attribute;
import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.xml.parsers.SimpleXPathAttribute;

public class PriorityAttribute implements Attribute<Job, Integer> {
    @Override
    public Integer get(final Job target) {
        final SimpleXPathAttribute<Job> xPathAttribute =
            new SimpleXPathAttribute<Job>("/project/properties/hudson.queueSorter.PrioritySorterJobProperty/priority");
        return Integer.parseInt(xPathAttribute.get(target));
    }
}
