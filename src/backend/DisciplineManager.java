package backend;

import java.util.ArrayList;
import java.util.List;

public class DisciplineManager {
    private static final List<String> disciplines = new ArrayList<>();

    static {
        // Initialisation des disciplines par défaut
        disciplines.add("design1");
        disciplines.add("design2");
        disciplines.add("implementation");
        disciplines.add("test");
        disciplines.add("deployment");
    }

    public static List<String> getAllDisciplines() {
        return new ArrayList<>(disciplines);
    }
}

