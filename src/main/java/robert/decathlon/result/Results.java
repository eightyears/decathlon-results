package robert.decathlon.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "results")
@XmlAccessorType(XmlAccessType.FIELD)
public class Results {

    @XmlElement(name = "result")
    private List<Result> resultsList;

    public Results() {
        resultsList = new ArrayList<>();
    }

    public List<Result> getResultsList() {
        return resultsList;
    }

    public void setResults(final List<Result> resultsList) {
        this.resultsList = resultsList;
    }
}
