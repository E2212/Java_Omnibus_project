
public abstract class IterableItem {
    protected int id;
    private static int nextId = 1;
    protected String name;

    protected IterableItem(String name) {
        this.name = name;
        generateUniqueId();
    }

    public abstract String toString();

    private void generateUniqueId() {
        id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
