package robert.decathlon.placingstrategy;

import org.junit.Before;
import org.junit.Test;
import robert.decathlon.discipline.Discipline;
import robert.decathlon.discipline.DisciplinesProvider;
import robert.decathlon.result.Result;
import robert.decathlon.result.Results;
import robert.decathlon.sportsman.Sportsman;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SharedPlaceTest {

    private List<Sportsman> sportsmen;
    private PlacingStrategy strategy;
    private DisciplinesProvider provider;

    @Before
    public void init() {
        provider = new DisciplinesProvider();
        Result testResult = new Result(provider.getDisciplinesList().get(0), "10");
        Results results = new Results();
        results.setResults(Collections.singletonList(testResult));
        Sportsman jack = new Sportsman("Jack", "Johns", results);
        Sportsman chuck = new Sportsman("Chuck", "Norris", results);
        strategy = new SharedPlace();
        sportsmen = strategy.placeSportsmen(Arrays.asList(jack, chuck));
    }

    @Test
    public void placeEmptyListOfSportsmenReturnsEmptyList() {
        sportsmen.clear();
        assertTrue(strategy.placeSportsmen(sportsmen).isEmpty());
    }

    @Test
    public void singleSportsmanHasFirstPlace() {
        sportsmen.remove(0);
        sportsmen = strategy.placeSportsmen(sportsmen);
        assertEquals("1", sportsmen.get(0).getPlace());
    }

    @Test
    public void sportsmanWithHighestScoreHasFirstPlace() {
        Result testResult = new Result(provider.getDisciplinesList().get(0), "5");
        Results testResults = new Results();
        testResults.setResults(Collections.singletonList(testResult));
        Sportsman will = new Sportsman("Will", "Smith", testResults);
        sportsmen.add(will);
        sportsmen = strategy.placeSportsmen(sportsmen);
        assertEquals("1", will.getPlace());
    }

    @Test
    public void sportsmanWithLowestScoreHasLastPlace() {
        Sportsman rob = new Sportsman("Rob", "Zombie", new Results());
        sportsmen.add(rob);
        sportsmen = strategy.placeSportsmen(sportsmen);
        String lastPlace = String.valueOf(sportsmen.size());
        assertEquals(lastPlace, rob.getPlace());
    }

    @Test
    public void sportsmenWithEqualScoresSharePlaces() {
        boolean sharePlaces = sportsmen.get(0).getPlace().equals(sportsmen.get(1).getPlace())
                & sportsmen.get(0).getPlace().equals("1-2");
        assertTrue(sharePlaces);
    }

    @Test
    public void sportsmenWithMultipleEqualScoresSharePlaces() {
        Sportsman morgan = new Sportsman("Morgan", "Freeman", new Results());
        Sportsman tommy = new Sportsman("Tommy", "Wiseau", new Results());
        sportsmen.add(morgan);
        sportsmen.add(tommy);
        sportsmen = strategy.placeSportsmen(sportsmen);
        boolean sharePlaces = sportsmen.get(0).getPlace().equals(sportsmen.get(1).getPlace())
                & sportsmen.get(0).getPlace().equals("1-2")
                & sportsmen.get(2).getPlace().equals(sportsmen.get(3).getPlace())
                & sportsmen.get(2).getPlace().equals("3-4");
        assertTrue(sharePlaces);
    }

    @Test
    public void fastestOneAndHalfKmRunnerHasHighestScore() {
        sportsmen.clear();
        Discipline halfAndOneKm = provider.getDisciplinesList().stream()
                .filter(discipline -> discipline.equals(Discipline.ONE_AND_A_HALF_KM))
                .findAny()
                .orElse(null);
        Result testResult1 = new Result(halfAndOneKm, "4.30.00");
        Result testResult2 = new Result(halfAndOneKm, "5.00.00");
        Results testResults1 = new Results();
        Results testResults2 = new Results();
        testResults1.setResults(Collections.singletonList(testResult1));
        testResults2.setResults(Collections.singletonList(testResult2));
        Sportsman billy = new Sportsman("Billy", "Bones", testResults1);
        Sportsman john = new Sportsman("John", "Silver", testResults2);
        sportsmen.addAll(Arrays.asList(billy, john));
        sportsmen = strategy.placeSportsmen(sportsmen);
        assertTrue(billy.getScore() > john.getScore());
    }
}
