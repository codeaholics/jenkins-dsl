package org.codeaholics.jenkinsdsl.xml;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

@SuppressWarnings("unused")
public abstract class XmlHandler {
    private Locator locator;

    public final void setLocator(final Locator locator) {
        this.locator = locator;
    }

    public void onStartElement(final String uri, final String tag, final String qname, final Attributes attrs) throws SAXParseException {
        checkNoAttributes(qname, attrs);
    }

    public XmlHandler onStartChild(final String uri, final String tag, final String qname, final Attributes attrs) throws SAXParseException {
        return null;
    }

    public void onEndChild(final String uri, final String tag, final String qname) throws SAXParseException {
    }

    public void onEndElement(final String uri, final String tag) throws SAXParseException {
    }

    public void characters(final char[] buf, final int start, final int count) throws SAXParseException {
        throw new SAXParseException(String.format("Unexpected character data: '%s'", new String(buf, start, count)), locator);
    }

    protected final void checkNoAttributes(final String qname, final Attributes attrs) throws SAXParseException {
        if (attrs.getLength() > 0) {
            throw new SAXParseException("Element '" + qname + "' did not expect any attributes", locator);
        }
    }
}
