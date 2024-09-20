public class Vehicle extends Gadget{
    private final int speed;
    private final int capacity;

    public Vehicle(String name, String description, int speed, int capacity) {
        super(name, description);
        this.speed = speed;
        this.capacity = capacity;
        Main.vehicles.add(this);
    }

    @Override
    public String toString() {
        return "-ID:           | #V-"  + id           + "\n" +
               "-name:         | "     + name         + "\n" +
               "-description:  | "     + description  + "\n" +
               "-speed:        | "     + speed        + "\n" +
               "-realName:     | "     + capacity     + "\n" ;
    }
}

