public class Base extends Gadget {
    private final String location;

    public Base(String name, String description, String location) {
        super(name, description);
        this.location = location;
        Main.bases.add(this);
    }

    @Override
    public String toString() {
        return "-ID:           | #B-"  + id            + "\n" +
               "-name:         | "     + name          + "\n" +
               "-location:     | "     + location      + "\n" +
               "-description:  | "     + description   + "\n";
    }
}
