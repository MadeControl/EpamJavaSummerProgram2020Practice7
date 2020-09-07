package com.epam.rd.java.basic.practice7;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ParserDOM {

    private final String inputXmlFile;
    private People people;

    public ParserDOM(String inputXmlFile) {
        this.inputXmlFile = inputXmlFile;
    }

    public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        documentBuilderFactory.setNamespaceAware(true);

        if (validate) {
            documentBuilderFactory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            documentBuilderFactory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e;
            }
        });

        Document document = documentBuilder.parse(inputXmlFile);
        Element root = document.getDocumentElement();

        people = new People();

        NodeList menNodes = root.getElementsByTagName(Constants.XML_MAN);

        for (int j = 0; j < menNodes.getLength(); j++) {
            Man man = getMan(menNodes.item(j));
            people.getManList().add(man);
        }
    }

    private static Man getMan(Node manNode) {

        Man man = new Man();
        Element manElement = (Element) manNode;

        Node nameNode = manElement.getElementsByTagName(Constants.XML_MAN_NAME).item(0);
        man.setName(nameNode.getTextContent());

        Node surnameNode = manElement.getElementsByTagName(Constants.XML_MAN_SURNAME).item(0);
        man.setSurname(surnameNode.getTextContent());

        Node ageNode = manElement.getElementsByTagName(Constants.XML_MAN_AGE).item(0);
        man.setAge(Long.parseLong(ageNode.getTextContent()));

        Node moneyNode = manElement.getElementsByTagName(Constants.XML_MAN_MONEY).item(0);
        Money money = getMoney(moneyNode);
        man.setMoney(money);

        return man;
    }

    private static Money getMoney(Node moneyNode) {

        Money answer = new Money();
        Element aElement = (Element) moneyNode;

        Node moneyCurrency = aElement.getElementsByTagName(Constants.XML_MONEY_CURRENCY).item(0);
        answer.setCurrency(moneyCurrency.getTextContent());

        Node moneyAmount = aElement.getElementsByTagName(Constants.XML_MONEY_AMOUNT).item(0);
        answer.setAmount(Double.parseDouble(moneyAmount.getTextContent()));

        return answer;
    }

    public People getPeople() {
        return people;
    }
}
