import java.util.ArrayList;
import java.util.List;

public abstract class Super extends Person {
    protected final String location;
    protected String oneLiner = "Fear me!!!";
    protected final int powerLevel;
    protected final ArrayList<Gadget> gadgets = new ArrayList<>();
    protected final ArrayList<Fan> fans = new ArrayList<>();

    protected Super(String name, String location, int powerLevel) {
        super(name);
        this.location = location;
        this.powerLevel = powerLevel;
    }

    public void addVehicle(String name, String description, int speed, int capacity) {
        Vehicle vehicle = new Vehicle(name, description, speed, capacity);
        gadgets.add(vehicle);
        vehicle.setOwner(this);
    }
    public void addBase(String name, String description, String location) {
        Base base = new Base(name,description,location);
        gadgets.add(base);
        base.setOwner(this);
    }
    public void addWeapon(String name, String description, int powerLevel) {
        Weapon weapon = new Weapon(name,description,powerLevel);
        gadgets.add(weapon);
        weapon.setOwner(this);
    }
  
    public String getOneLiner() {
        return oneLiner;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public List<Gadget> getGadgets() {
        return gadgets;
    }

    public void addFan(Fan fan) {
        fans.add(fan);
    }

    public void removeFan(Fan fan) {
        fans.remove(fan);
    }

    public List<Fan> getFans() {
        return fans;
    }
}
