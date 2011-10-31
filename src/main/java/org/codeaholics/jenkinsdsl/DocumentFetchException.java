package org.codeaholics.jenkinsdsl;

import java.net.MalformedURLException;
import java.net.URL;

public class DocumentFetchException extends RuntimeException {
    private static final long serialVersionUID = -6317883455488292186L;

    public DocumentFetchException(final URL url, final Exception e) {
        super("Failed to fetch url: " + url, e);
    }

    private DocumentFetchException(final String message, final MalformedURLException e) {
        super(message, e);
    }

    public static DocumentFetchException cannotParseUrl(final String baseUrl, final String url, final MalformedURLException e) {
        return new DocumentFetchException("Failed to parse url: " + baseUrl + url, e);
    }
}
