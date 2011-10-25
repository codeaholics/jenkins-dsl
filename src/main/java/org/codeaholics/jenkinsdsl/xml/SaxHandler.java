package org.codeaholics.jenkinsdsl.xml;

import java.io.IOException;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {
    private final Stack<XmlHandler> xmlHandlers = new Stack<XmlHandler>();

    private XmlHandler currentHandler;
    private Locator locator;

    public SaxHandler(final XmlHandler rootHandler) {
        if (rootHandler == null) {
            throw new NullPointerException("root handler cannot be null");
        }

        xmlHandlers.push(rootHandler);
        currentHandler = rootHandler;
    }

    @Override
    public InputSource resolveEntity(final String publicId, final String systemId) throws IOException, SAXException {
        throw new SAXParseException("Entities are not supported", locator);
    }

    @Override
    public void startElement(final String uri, final String tag, final String qname, final Attributes attrs) throws SAXParseException {
        final XmlHandler next = currentHandler.onStartChild(uri, tag, qname, attrs);

        if (next == null) {
            throw new SAXParseException("Unexpected element \"" + qname + "\" " + tag, locator);
        }

        xmlHandlers.push(currentHandler);
        currentHandler = next;
        currentHandler.setLocator(locator);
        currentHandler.onStartElement(uri, tag, qname, attrs);
    }

    @Override
    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
        currentHandler.setLocator(locator);
    }

    @Override
    public void endElement(final String uri, final String name, final String qName) throws SAXException {
        currentHandler.onEndElement(uri, name);
        final XmlHandler prev = xmlHandlers.pop();
        currentHandler = prev;
        if (currentHandler != null) {
            currentHandler.onEndChild(uri, name, qName);
        }
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        currentHandler.characters(ch, start, length);
    }
}
