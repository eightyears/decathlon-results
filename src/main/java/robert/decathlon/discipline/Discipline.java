package robert.decathlon.discipline;

import java.util.Arrays;
import java.util.List;

public final class Discipline {

    private static final String[] DISCIPLINES
            = {"100 m", "Long jump", "Shot put", "High jump", "400 m", "110 m hurdles", "Discus throw", "Pole vault",
            "Javelin throw", "1500 m"};

    private static final double[] PARAMETERS_A
            = {25.4348, 90.5674, 51.39, 585.64, 1.53775, 5.74354, 12.91, 140.182, 10.14, 0.03768};
    private static final double[] PARAMETERS_B = {18, 2.2, 1.5, 0.75, 82, 28.5, 4, 1, 7, 480};
    private static final double[] PARAMETERS_C = {1.81, 1.4, 1.05, 1.42, 1.81, 1.92, 1.1, 1.35, 1.08, 1.85};

    static final Discipline HUNDRED_M
            = new Discipline(DISCIPLINES[0], Arrays.asList(PARAMETERS_A[0], PARAMETERS_B[0], PARAMETERS_C[0]));
    static final Discipline LONG_JUMP
            = new Discipline(DISCIPLINES[1], Arrays.asList(PARAMETERS_A[1], PARAMETERS_B[1], PARAMETERS_C[1]));
    static final Discipline SHOT_PUT
            = new Discipline(DISCIPLINES[2], Arrays.asList(PARAMETERS_A[2], PARAMETERS_B[2], PARAMETERS_C[2]));
    static final Discipline HIGH_JUMP
            = new Discipline(DISCIPLINES[3], Arrays.asList(PARAMETERS_A[3], PARAMETERS_B[3], PARAMETERS_C[3]));
    static final Discipline FOUR_HUNDRED_M
            = new Discipline(DISCIPLINES[4], Arrays.asList(PARAMETERS_A[4], PARAMETERS_B[4], PARAMETERS_C[4]));
    static final Discipline HURDLES
            = new Discipline(DISCIPLINES[5], Arrays.asList(PARAMETERS_A[5], PARAMETERS_B[5], PARAMETERS_C[5]));
    static final Discipline DISCUS_THROW
            = new Discipline(DISCIPLINES[6], Arrays.asList(PARAMETERS_A[6], PARAMETERS_B[6], PARAMETERS_C[6]));
    static final Discipline POLE_VAULT
            = new Discipline(DISCIPLINES[7], Arrays.asList(PARAMETERS_A[7], PARAMETERS_B[7], PARAMETERS_C[7]));
    static final Discipline JAVELIN_THROW
            = new Discipline(DISCIPLINES[8], Arrays.asList(PARAMETERS_A[8], PARAMETERS_B[8], PARAMETERS_C[8]));
    public static final Discipline ONE_AND_A_HALF_KM
            = new Discipline(DISCIPLINES[9], Arrays.asList(PARAMETERS_A[9], PARAMETERS_B[9], PARAMETERS_C[9]));

    private final String name;
    private final List<Double> parameters;

    private Discipline(final String name, final List<Double> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public List<Double> getParameters() {
        return parameters;
    }
}
