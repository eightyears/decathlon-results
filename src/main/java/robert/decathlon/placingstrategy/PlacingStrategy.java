package robert.decathlon.placingstrategy;

import robert.decathlon.sportsman.Sportsman;

import java.util.List;

public interface PlacingStrategy {

    List<Sportsman> placeSportsmen(List<Sportsman> sportsmen);
}
