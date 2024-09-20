import java.util.ArrayList;
import java.util.List;

public class Fan extends Person {
    private final List<Super> favorites = new ArrayList<>();

    protected Fan(String name) {
        super(name);
        Main.fans.add(this);
    }

    @Override
    public String toString() {
        String string =
                        "-ID:           | #F-"  + id            + "\n" +
                        "-name:         | "     + name          + "\n" +
                        "\n\n" +

                                "-Fan of:" + "\n";
       for (Super favorite : favorites) {
            string += "-    " + favorite.getName() + "\n";
        }
        return string;
    }


    public void addFavorite(Super superCharacter){
        favorites.add(superCharacter);
        superCharacter.addFan(this);
    }

    public void removeFavorite(Super superCharacter){
        favorites.remove(superCharacter);
        superCharacter.removeFan(this);
    }

    public List<Super> getFavorites() {
        return favorites;
    }
}

