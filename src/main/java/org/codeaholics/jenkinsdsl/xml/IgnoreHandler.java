package org.codeaholics.jenkinsdsl.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;

public class IgnoreHandler extends XmlHandler {
    @Override
    public XmlHandler onStartChild(final String uri, final String tag, final String qname, final Attributes attrs) throws SAXParseException {
        return this;
    }

    @Override
    public void characters(final char[] buf, final int start, final int count) throws SAXParseException {
    }
}
