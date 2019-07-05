package robert.decathlon.outputwriter;

import robert.decathlon.sportsman.Sportsman;

import java.util.List;

public interface OutputFileWriter {

    boolean writeDataToFile(List<Sportsman> sportsmen, String filename);
}
