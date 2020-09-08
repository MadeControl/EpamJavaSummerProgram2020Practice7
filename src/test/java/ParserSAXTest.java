import com.epam.rd.java.basic.practice7.ParserSAX;
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

public class ParserSAXTest {

    private static final String inputXmlFile = "input.xml";
    private static final String outputXmlFile = "test.sax.xml";

    @Test
    public void shouldCorrectlyCreateParserWithValidate() {

        People people = parseSAXAndGetPeople(inputXmlFile, true);

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

        People people = parseSAXAndGetPeople(inputXmlFile, false);

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

    public People parseSAXAndGetPeople(String inputXmlFile, boolean validate) {

        ParserSAX parserSAX = new ParserSAX(inputXmlFile);

        try {
            parserSAX.parse(validate);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return parserSAX.getPeople();

    }

}
