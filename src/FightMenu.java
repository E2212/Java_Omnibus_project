public class FightMenu extends Menu {

    public FightMenu(String title) {
        super(title);
        allowExit = false;
    }

    @Override
    public Menu selectOption(int index) {
        Option option = options.get(index - 1);
        String result;
        if (option.getMenu().getTitle().equals("Individual")) {
            result = handleSoloFight();
        } else {
            result = handleTeamFight();
        }

        Menu resultMenu = new Menu("Fight Result");
        resultMenu.addDetail("The winner is \"" + result + "\"!");


        // We updaten eerst de fights door deze uit de main menu te verwijderen en daarna weer opnieuw op te bouwen
        Main.mainMenu.removeOption(parent);
        Main.setupFightMenu();

        // Parent die op newFightMenu is ingesteld nemen we in het resultaat over
        // zodat we vanaf daar kunnen terug navigeren.
        resultMenu.setParent(Main.mainMenu);

        return resultMenu;
    }

    private String handleSoloFight() {
        System.out.println("Select a hero:");
        for (int i = 0; i < Main.heroes.size(); i++) {
            System.out.println("[" + (i+1) + "] " + Main.heroes.get(i).getName());
        }
        // we gaan er hier van uit dat er altijd 1 hero bestaat
        int heroInput = Main.getIntInput(1, Main.heroes.size());
        Hero hero = Main.heroes.get(heroInput);

        System.out.println("Select a villain:");
        for (int i = 0; i < Main.villains.size(); i++) {
            System.out.println("[" + (i+1) + "] " + Main.villains.get(i).getName());
        }
        // we gaan er hier van uit dat er altijd 1 villain bestaat
        int villainInput = Main.getIntInput(1, Main.heroes.size());
        Villain villain = Main.villains.get(villainInput);

        SoloFight fight = new SoloFight(hero, villain);

        System.out.println("Who wins? (\"h\" = hero, \"v\" = villain, \"n\" = let fate decide.)");
        char[] allowedChars = {'h', 'v', 'n'};
        String fightInput = Main.getSpecificCharInput(allowedChars);

        fight.startFight(fightInput);

        return fight.getWinner().getName();
    }

    private String handleTeamFight() {
        System.out.println("Select a hero team:");
        for (int i = 0; i < Main.heroTeams.size(); i++) {
            System.out.println("[" + (i+1) + "] " + Main.heroTeams.get(i).getName());
        }
        // we gaan er hier van uit dat er altijd 1 hero team bestaat
        int heroTeamInput = Main.getIntInput(1, Main.heroTeams.size());
        HeroTeam heroTeam = Main.heroTeams.get(heroTeamInput);

        System.out.println("Select a villain team:");
        for (int i = 0; i < Main.villainTeams.size(); i++) {
            System.out.println("[" + (i+1) + "] " + Main.villainTeams.get(i).getName());
        }
        // we gaan er hier van uit dat er altijd 1 villain bestaat
        int villainTeamInput = Main.getIntInput(1, Main.villainTeams.size());
        VillainTeam villainTeam = Main.villainTeams.get(villainTeamInput);

        TeamFight fight = new TeamFight(heroTeam, villainTeam);

        System.out.println("Who wins? (\"h\" = heroes, \"v\" = villains, \"n\" = fate decides the winner of the armageddon.)");
        char[] allowedChars = {'h', 'v', 'n'};
        String fightInput = Main.getSpecificCharInput(allowedChars);

        fight.startFight(fightInput);

        return fight.getWinner().getName();
    }

}