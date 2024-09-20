public abstract class Gadget extends IterableItem {

    protected final String description;
    protected Super owner;

    public Gadget(String name, String description) {
        super(name);
        this.description = description;
    }

    public void setOwner(Super superChar) {
        this.owner = superChar;
    }
}

