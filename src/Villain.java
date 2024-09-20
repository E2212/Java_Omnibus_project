import java.util.ArrayList;

public class Villain extends Super {
    private final String evilPlan;
    private Hero archEnemy;
    private final ArrayList<Hero> enemies = new ArrayList<>();

    public Villain(String name, String location, int powerLevel, String evilPlan) {
        super(name, location, powerLevel);
        this.evilPlan = evilPlan;
    }

    public Villain(String name, String location, int powerLevel, String evilPlan, String oneLiner) {
        super(name, location, powerLevel);
        this.evilPlan = evilPlan;
        this.oneLiner = oneLiner;
        Main.villains.add(this);
    }

    @Override
    public String toString() {
        String string =
                        "-ID:           | #V-" + id + "\n" +
                        "-name:         | " + name + "\n" +
                        "-location:     | " + location + "\n" +
                        "-powerLevel:   | " + powerLevel + "\n" +
                        "-evilPlan:     | " + evilPlan + "\n" +
                        "-oneLiner:     | " + oneLiner + "\n" +
                        "-archEnemy:    | " + archEnemy.getName() + "\n" +
                        "\n\n" +
                        "-Enemies:" + "\n";
        for (Hero hero : enemies) {
            string += "-    " + hero.getName()      + "\n";
        }
        string += "-Fans\n";
        for (Fan fan : fans) {
            string += "-    " + fan.getName()       + "\n";
        }
        string += "-Gadgets\n";
        for (Gadget gadget : gadgets) {
            string += "-    " + gadget.getName()    + "\n";
        }
        return string;
    }

    public void addArchEnemy(Hero hero) {
        archEnemy = hero;
    }

    public void addEnemy(Hero hero) {
        enemies.add(hero);
    }

    public Hero getArchEnemy() {
        return this.archEnemy;
    }
}
