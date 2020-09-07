package com.epam.rd.java.basic.practice7;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public final class Main {

    public static void main(final String[] args) throws TransformerException, ParserConfigurationException {

        String xmlFile = args[0];

        ParserSAX parserSAX = new ParserSAX(xmlFile);
        try {
            parserSAX.parse(true);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        People people = parserSAX.getPeople();

        Util.saveToXML(people, Constants.XML_OUTPUT_SAX);

    }
}
