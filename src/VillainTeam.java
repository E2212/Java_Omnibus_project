import java.util.ArrayList;
import java.util.List;

public class VillainTeam extends Team {
    private final List<Villain> villains = new ArrayList<>();

    public VillainTeam(String name) {
        super(name);
        Main.villainTeams.add(this);
    }

    @Override
    public String toString() {
        String string =
                        "-ID:           | #T-   " + id +   "\n" +
                        "-name:         |       " + name + "\n" +
                        "-Members:              " +        "\n" ;
        for (Villain villain : villains) {
            string += "-    " + villain.getName()      +   "\n" ;
        }
        return string;
    }

    public void addVillain(Villain villain) {
        villains.add(villain);
    }

    public List<Villain> getVillains() {
        return villains;
    }
}