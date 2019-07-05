package robert.decathlon.sportsman;

import robert.decathlon.result.Results;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sportsman")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sportsman {

    private String firstName;
    private String secondName;
    private Results results;
    private int score;
    private String place;

    public Sportsman() {
    }

    public Sportsman(final String firstName, final String secondName, final Results results) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.results = results;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Results getResults() {
        return results;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(final String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "\n" + "First name: " + firstName + ", Last name: " + secondName
                + ", Results: " + results + ", Score: " + score + ", Place: " + place;
    }
}
