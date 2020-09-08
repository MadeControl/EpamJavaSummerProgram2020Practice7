import com.epam.rd.java.basic.practice7.ParserDOM;
import com.epam.rd.java.basic.practice7.People;
import com.epam.rd.java.basic.practice7.Util;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ParserDOMTest {

    private static final String inputXmlFile = "input.xml";
    private static final String outputXmlFile = "test.dom.xml";

    @Test
    public void shouldCorrectlyCreateParserWithValidate() {

        People people = parseDomAndGetPeople(inputXmlFile, true);

        Assert.assertNotNull(people);

        try {
            Util.saveToXML(people, outputXmlFile);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(Files.exists(Paths.get(outputXmlFile)));

    }

    @Test
    public void shouldCorrectlyCreateParserWithoutValidate() {

        People people = parseDomAndGetPeople(inputXmlFile, false);

        Assert.assertNotNull(people);

        try {
            Util.saveToXML(people, outputXmlFile);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(Files.exists(Paths.get(outputXmlFile)));

    }

    @After
    public void deleteTestFile() {

        try {
            Files.deleteIfExists(Paths.get(outputXmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public People parseDomAndGetPeople(String inputXmlFile, boolean validate) {

        ParserDOM parserDOM = new ParserDOM(inputXmlFile);

        try {
            parserDOM.parse(validate);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return parserDOM.getPeople();

    }

}
