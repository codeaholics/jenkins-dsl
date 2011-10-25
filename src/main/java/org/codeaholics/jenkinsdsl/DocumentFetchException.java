package org.codeaholics.jenkinsdsl;

public class DocumentFetchException extends RuntimeException {
    private static final long serialVersionUID = -6317883455488292186L;

    public DocumentFetchException(final Exception e, final String url) {
        super("Failed to fetch " + url, e);
    }
}
