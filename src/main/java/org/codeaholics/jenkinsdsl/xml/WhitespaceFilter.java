package org.codeaholics.jenkinsdsl.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

public class WhitespaceFilter extends XMLFilterImpl
{
    public WhitespaceFilter(final XMLReader parent)
    {
        setParent(parent);
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException
    {
        if (new String(ch, start, length).trim().length() != 0)
        {
            super.characters(ch, start, length);
        }
    }

    @Override
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException
    {
        // always ignore
    }
}
