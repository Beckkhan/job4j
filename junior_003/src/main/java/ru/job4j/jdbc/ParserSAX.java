package ru.job4j.jdbc;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.03.2019
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserSAX extends DefaultHandler {

    private long sum;

    public long getSum() {
        return sum;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("entry")) {
            String field = attributes.getValue("field");
            sum += Integer.valueOf(field);
        }
    }
}