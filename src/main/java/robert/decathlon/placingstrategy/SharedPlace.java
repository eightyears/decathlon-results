package robert.decathlon.placingstrategy;

import robert.decathlon.discipline.Discipline;
import robert.decathlon.result.Result;
import robert.decathlon.sportsman.Sportsman;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SharedPlace implements PlacingStrategy {

    private static final String DOT_DELIMITER = "\\.";
    private static final int SECONDS_IN_MINUTE = 60;

    @Override
    public List<Sportsman> placeSportsmen(List<Sportsman> sportsmen) {
        if (sportsmen.isEmpty()) {
            return sportsmen;
        }
        for (Sportsman sportsman : sportsmen) {
            sportsman.setScore(calculateTotalScore(sportsman));
        }
        Comparator<Sportsman> comparator = Comparator.comparing(Sportsman::getScore).reversed();
        sportsmen = sportsmen.stream().sorted(comparator).collect(Collectors.toList());
        return setPlaces(sportsmen);
    }

    private List<Sportsman> setPlaces(final List<Sportsman> sportsmen) {
        int place = 1;
        for (int i = 0; i < sportsmen.size() - 1; i++) {
            if (sportsmen.get(i).getScore() == sportsmen.get(i + 1).getScore()) {
                int j = i;
                int numOfEqualScores = 0;
                while (sportsmen.get(j).getScore() == sportsmen.get(j + 1).getScore()) {
                    numOfEqualScores++;
                    j++;
                    if (j == sportsmen.size() - 1) {
                        break;
                    }
                }
                for (int k = i; k <= j; k++) {
                    sportsmen.get(k).setPlace(place + "-" + (place + numOfEqualScores));
                }
                place += numOfEqualScores + 1;
                i += numOfEqualScores;
            } else {
                sportsmen.get(i).setPlace(String.valueOf(place));
                place++;
            }
        }
        Sportsman lastSportsman = sportsmen.get(sportsmen.size() - 1);
        if (lastSportsman.getPlace() == null || sportsmen.size() == 1) {
            lastSportsman.setPlace(String.valueOf(place));
        }
        return sportsmen;
    }

    private int calculateTotalScore(final Sportsman sportsman) {
        int sum = 0;
        for (Result result : sportsman.getResults().getResultsList()) {
            int score = calculateScore(result);
            sum += score;
        }
        return sum;
    }

    private int calculateScore(final Result result) {
        List<Double> parameters = result.getDiscipline().getParameters();
        double a = parameters.get(0);
        double b = parameters.get(1);
        double c = parameters.get(2);
        double performance;
        if (result.getDiscipline().equals(Discipline.ONE_AND_A_HALF_KM)) {
            String[] time = result.getPerformance().split(DOT_DELIMITER, 2);
            performance = Double.valueOf(time[0]) * SECONDS_IN_MINUTE + Double.valueOf(time[1]);
        } else {
            performance = Double.parseDouble(result.getPerformance());
        }
        return (int) (a * Math.pow(Math.abs(performance - b), c));
    }
}
