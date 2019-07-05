package robert.decathlon.discipline;

import java.util.Arrays;
import java.util.List;

public class DisciplinesProvider {

    private final List<Discipline> disciplinesList;

    public DisciplinesProvider() {
        disciplinesList = Arrays.asList(Discipline.HUNDRED_M, Discipline.LONG_JUMP,
                Discipline.SHOT_PUT, Discipline.HIGH_JUMP, Discipline.FOUR_HUNDRED_M,
                Discipline.HURDLES, Discipline.DISCUS_THROW, Discipline.POLE_VAULT,
                Discipline.JAVELIN_THROW, Discipline.ONE_AND_A_HALF_KM);
    }

    public List<Discipline> getDisciplinesList() {
        return disciplinesList;
    }
}
