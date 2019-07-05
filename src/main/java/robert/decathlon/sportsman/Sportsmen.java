package robert.decathlon.sportsman;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sportsmen")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sportsmen {

    @XmlElement(name = "sportsman")
    private List<Sportsman> sportsmen;

    public Sportsmen() {
        sportsmen = new ArrayList<>();
    }

    public List<Sportsman> getSportsmen() {
        return sportsmen;
    }

    public void setSportsmen(final List<Sportsman> sportsmen) {
        this.sportsmen = sportsmen;
    }
}
