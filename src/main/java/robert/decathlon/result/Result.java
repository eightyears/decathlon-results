package robert.decathlon.result;

import robert.decathlon.discipline.Discipline;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {

    @XmlTransient
    private Discipline discipline;
    private String performance;

    public Result() {
    }

    public Result(final Discipline discipline, final String performance) {
        this.discipline = discipline;
        this.performance = performance;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public String getPerformance() {
        return performance;
    }

    @Override
    public String toString() {
        return "Discipline: " + discipline.getName() + ", Performance: " + performance;
    }
}
