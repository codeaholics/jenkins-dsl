package org.codeaholics.jenkinsdsl.plugins.scm;

import org.codeaholics.jenkinsdsl.domain.Attribute;
import org.codeaholics.jenkinsdsl.domain.Job;
import org.codeaholics.jenkinsdsl.xml.parsers.SimpleXPathAttribute;

public final class Scm {
    private Scm() {}

    public static Attribute<Job, String> scmProvider() {
        return new SimpleXPathAttribute<Job>("/project/scm/@class");
    }
}
