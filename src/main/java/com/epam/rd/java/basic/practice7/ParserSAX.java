package com.epam.rd.java.basic.practice7;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ParserSAX extends DefaultHandler {

    private static final SAXParserFactory SAX_PARSER_FACTORY = SAXParserFactory.newInstance();
    private final String inputXmlFile;
    private People people;
    private Man man;
    private Money money;

    private String currentElement;

    public ParserSAX(String inputXmlFile) {
        this.inputXmlFile = inputXmlFile;
    }

    public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {

        SAX_PARSER_FACTORY.setNamespaceAware(true);

        if (validate) {
            SAX_PARSER_FACTORY.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            SAX_PARSER_FACTORY.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        SAXParser parser = SAX_PARSER_FACTORY.newSAXParser();
        parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        parser.parse(inputXmlFile, this);

    }

    public People getPeople() {
        return people;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        currentElement = localName;

        if (Constants.XML_PEOPLE.equals(currentElement)) {
            people = new People();
            return;
        }

        if (Constants.XML_MAN.equals(currentElement)) {
            man = new Man();
            return;
        }

        if (Constants.XML_MAN_MONEY.equals(currentElement)) {
            money = new Money();
            man.setMoney(money);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {

        String elementText = new String(ch, start, length).trim();

        if (elementText.isEmpty()) {
            return;
        }

        if(Constants.XML_MAN_NAME.equals(currentElement)) {
            man.setName(elementText);
            return;
        }

        if(Constants.XML_MAN_SURNAME.equals(currentElement)) {
            man.setSurname(elementText);
            return;
        }

        if(Constants.XML_MAN_AGE.equals(currentElement)) {
            man.setAge(Long.parseLong(elementText));
            return;
        }

        if (Constants.XML_MONEY_CURRENCY.equals(currentElement)) {
            money.setCurrency(elementText);
            return;
        }

        if (Constants.XML_MONEY_AMOUNT.equals(currentElement)) {
            money.setAmount(Double.parseDouble(elementText));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (Constants.XML_MAN.equals(localName)) {
            people.getManList().add(man);
        }

    }


}

