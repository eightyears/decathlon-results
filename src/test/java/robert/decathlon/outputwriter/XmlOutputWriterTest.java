package robert.decathlon.outputwriter;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;
import robert.decathlon.discipline.DisciplinesProvider;
import robert.decathlon.placingstrategy.PlacingStrategy;
import robert.decathlon.placingstrategy.SharedPlace;
import robert.decathlon.result.Result;
import robert.decathlon.result.Results;
import robert.decathlon.sportsman.Sportsman;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XmlOutputWriterTest {

    private static final String TEST_FILE_PATH = "src/main/resources/output/test.txt";
    private static final String CONTROL_XML = "src/main/resources/output/control.xml";
    private static final String OUTPUT_XML_TEST = "src/main/resources/output/output.xml";

    private List<Sportsman> sportsmen;
    private OutputFileWriter outputWriter;

    @BeforeClass
    public static void setUp() {
        try {
            FileOutputStream out = new FileOutputStream(TEST_FILE_PATH);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        XMLUnit.setIgnoreWhitespace(true);
    }

    @Before
    public void init() {
        DisciplinesProvider provider = new DisciplinesProvider();
        PlacingStrategy strategy = new SharedPlace();
        outputWriter = new XmlOutputWriter();
        Result result1 = new Result(provider.getDisciplinesList().get(0), "10");
        Result result2 = new Result(provider.getDisciplinesList().get(1), "5");
        Results results = new Results();
        results.setResults(Arrays.asList(result1, result2));
        Sportsman homer = new Sportsman("Homer", "Simpson", results);
        Sportsman marge = new Sportsman("Marge", "Simpson", results);
        sportsmen = strategy.placeSportsmen(Arrays.asList(homer, marge));
    }

    @AfterClass
    public static void doAfterClass() {
        try {
            Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
            Files.deleteIfExists(Paths.get(CONTROL_XML));
            Files.deleteIfExists(Paths.get(OUTPUT_XML_TEST));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeDataToWrongFileFormatReturnsFalse() {
        boolean success = outputWriter.writeDataToFile(Collections.emptyList(), TEST_FILE_PATH);
        assertFalse(success);
    }

    @Test
    public void writtenXmlDocumentIsSimilarToExpected() throws IOException, SAXException {
        String control = generateControlXmlString();
        try {
            FileOutputStream out = new FileOutputStream(CONTROL_XML);
            out.write(control.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputWriter.writeDataToFile(sportsmen, OUTPUT_XML_TEST);
        Diff diff = new Diff(new FileReader(CONTROL_XML), new FileReader(OUTPUT_XML_TEST));
        assertTrue(diff.similar());
    }

    private String generateControlXmlString() {
        String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";
        StringBuilder controlXml = new StringBuilder(xmlHeader + "<sportsmen>");
        for (Sportsman sportsman : sportsmen) {
            int numOfResults = sportsman.getResults().getResultsList().size();
            StringBuilder results = new StringBuilder();
            for (int i = 0; i < numOfResults; i++) {
                String performance = sportsman.getResults().getResultsList().get(i).getPerformance();
                results.append("<result><performance>").append(performance).append("</performance></result>");
            }
            String singleSportsmanStructure = "<sportsman><firstName>" + sportsman.getFirstName()
                    + "</firstName><secondName>" + sportsman.getSecondName() + "</secondName>"
                    + "<results>" + results + "</results><score>" + sportsman.getScore()
                    + "</score><place>" + sportsman.getPlace() + "</place></sportsman>";
            controlXml.append(singleSportsmanStructure);
        }
        controlXml.append("</sportsmen>");
        return controlXml.toString();
    }
}
