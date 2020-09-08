import com.epam.rd.java.basic.practice7.ParserSAX;
import com.epam.rd.java.basic.practice7.ParserStAX;
import com.epam.rd.java.basic.practice7.People;
import com.epam.rd.java.basic.practice7.Util;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParserSTAXTest {

    private static final String inputXmlFile = "input.xml";
    private static final String outputXmlFile = "test.stax.xml";

    @Test
    public void shouldCorrectlyCreateParserWithValidate() {

        People people = parseSTAXAndGetPeople(inputXmlFile);

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

    public People parseSTAXAndGetPeople(String inputXmlFile) {

        ParserStAX parserSTAX = new ParserStAX(inputXmlFile);

        try {
            parserSTAX.parse();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return parserSTAX.getPeople();

    }

}
