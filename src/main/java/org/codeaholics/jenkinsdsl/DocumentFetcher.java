package org.codeaholics.jenkinsdsl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.codeaholics.jenkinsdsl.xml.SaxHandler;
import org.codeaholics.jenkinsdsl.xml.WhitespaceFilter;
import org.codeaholics.jenkinsdsl.xml.XmlHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class DocumentFetcher {
    private final String userName;
    private final String password;

    public DocumentFetcher(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

    public void parse(final String baseUrl, final String url, final XmlHandler handler) {
        final URL targetUrl = getTargetUrl(baseUrl, url);

        try {
            final SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            parserFactory.setNamespaceAware(false);
            final SAXParser parser = parserFactory.newSAXParser();
            final XMLReader xmlReader = new WhitespaceFilter(parser.getXMLReader());

            final SaxHandler saxHandler = new SaxHandler(handler);

            xmlReader.setContentHandler(saxHandler);
            xmlReader.setEntityResolver(saxHandler);
            xmlReader.setErrorHandler(saxHandler);
            xmlReader.setDTDHandler(saxHandler);

            final InputSource inputSource = new InputSource(fetch(targetUrl));

            xmlReader.parse(inputSource);
        } catch (final ParserConfigurationException e) {
            throw new DocumentFetchException(targetUrl, e);
        } catch (final SAXException e) {
            throw new DocumentFetchException(targetUrl, e);
        } catch (final IOException e) {
            throw new DocumentFetchException(targetUrl, e);
        }
    }

    public Node fetchDocument(final String baseUrl, final String url) {
        final URL targetUrl = getTargetUrl(baseUrl, url);

        try {
            final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(false);
            final DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

            final Document document = documentBuilder.parse(fetch(targetUrl));
            return document.getDocumentElement();
        } catch (final ParserConfigurationException e) {
            throw new DocumentFetchException(targetUrl, e);
        } catch (final SAXException e) {
            throw new DocumentFetchException(targetUrl, e);
        } catch (final IOException e) {
            throw new DocumentFetchException(targetUrl, e);
        }
    }

    private InputStream fetch(final URL targetUrl) {
        try {
            final URLConnection connection = targetUrl.openConnection();
            if (userName != null) {
                addAuthenticationDetails(connection);
            }
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            final int status = ((HttpURLConnection)connection).getResponseCode();
            if (status != 200) {
                throw new IOException(String.format("Server returned response code %d while trying to fetch %s", status, targetUrl));
            }
            return connection.getInputStream();
        } catch (final IOException e) {
            throw new DocumentFetchException(targetUrl, e);
        }
    }

    private void addAuthenticationDetails(final URLConnection connection) {
        final String userPassword = userName + ":" + password;
        final String encoding = Base64.encodeBytes(userPassword.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encoding);
    }

    private URL getTargetUrl(final String baseUrl, final String url) {
        try {
            final String target = baseUrl.endsWith("/") ? baseUrl + url : baseUrl + "/" + url;
            return new URL(target);
        } catch (final MalformedURLException e) {
            throw DocumentFetchException.cannotParseUrl(baseUrl, url, e);
        }
    }
}
