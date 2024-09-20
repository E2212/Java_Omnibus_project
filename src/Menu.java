import java.util.ArrayList;
import java.util.List;

public class Menu {
    protected final List<Option> options = new ArrayList<>();
    protected final String title;
    protected Menu parent;
    protected String detail;
    protected boolean allowSearch = false;
    protected boolean allowExit = true;

    public Menu(String title) {
        this.title = title;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public void addOption(Menu menu) {
        menu.setParent(this);
        Option option = new Option(menu);
        options.add(option);
    }

    public void removeOption(Menu menu) {
        for (Option option : options) {
            if (option.getMenu().equals(menu)) {
                options.remove(option);
                break;
            }
        }
    }

    public void showOptions() {
        System.out.println("\n=== " + title + " ===");
        if (detail != null) {
            System.out.println(detail + "\n");
        }

        for (int i = 0; i < options.size(); i++) {
            System.out.println("[" + (i+1) + "] " + options.get(i).getMenu().getTitle());
        }

        if (allowSearch && !options.isEmpty()) {
            System.out.println("[" + (options.size() + 1) + "] Search...");
        }

        if (allowExit) {
            System.out.println("[0] Exit");
        }
    }

    public Menu selectOption(int index) {
        if (index == 0) {
            if (parent == null) {
                System.out.println("Omnibus exited.");
                System.exit(200);
            }
            return parent;
        }

        // De zoek optie staat op de plek na de options
        if (index == options.size() + 1 && allowSearch) {
            System.out.println("What would you like to search for? Only exact matches will be found.");
            String input = Main.getStringInput();

            for (Option option : options) {
                if(option.getMenu().getTitle().equalsIgnoreCase(input)) {
                    return option.getMenu();
                }
            }

            System.out.println("Unfortunately, nothing was found.");
            return this;
        }

        // -1 op de index omdat het list element begint bij 0 en in het menu toont als 1
        Option option = options.get(index - 1);

        return option.getMenu();
    }

    public void addDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setAllowSearch(boolean allowSearch) {
        this.allowSearch = allowSearch;
    }

    public boolean getAllowSearch() {
        return allowSearch;
    }

    public boolean getAllowExit() {
        return allowExit;
    }
}
