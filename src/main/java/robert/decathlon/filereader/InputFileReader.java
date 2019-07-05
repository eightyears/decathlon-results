package robert.decathlon.filereader;

import robert.decathlon.sportsman.Sportsman;

import java.util.List;

public interface InputFileReader {

    List<Sportsman> getDecathlonResults(String filename);
}
