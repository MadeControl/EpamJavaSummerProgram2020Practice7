package com.epam.rd.java.basic.practice7;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.logging.Logger;

public final class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    public static void main(final String[] args) throws TransformerException, ParserConfigurationException {

        String xmlFile = args[0];


        ParserDOM parserDOM = new ParserDOM(xmlFile);
        try {
            parserDOM.parse(true);
        } catch (SAXException | IOException e) {
            LOGGER.warning(e.getMessage());
        }
        People people = parserDOM.getPeople();
        Util.saveToXML(people, Constants.XML_OUTPUT_DOM);


        ParserSAX parserSAX = new ParserSAX(xmlFile);
        try {
            parserSAX.parse(true);
        } catch (SAXException | IOException e) {
            LOGGER.warning(e.getMessage());
        }
        people = parserSAX.getPeople();
        Util.saveToXML(people, Constants.XML_OUTPUT_SAX);


        ParserStAX parserStAX = new ParserStAX(xmlFile);
        try {
            parserStAX.parse();
        } catch (XMLStreamException e) {
            LOGGER.warning(e.getMessage());
        }
        people = parserStAX.getPeople();
        Util.saveToXML(people, Constants.XML_OUTPUT_STAX);

    }

}
