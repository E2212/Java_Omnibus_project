import java.util.ArrayList;

public class Hero extends Super {
    private final String realName;
    private Villain archEnemy;
    private final ArrayList<Villain> enemies = new ArrayList<>();

    public Hero(String name, String location,int powerLevel, String realName) {
        super(name, location, powerLevel);
        this.realName = realName;
    }

    public Hero(String name, String location,int powerLevel, String realName, String oneLiner) {
        super(name, location, powerLevel);
        this.realName = realName;
        this.oneLiner = oneLiner;
        Main.heroes.add(this);
    }

    @Override
    public String toString() {
        String string =
                "-ID:           | #H-"  + id           + "\n" +
                "-name:         | "     + name         + "\n" +
                "-location:     | "     + location     + "\n" +
                "-powerLevel:   | "     + powerLevel   + "\n" +
                "-realName:     | "     + realName     + "\n" +
                "-oneLiner:     | "     + oneLiner     + "\n" +
                "-archEnemy:    | "     + archEnemy.getName() + "\n" +
                "\n\n" +

                        "-Enemies:" + "\n";
        for (Villain villain : enemies) {
            string += "-    " + villain.getName()   + "\n";
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

    public void addArchEnemy(Villain villain) {
        archEnemy = villain;
    }

    public void addEnemy(Villain villain) {
        enemies.add(villain);
    }

    public Villain getArchEnemy() {
        return archEnemy;
    }

    public ArrayList<Villain> getEnemies() {
        return enemies;
    }
}
