public class Weapon extends Gadget {
    private final int powerLevel;

    public Weapon(String name, String description, int powerLevel) {
        super(name, description);
        this.powerLevel = powerLevel;
        Main.weapons.add(this);
    }

    @Override
    public String toString() {
        return "-ID:           | #W-"  + id           + "\n" +
               "-name:         | "     + name         + "\n" +
               "-description:  | "     + description  + "\n" +
               "-powerLevel:   | "     + powerLevel   + "\n" +
               "-owner:        | "     + owner        + "\n" ;
    }

    public int getPowerLevel() {
        return powerLevel;
    }
}
