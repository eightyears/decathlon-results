package robert.decathlon.outputwriter;

import org.apache.commons.io.FilenameUtils;
import robert.decathlon.sportsman.Sportsman;
import robert.decathlon.sportsman.Sportsmen;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class XmlOutputWriter implements OutputFileWriter {

    private static final String EXTENSION = "xml";

    @Override
    public boolean writeDataToFile(final List<Sportsman> sportsmenList, final String filename) {
        boolean dataSuccessfullyWritten = false;
        Sportsmen sportsmen = sortSportsmenByPlaceAscending(sportsmenList);
        String extension = FilenameUtils.getExtension(filename);
        if (extension.equals(EXTENSION)) {
            try {
                marshalSportsmen(sportsmen, filename);
                dataSuccessfullyWritten = true;
            } catch (JAXBException e) {
                System.out.println("Error while marshalling data!");
                e.printStackTrace();
            }
        }
        return dataSuccessfullyWritten;
    }

    private void marshalSportsmen(final Sportsmen sportsmen, final String filename) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Sportsmen.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(sportsmen, System.out);
        jaxbMarshaller.marshal(sportsmen, new File(filename));
    }

    private Sportsmen sortSportsmenByPlaceAscending(List<Sportsman> sportsmenList) {
        Comparator<Sportsman> comparator = Comparator.comparing(Sportsman::getPlace).reversed();
        sportsmenList = sportsmenList.stream().sorted(comparator).collect(Collectors.toList());
        Sportsmen sportsmen = new Sportsmen();
        sportsmen.setSportsmen(sportsmenList);
        return sportsmen;
    }
}
