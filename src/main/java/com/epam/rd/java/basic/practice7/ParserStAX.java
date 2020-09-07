package com.epam.rd.java.basic.practice7;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;
import java.util.Objects;

public class ParserStAX {

    private static final XMLInputFactory XML_INPUT_FACTORY = XMLInputFactory.newInstance();
    private final String inputXmlFile;
    private People people;
    private Man man;
    private Money money;

    public ParserStAX(String inputXmlFile) {
        this.inputXmlFile = inputXmlFile;
    }

    public void parse() throws XMLStreamException {

        String currentElement = null;

        XML_INPUT_FACTORY.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        XML_INPUT_FACTORY.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = XML_INPUT_FACTORY.createXMLEventReader(new StreamSource(inputXmlFile));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                methodForStartElement(currentElement);
            }

            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                methodForCharacters(currentElement, characters);
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if (Constants.XML_MAN.equals(localName)) {
                    people.getManList().add(man);
                }
            }
        }
        reader.close();
    }

    public People getPeople() {
        return people;
    }

    private void methodForStartElement(String currentElement) {

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
            Objects.requireNonNull(man).setMoney(money);
        }

    }

    private void methodForCharacters(String currentElement, Characters characters) {

        if (Constants.XML_MAN_NAME.equals(currentElement)) {
            Objects.requireNonNull(man).setName(characters.getData());
            return;
        }

        if (Constants.XML_MAN_SURNAME.equals(currentElement)) {
            Objects.requireNonNull(man).setSurname(characters.getData());
            return;
        }

        if (Constants.XML_MAN_AGE.equals(currentElement)) {
            Objects.requireNonNull(man).setAge(Long.parseLong(characters.getData()));
            return;
        }

        if (Constants.XML_MONEY_CURRENCY.equals(currentElement)) {
            Objects.requireNonNull(money).setCurrency(characters.getData());
            return;
        }

        if (Constants.XML_MONEY_AMOUNT.equals(currentElement)) {
            Objects.requireNonNull(money).setAmount(Double.parseDouble(characters.getData()));
        }

    }
}
