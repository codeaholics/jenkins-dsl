package org.codeaholics.jenkinsdsl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DocumentFetcher {
    private final String baseUrl;

    public DocumentFetcher(final String url) {
        this.baseUrl = url.endsWith("/") ? url : url + "/";
    }

    public InputStream fetch(final String url) {
        final String targetUrl = baseUrl + url;

        try {
            final URLConnection connection = new URL(targetUrl).openConnection();
            final int status = ((HttpURLConnection)connection).getResponseCode();
            if (status != 200) {
                throw new IOException(String.format("Server returned response code %d while trying to fetch %s", status, targetUrl));
            }
            return connection.getInputStream();
        } catch (final IOException e) {
            throw new DocumentFetchException(e, targetUrl);
        }
    }

}
