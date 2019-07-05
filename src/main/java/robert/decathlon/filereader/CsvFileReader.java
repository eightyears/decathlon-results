package robert.decathlon.filereader;

import org.apache.commons.io.FilenameUtils;
import robert.decathlon.discipline.Discipline;
import robert.decathlon.discipline.DisciplinesProvider;
import robert.decathlon.result.Result;
import robert.decathlon.result.Results;
import robert.decathlon.sportsman.Sportsman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader implements InputFileReader {

    private static final String EXTENSION = "csv";
    private static final String DELIMITER = ";";
    private static final String SPACE_DELIMITER = " ";
    private static final int DATA_ARRAY_SIZE = 11;

    @Override
    public List<Sportsman> getDecathlonResults(final String filename) {
        List<Sportsman> sportsmen = new ArrayList<>();
        String extension = FilenameUtils.getExtension(filename);
        if (extension.equals(EXTENSION)) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(filename));
                String line;
                while ((line = csvReader.readLine()) != null) {
                    if (line.isEmpty()) {
                        break;
                    }
                    String[] data = line.split(DELIMITER);
                    if (data.length == DATA_ARRAY_SIZE) {
                        sportsmen.add(getSportsman(data));
                    }
                }
                csvReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File is not found! Check if the file path is correct!");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO Exception has occurred while reading the file!");
                e.printStackTrace();
            }
        }
        return sportsmen;
    }

    private Sportsman getSportsman(final String[] data) {
        List<Result> resultsList = new ArrayList<>();
        List<Discipline> disciplines = new DisciplinesProvider().getDisciplinesList();
        int index = 1;
        for (Discipline discipline : disciplines) {
            resultsList.add(new Result(discipline, data[index]));
            index++;
        }
        String[] fullName = data[0].split(SPACE_DELIMITER);
        Results results = new Results();
        results.setResults(resultsList);
        return new Sportsman(fullName[0], fullName[1], results);
    }
}
