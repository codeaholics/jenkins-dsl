package org.codeaholics.jenkinsdsl.plugins.persistentbuildqueue;

import org.codeaholics.jenkinsdsl.domain.Attribute;
import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.xml.parsers.SimpleXPathAttribute;

public class PersistentBuildQueueAttribute implements Attribute<Job, Boolean> {
    @Override
    public Boolean get(final Job target) {
        final SimpleXPathAttribute<Job> xPathAttribute =
            new SimpleXPathAttribute<Job>("count(/project/buildWrappers/org.jenkins.ci.plugins.PersistentBuildQueueBuildWrapper)");
        return Integer.parseInt(xPathAttribute.get(target)) == 1;
    }
}
