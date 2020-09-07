package com.epam.rd.java.basic.practice7;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;

public class ParserStAX {

    private final String inputXmlFile;
    private People people;

    public ParserStAX(String inputXmlFile) {
        this.inputXmlFile = inputXmlFile;
    }

    public void parse() throws XMLStreamException {

        Man man = null;
        Money money = null;

        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = factory.createXMLEventReader(new StreamSource(inputXmlFile));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                if (Constants.XML_PEOPLE.equals(currentElement)) {
                    people = new People();
                    continue;
                }

                if (Constants.XML_MAN.equals(currentElement)) {
                    man = new Man();
                    continue;
                }

                if (Constants.XML_MAN_MONEY.equals(currentElement)) {
                    money = new Money();
                    man.setMoney(money);
                    continue;
                }
            }

            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (Constants.XML_MAN_NAME.equals(currentElement)) {
                    man.setName(characters.getData());
                    continue;
                }

                if (Constants.XML_MAN_SURNAME.equals(currentElement)) {
                    man.setSurname(characters.getData());
                    continue;
                }

                if (Constants.XML_MAN_AGE.equals(currentElement)) {
                    man.setAge(Long.parseLong(characters.getData()));
                    continue;
                }

                if (Constants.XML_MONEY_CURRENCY.equals(currentElement)) {
                    money.setCurrency(characters.getData());
                    continue;
                }

                if (Constants.XML_MONEY_AMOUNT.equals(currentElement)) {
                    money.setAmount(Double.parseDouble(characters.getData()));
                    continue;
                }
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
}
