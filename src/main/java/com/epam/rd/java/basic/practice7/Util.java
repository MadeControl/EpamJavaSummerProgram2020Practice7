package com.epam.rd.java.basic.practice7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public final class Util {

    private static final DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();

    private Util() {
    }

    public static Document getDocument(People people) throws ParserConfigurationException {

        DOCUMENT_BUILDER_FACTORY.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        DOCUMENT_BUILDER_FACTORY.setNamespaceAware(true);

        DocumentBuilder documentBuilder = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        // container is people
        Element peopleElement = document.createElement(Constants.XML_PEOPLE);
        document.appendChild(peopleElement);

        for(Man man : people.getManList()) {

            Money manMoney = man.getMoney();

            // container is man
            Element manElement = document.createElement(Constants.XML_MAN);
            peopleElement.appendChild(manElement);

            // man name
            Element nameElement = document.createElement(Constants.XML_MAN_NAME);
            nameElement.setTextContent(man.getName());
            manElement.appendChild(nameElement);

            // man surname
            Element surnameElement = document.createElement(Constants.XML_MAN_SURNAME);
            surnameElement.setTextContent(man.getSurname());
            manElement.appendChild(surnameElement);

            // man age
            Element ageElement = document.createElement(Constants.XML_MAN_AGE);
            ageElement.setTextContent(String.valueOf(man.getAge()));
            manElement.appendChild(ageElement);

            // man money
            Element moneyElement = document.createElement(Constants.XML_MAN_MONEY);
            manElement.appendChild(moneyElement);

            // money currency
            Element currencyMoneyElement = document.createElement(Constants.XML_MONEY_CURRENCY);
            currencyMoneyElement.setTextContent(manMoney.getCurrency());
            moneyElement.appendChild(currencyMoneyElement);

            // money amount
            Element moneyAmountElement = document.createElement(Constants.XML_MONEY_AMOUNT);
            moneyAmountElement.setTextContent(String.valueOf(manMoney.getAmount()));
            moneyElement.appendChild(moneyAmountElement);

        }

        return document;
    }

    public static void saveToXML(People people, String xmlFileName)
            throws ParserConfigurationException, TransformerException {

        saveToXML(getDocument(people), xmlFileName);
    }

    public static void saveToXML(Document document, String xmlFileName) throws TransformerException {

        StreamResult result = new StreamResult(new File(xmlFileName));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(new DOMSource(document), result);
    }

}
