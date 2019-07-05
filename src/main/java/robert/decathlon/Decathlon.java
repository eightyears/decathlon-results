package robert.decathlon;

import robert.decathlon.filereader.CsvFileReader;
import robert.decathlon.filereader.InputFileReader;
import robert.decathlon.outputwriter.OutputFileWriter;
import robert.decathlon.outputwriter.XmlOutputWriter;
import robert.decathlon.placingstrategy.PlacingStrategy;
import robert.decathlon.placingstrategy.SharedPlace;
import robert.decathlon.sportsman.Sportsman;

import java.util.List;

public class Decathlon {

    private static final String INPUT_FILE_PATH = "src/main/resources/input/results.csv";
    private static final String OUTPUT_FILE_PATH = "src/main/resources/output/results.xml";

    public static void main(String[] args) {
        InputFileReader fileReader = new CsvFileReader();
        PlacingStrategy strategy = new SharedPlace();
        List<Sportsman> sportsmen = strategy.placeSportsmen(fileReader.getDecathlonResults(INPUT_FILE_PATH));
        OutputFileWriter outputWriter = new XmlOutputWriter();
        outputWriter.writeDataToFile(sportsmen, OUTPUT_FILE_PATH);
    }
}
