import java.util.ArrayList;
import java.util.List;

public class HeroTeam extends Team {
    private final List<Hero> heroes = new ArrayList<>();

    public HeroTeam(String name) {
        super(name);
        Main.heroTeams.add(this);
    }

    @Override
    public String toString() {
        String string =
                        "-ID:           | #T-   " + id +   "\n" +
                        "-name:         |       " + name + "\n" +
                        "-Members:      |       " +        "\n";
        for (Hero hero : heroes) {
            string += "-    " + hero.getName()    +        "\n";
        }
        return string;
    }

    public void addHero(Hero hero) {
        heroes.add(hero);
    }

    public List<Hero> getHeroes() {
        return heroes;
    }
}
