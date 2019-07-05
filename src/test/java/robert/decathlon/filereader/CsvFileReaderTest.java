package robert.decathlon.filereader;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class CsvFileReaderTest {

    private static final String FILE_PATH = "src/main/resources/input/results.csv";
    private static final String TEST_FILE_PATH = "src/main/resources/input/test.txt";

    private InputFileReader inputReader;

    @BeforeClass
    public static void setUp() {
        String data = "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72";
        try {
            FileOutputStream out = new FileOutputStream(TEST_FILE_PATH);
            out.write(data.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() {
        inputReader = new CsvFileReader();
    }

    @AfterClass
    public static void doAfterClass() {
        try {
            Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getResultsFromWrongFileFormatReturnsEmptySportsmenList() {
        int numOfSportsmen = inputReader.getDecathlonResults(TEST_FILE_PATH).size();
        assertEquals(0, numOfSportsmen);
    }

    @Test
    public void allSportsmenResultsAreReadFromFile() {
        int numOfSportsmen = inputReader.getDecathlonResults(FILE_PATH).size();
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                lines++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(lines, numOfSportsmen);
    }
}
