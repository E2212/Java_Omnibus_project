import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static final ArrayList<Hero> heroes = new ArrayList<>();
    public static final ArrayList<Villain> villains = new ArrayList<>();
    public static final ArrayList<Fan> fans = new ArrayList<>();
    public static final ArrayList<HeroTeam> heroTeams = new ArrayList<>();
    public static final ArrayList<VillainTeam> villainTeams = new ArrayList<>();
    public static final ArrayList<Weapon> weapons = new ArrayList<>();
    public static final ArrayList<Base> bases = new ArrayList<>();
    public static final ArrayList<Vehicle> vehicles = new ArrayList<>();
    public static final ArrayList<TeamFight> teamFights = new ArrayList<>();
    public static final ArrayList<SoloFight> soloFights = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static final Menu mainMenu = new Menu("Main menu");

    public static void main(String[] args) {
        initDataSet();

        setupMenus();

        System.out.println("\nWelcome to the Omnibus\n");
        runMenu(mainMenu);
    }

    public static void runMenu(Menu menu) {
        menu.showOptions();
        int availableOptions = menu.getOptions().size();

        // Bij Search komt er een extra "optie" bij
        if (menu.getAllowSearch()) {
            availableOptions++;
        }

        int minInt = 1;
        if (menu.getAllowExit()) {
            minInt = 0;
        }

        int optionIndex = getIntInput(minInt, availableOptions);
        Menu nextMenu = menu.selectOption(optionIndex);
        runMenu(nextMenu);
    }

    public static int getIntInput(int min, int availableOptions) {
        int choice = -1;
        System.out.println("\nSelect an option.");
        while (choice < min || choice > availableOptions) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < min || choice > availableOptions) {
                    System.out.println("\nPlease enter a number from " + min + " to " + availableOptions + ", inclusive.");
                }
            } else {
                // Omdat er geen int is ingevuld moet de input "geconsumeerd" worden.
                scanner.next();
                System.out.println("\nPlease enter a valid number from " + min + " to " + availableOptions + ", inclusive.");
            }
        }

        return choice;
    }

    public static String getStringInput() {
        String input = "";
        while (input.isEmpty()) {
            if (scanner.hasNext()) {
                input = scanner.nextLine();
            }
        }
        return input;
    }

    public static String getSpecificCharInput(char[] allowedChars) {
        String input = "";
        while (!stringExistsCharArray(allowedChars, input)) {
            if (scanner.hasNext()) {
                input = scanner.next();

                if (!stringExistsCharArray(allowedChars, input)) {
                    System.out.println("\nPlease enter one of the following characters: " + Arrays.toString(allowedChars));
                }
            }
        }
        return input;
    }

    public static boolean stringExistsCharArray(char[] haystack, String needle) {
        for (char c : haystack) {
            if (needle.equals(String.valueOf(c))) {
                return true;
            }
        }

        return false;
    }


    private static void setupMenus() {
        setupPeopleMenu();
        setupGadgetMenu();
        setupFightMenu();

        mainMenu.setAllowSearch(true);
    }

    public static void setupFightMenu() {
        Menu fightMenu = new Menu("Menu Fights");
        fightMenu.setAllowSearch(true);

        Menu soloFightMenu = new Menu("Solo Fights");
        soloFightMenu.setAllowSearch(true);
        for (SoloFight soloFight : soloFights) {
            Menu soloFightDetail = new Menu(soloFight.getHero().getName() + " vs " + soloFight.getVillain().getName());
            soloFightDetail.addDetail("\"" + soloFight.getWinner().getName() + "\" was the winner of this fight.");
            soloFightMenu.addOption(soloFightDetail);
        }
        fightMenu.addOption(soloFightMenu);

        Menu teamFightMenu = new Menu("Team Fights");
        teamFightMenu.setAllowSearch(true);
        for (TeamFight teamFight : teamFights) {
            Menu soloFightDetail = new Menu(teamFight.getHeroTeam().getName() + " vs " + teamFight.getVillainTeam().getName());
            soloFightDetail.addDetail("\"" + teamFight.getWinner().getName() + "\" was the winner of this fight.");
            teamFightMenu.addOption(soloFightDetail);
        }
        fightMenu.addOption(teamFightMenu);

        // Achter gevechten zit wat logica die we niet als een standaard menu kunnen behandelen
        FightMenu newFightMenu = new FightMenu("New Fight");
        newFightMenu.addDetail("Fight between individuals or teams?");
        newFightMenu.addOption(new Menu("Individual"));
        newFightMenu.addOption(new Menu("Teams"));

        // Stel het fight menu als parent in zodat we hier naar kunnen navigeren aan het einde van een gevecht
        newFightMenu.setParent(fightMenu);

        fightMenu.addOption(newFightMenu);

        mainMenu.addOption(fightMenu);
    }


    private static void setupGadgetMenu() {
        Menu gadgetMenu = new Menu("Menu Gadgets");
        gadgetMenu.setAllowSearch(true);

        Menu weaponMenu = new Menu("Menu Weapons");
        weaponMenu.setAllowSearch(true);
        for (Weapon weapon : weapons) {
            Menu weaponDetail = new Menu("#W-" + weapon.getId() + " | " + weapon.getName());
            weaponDetail.addDetail(weapon.toString());
            weaponMenu.addOption(weaponDetail);
        }
        gadgetMenu.addOption(weaponMenu);


        Menu baseMenu = new Menu("Menu Bases");
        baseMenu.setAllowSearch(true);
        for (Base base : bases) {
            Menu baseDetail = new Menu("#B-" + base.getId() + " | " + base.getName());
            baseDetail.addDetail(base.toString());
            baseMenu.addOption(baseDetail);
        }
        gadgetMenu.addOption(baseMenu);

        Menu vehicleMenu = new Menu("Menu Vehicles");
        vehicleMenu.setAllowSearch(true);
        for (Vehicle vehicle : vehicles) {
            Menu vehicleDetail = new Menu("#V-" + vehicle.getId() + " | " + vehicle.getName());
            vehicleDetail.addDetail(vehicle.toString());
            vehicleMenu.addOption(vehicleDetail);
        }
        gadgetMenu.addOption(vehicleMenu);

        mainMenu.addOption(gadgetMenu);
    }

    private static void setupPeopleMenu() {
        Menu peopleMenu = new Menu("Menu People");
        peopleMenu.setAllowSearch(true);

        Menu heroMenu = new Menu("Menu Heroes");
        heroMenu.setAllowSearch(true);
        for (Hero hero : heroes) {
            Menu heroDetail = new Menu("#H-" + hero.getId() + " | " + hero.getName());
            heroDetail.addDetail(hero.toString());
            heroMenu.addOption(heroDetail);
        }
        peopleMenu.addOption(heroMenu);

        Menu villainMenu = new Menu("Menu Villains");
        villainMenu.setAllowSearch(true);
        for (Villain villain : villains) {
            Menu villainDetail = new Menu("#V-" + villain.getId() + " | " + villain.getName());
            villainDetail.addDetail(villain.toString());
            villainMenu.addOption(villainDetail);
        }
        peopleMenu.addOption(villainMenu);

        Menu fanMenu = new Menu("Menu Fans");
        fanMenu.setAllowSearch(true);
        for (Fan fan : fans) {
            Menu fanDetail = new Menu("#F-" + fan.getId() + " | " + fan.getName());
            fanDetail.addDetail(fan.toString());
            fanMenu.addOption(fanDetail);
        }
        peopleMenu.addOption(fanMenu);

        Menu heroTeamMenu = new Menu("Menu Hero Team");
        heroTeamMenu.setAllowSearch(true);
        for (HeroTeam heroTeam : heroTeams) {
            Menu teamDetail = new Menu("#HT-" + heroTeam.getId() + " | " + heroTeam.getName());
            teamDetail.addDetail(heroTeam.toString());
            heroTeamMenu.addOption(teamDetail);
        }
        peopleMenu.addOption(heroTeamMenu);

        Menu villainTeamMenu = new Menu("Menu Villain Team");
        villainTeamMenu.setAllowSearch(true);
        for (VillainTeam villainTeam : villainTeams) {
            Menu teamDetail = new Menu("#VT-" + villainTeam.getId() + " | " + villainTeam.getName());
            teamDetail.addDetail(villainTeam.toString());
            villainTeamMenu.addOption(teamDetail);
        }
        peopleMenu.addOption(villainTeamMenu);

        mainMenu.addOption(peopleMenu);
    }

    private static void initDataSet() {
        //Hero init.
        Hero thor = new Hero("Thor", "Asgard", 95, "Thor Odinson", "Bring me Thanos!");
        Hero hulk = new Hero("Hulk", "USA", 90, "Bruce Banner", "Hulk smash!");
        Hero captainAmerica = new Hero("Captain America", "New York", 80, "Steve Rogers", "I can do this all day.");
        Hero blackWidow = new Hero("Black Widow", "Russia", 75, "Natasha Romanoff");
        Hero doctorStrange = new Hero("Doctor Strange", "New York", 88, "Stephen Strange", "We're in the endgame now.");
        Hero spiderMan = new Hero("Spider-Man", "New York", 78, "Peter Parker", "With great power comes great responsibility.");
        Hero blackPanther = new Hero("Black Panther", "Wakanda", 83, "T'Challa", "Wakanda forever!");
        Hero antMan = new Hero("Ant-Man", "San Francisco", 70, "Scott Lang");
        Hero wolverine = new Hero("Wolverine", "Canada", 85, "Logan", "I'm the best there is at what I do, but what I do isn't very nice.");
        Hero solarFlare = new Hero("Solar Flare", "Los Angeles", 82, "Maya Hartley", "Let there be light!");
        Hero batman = new Hero("Batman", "Arkaham city", 89, "Bruce Wayne", "I am Batman.");
        Hero superman = new Hero("Superman", "Metropolis", 95, "Clark Kent", "Truth, Justice, and the American Way.");

        //Hero Gadgets.
        thor.addWeapon("Mjolnir", "Magical hammer that only the worthy can lift", 300);
        thor.addBase("Asgardian Palace", "The royal palace of Asgard", "Asgard");

        hulk.addBase("Secret Lab", "A secluded lab to control transformations", "Hidden location");
        hulk.addWeapon("Gamma Enhancer", "Device to boost strength", 150);

        captainAmerica.addWeapon("Shield", "Vibranium shield", 100);
        captainAmerica.addBase("Brooklyn Apartment", "Steve's personal residence", "Brooklyn, NY");

        blackWidow.addWeapon("Widow's Bite", "Electroshock weapon", 50);
        blackWidow.addVehicle("Stealth Bike", "High-speed motorcycle with stealth capabilities", 220, 1);

        doctorStrange.addBase("Sanctum Sanctorum", "Protective and mystical base", "Greenwich Village, NY");
        doctorStrange.addWeapon("Sling Ring", "Magical ring for creating portals", 120);

        spiderMan.addWeapon("Web-Shooters", "Devices that shoot a strong spider-web string", 60);
        spiderMan.addBase("Aunt May's Apartment", "Peter's home with Aunt May", "Queens, NY");

        blackPanther.addBase("Royal Palace", "The center of governance in Wakanda", "Wakanda");
        blackPanther.addWeapon("Vibranium Suit", "Black Panther's battle suit", 150);

        antMan.addVehicle("Ant-Van", "Van equipped with quantum tunnel", 100, 5);
        antMan.addWeapon("Pym Particle Discs", "Discs that shrink or enlarge objects", 80);

        wolverine.addWeapon("Adamantium Claws", "Retractable claws coated with indestructible metal", 200);
        wolverine.addBase("Xavier's School", "Safe haven and training site for mutants", "Westchester, NY");

        solarFlare.addWeapon("Solar Staff", "High-tech staff that releases solar energy", 100);
        solarFlare.addVehicle("Photon Glider", "Solar-powered glider capable of supersonic speeds", 1200, 1);

        batman.addWeapon("Explosive gel", "Known as water gel explosive, dynamite shaped.", 160);
        batman.addVehicle("Batmobile", "Batmobile is the primary vehicle Batman uses.", 450, 1);
        batman.addBase("The Batcave", "The Batcave is a subterranean location beneath Batman's personal residence.", "Arkaham City");

        superman.addWeapon("Heat Vision", "Concentrated thermal beams emitted from his eyes.", 200);
        superman.addVehicle("Fortress of Solitude Crystal Ship", "An alien ship that also serves as a transport vehicle.", 500, 1);
        superman.addBase("Fortress of Solitude", "A fortress containing knowledge of Kryptonian culture and science, located in the Arctic.", "Arctic");

        //Villain init
        Villain thanos = new Villain("Thanos", "Titan", 99, "Erase half of all life", "I am inevitable.");
        Villain loki = new Villain("Loki", "Asgard", 88, "Conquer Earth", "I am burdened with glorious purpose.");
        Villain ultron = new Villain("Ultron", "Earth", 90, "Eradicate humanity");
        Villain redSkull = new Villain("Red Skull", "Germany", 80, "Use the Tesseract to win the war", "Hail Hydra.");
        Villain hela = new Villain("Hela", "Asgard", 95, "Take over Asgard", "Asgard is not a place, it's a people.");
        Villain killmonger = new Villain("Erik Killmonger", "Wakanda", 85, "Overthrow Wakandan monarchy");
        Villain vulture = new Villain("Vulture", "New York", 70, "Steal alien technology", "The world's changing, it's time we change too.");
        Villain doctorOctopus = new Villain("Doctor Octopus", "New York", 85, "Rebuild the sun reactor");
        Villain greenGoblin = new Villain("Green Goblin", "New York", 80, "Control New York's underworld", "Out, am I?");
        Villain sabretooth = new Villain("Sabretooth", "Canada", 84, "Killing people", "Miaauuw.");
        Villain joker = new Villain("Joker", "Arkham City", 92, "Create chaos and destroy Batman", "Why so serious?");
        Villain lexLuthor = new Villain("Lex Luthor", "Metropolis", 91, "Achieve global domination and eliminate Superman", "Knowledge is Power.");

        //Villain Gadgets.
        thanos.addWeapon("Infinity Gauntlet", "A gauntlet embedded with all six Infinity Stones", 500);
        thanos.addBase("Sanctuary II", "Massive warship serving as Thanos' base", "Space");

        loki.addWeapon("Scepter", "Powerful staff housing the Mind Stone", 150);
        loki.addBase("Loki's Palace", "A temporary palace when in control", "Asgard");

        ultron.addBase("Ultron's Lair", "Main base of operations", "Secluded Island");
        ultron.addWeapon("Drone Army", "Army of robotic drones", 200);

        redSkull.addWeapon("Hydra Weapons", "Advanced weaponry developed by Hydra", 90);
        redSkull.addVehicle("Hydra Tank", "Heavily armored combat vehicle", 80, 10);

        hela.addWeapon("Necroswords", "Blades formed from dark energy", 200);
        hela.addBase("Hel's Throne", "Throne in the realm of Hel", "Hel");

        killmonger.addWeapon("Vibranium Suit", "Black Panther suit stolen from Wakanda", 150);
        killmonger.addBase("Wakandan Throne Room", "The center of power in Wakanda", "Wakanda");

        vulture.addVehicle("Vulture Suit", "Flying exo-suit with advanced weaponry", 100, 1);
        vulture.addWeapon("Chitauri Tech", "Modified alien technology weapons", 120);

        doctorOctopus.addWeapon("Mechanical Arms", "Highly versatile and strong robotic arms", 140);
        doctorOctopus.addBase("Underground Lab", "Secret laboratory for experiments", "New York");

        greenGoblin.addVehicle("Goblin Glider", "High-speed, armed glider", 150, 1);
        greenGoblin.addWeapon("Pumpkin Bombs", "Explosive devices shaped like pumpkins", 130);

        sabretooth.addWeapon("Bone claws", "Retractable bone claws with brute strength", 180);

        joker.addWeapon("Acid Flower", "A flower attached to Joker's lapel that sprays corrosive acid.", 70);
        joker.addVehicle("Jokermobile", "A customized vehicle with various traps and gadgets designed for chaos.", 300, 1);
        joker.addBase("Abandoned Amusement Park", "A hideout styled as a creepy amusement park filled with deadly traps.", "Arkham City");

        lexLuthor.addWeapon("Kryptonite Ray", "A weapon that emits a radiation harmful to Kryptonians.", 150);
        lexLuthor.addVehicle("LexCorp Jet", "Advanced jet equipped with high-tech weaponry and defenses.", 400, 1);
        lexLuthor.addBase("LexCorp Tower", "Skyscraper that serves as the headquarters for LexCorp and Luthor's operations.", "Metropolis");

        //VillainTeam init.
        VillainTeam chaosMakers = new VillainTeam("Chaos Makers");
        chaosMakers.addVillain(joker);
        chaosMakers.addVillain(greenGoblin);

        VillainTeam cosmicThreats = new VillainTeam("Cosmic Threats");
        cosmicThreats.addVillain(thanos);
        cosmicThreats.addVillain(loki);

        VillainTeam darkRulers = new VillainTeam("Dark Rulers");
        darkRulers.addVillain(hela);
        darkRulers.addVillain(lexLuthor);

        VillainTeam techTerrors = new VillainTeam("Tech Terrors");
        techTerrors.addVillain(ultron);
        techTerrors.addVillain(doctorOctopus);

        VillainTeam warMongers = new VillainTeam("War Mongers");
        warMongers.addVillain(redSkull);
        warMongers.addVillain(killmonger);

        VillainTeam cityScourge = new VillainTeam("City Scourge");
        cityScourge.addVillain(vulture);
        cityScourge.addVillain(sabretooth);

        //HeroTeam init.
        HeroTeam cosmicWarriors = new HeroTeam("Cosmic Warriors");
        cosmicWarriors.addHero(thor);
        cosmicWarriors.addHero(superman);

        HeroTeam streetDefenders = new HeroTeam("Street Defenders");
        streetDefenders.addHero(spiderMan);
        streetDefenders.addHero(batman);

        HeroTeam scienceHeroes = new HeroTeam("Science Heroes");
        scienceHeroes.addHero(hulk);
        scienceHeroes.addHero(antMan);

        HeroTeam tacticalFighters = new HeroTeam("Tactical Fighters");
        tacticalFighters.addHero(captainAmerica);
        tacticalFighters.addHero(blackWidow);

        HeroTeam mysticalGuardians = new HeroTeam("Mystical Guardians");
        mysticalGuardians.addHero(doctorStrange);
        mysticalGuardians.addHero(solarFlare);

        HeroTeam freedomFighters = new HeroTeam("Freedom Fighters");
        freedomFighters.addHero(blackPanther);
        freedomFighters.addHero(wolverine);

        //Giving enemies to Hero.
        thor.addEnemy(vulture);
        thor.addEnemy(thanos);
        thor.addEnemy(ultron);
        thor.addArchEnemy(doctorOctopus);

        hulk.addEnemy(greenGoblin);
        hulk.addEnemy(killmonger);
        hulk.addEnemy(redSkull);
        hulk.addArchEnemy(loki);

        captainAmerica.addEnemy(greenGoblin);
        captainAmerica.addEnemy(vulture);
        captainAmerica.addEnemy(hela);
        captainAmerica.addArchEnemy(killmonger);

        blackWidow.addEnemy(thanos);
        blackWidow.addEnemy(killmonger);
        blackWidow.addEnemy(loki);
        blackWidow.addArchEnemy(redSkull);

        doctorStrange.addEnemy(killmonger);
        doctorStrange.addEnemy(vulture);
        doctorStrange.addEnemy(hela);
        doctorStrange.addArchEnemy(doctorOctopus);

        spiderMan.addEnemy(doctorOctopus);
        spiderMan.addEnemy(killmonger);
        spiderMan.addEnemy(loki);
        spiderMan.addArchEnemy(greenGoblin);

        blackPanther.addEnemy(hela);
        blackPanther.addEnemy(loki);
        blackPanther.addEnemy(ultron);
        blackPanther.addArchEnemy(redSkull);

        antMan.addEnemy(vulture);
        antMan.addEnemy(doctorOctopus);
        antMan.addEnemy(redSkull);
        antMan.addArchEnemy(thanos);

        wolverine.addEnemy(hela);
        wolverine.addEnemy(greenGoblin);
        wolverine.addEnemy(doctorOctopus);
        wolverine.addArchEnemy(sabretooth);

        solarFlare.addEnemy(loki);
        solarFlare.addEnemy(redSkull);
        solarFlare.addEnemy(sabretooth);
        solarFlare.addArchEnemy(vulture);
      
        batman.addEnemy(sabretooth);
        batman.addEnemy(redSkull);
        batman.addEnemy(doctorOctopus);
        batman.addArchEnemy(joker);
        
        superman.addEnemy(loki);
        superman.addEnemy(redSkull);
        superman.addEnemy(ultron);
        superman.addArchEnemy(lexLuthor);

        // Giving enemies to Villian.
        thanos.addEnemy(thor);
        thanos.addEnemy(hulk);
        thanos.addEnemy(captainAmerica);
        thanos.addArchEnemy(doctorStrange);

        loki.addEnemy(thor);
        loki.addEnemy(hulk);
        loki.addEnemy(captainAmerica);
        loki.addArchEnemy(blackWidow);

        ultron.addEnemy(hulk);
        ultron.addEnemy(captainAmerica);
        ultron.addEnemy(blackWidow);
        ultron.addArchEnemy(hulk);

        redSkull.addEnemy(captainAmerica);
        redSkull.addEnemy(thor);
        redSkull.addEnemy(blackWidow);
        redSkull.addArchEnemy(captainAmerica);

        hela.addEnemy(thor);
        hela.addEnemy(hulk);
        hela.addEnemy(doctorStrange);
        hela.addArchEnemy(thor);

        killmonger.addEnemy(blackPanther);
        killmonger.addEnemy(spiderMan);
        killmonger.addEnemy(antMan);
        killmonger.addArchEnemy(blackPanther);

        vulture.addEnemy(spiderMan);
        vulture.addEnemy(hulk);
        vulture.addEnemy(doctorStrange);
        vulture.addArchEnemy(spiderMan);

        doctorOctopus.addEnemy(spiderMan);
        doctorOctopus.addEnemy(hulk);
        doctorOctopus.addEnemy(captainAmerica);
        doctorOctopus.addArchEnemy(spiderMan);

        greenGoblin.addEnemy(spiderMan);
        greenGoblin.addEnemy(hulk);
        greenGoblin.addEnemy(blackWidow);
        greenGoblin.addArchEnemy(spiderMan);

        sabretooth.addEnemy(wolverine);
        sabretooth.addEnemy(hulk);
        sabretooth.addEnemy(captainAmerica);
        sabretooth.addArchEnemy(wolverine);

        joker.addEnemy(captainAmerica);
        joker.addEnemy(spiderMan);
        joker.addEnemy(wolverine);
        joker.addArchEnemy(batman);

        lexLuthor.addEnemy(wolverine);
        lexLuthor.addEnemy(blackPanther);
        lexLuthor.addEnemy(thor);
        lexLuthor.addArchEnemy(superman);
      
        // Fan init.
        Fan jaap = new Fan("Jaap");
        Fan jelle = new Fan("Jelle");
        Fan arthur = new Fan("Arthur");
        Fan ensar = new Fan("Ensar");
        Fan sarwish = new Fan("Sarwish");
        Fan paco = new Fan("Paco");
        Fan ava = new Fan("Ava");
        Fan ethan = new Fan("Ethan");
        Fan isabella = new Fan("Isabella");
        Fan mason = new Fan("Mason");
        Fan mia = new Fan("Mia");
        Fan logan = new Fan("Logan");
        Fan charlotte = new Fan("Charlotte");
        Fan lucas = new Fan("Lucas");
        Fan amelia = new Fan("Amelia");
        Fan jackson = new Fan("Jackson");
        Fan harper = new Fan("Harper");
        Fan aiden = new Fan("Aiden");
        Fan evelyn = new Fan("Evelyn");
        Fan james = new Fan("James");
        Fan abigail = new Fan("Abigail");
        Fan alexander = new Fan("Alexander");
        Fan emily = new Fan("Emily");
        Fan benjamin = new Fan("Benjamin");
        Fan lily = new Fan("Lily");
        Fan jack = new Fan("Jack");
        Fan madison = new Fan("Madison");
        Fan luke = new Fan("Luke");
        Fan sophie = new Fan("Sophie");
        Fan william = new Fan("William");
        Fan grace = new Fan("Grace");
        Fan michael = new Fan("Michael");
        Fan chloe = new Fan("Chloe");
        Fan owen = new Fan("Owen");
        Fan ella = new Fan("Ella");

        //Adding favorites.
        jaap.addFavorite(wolverine);
        jaap.addFavorite(spiderMan);

        jelle.addFavorite(greenGoblin);
        jelle.addFavorite(sabretooth);

        arthur.addFavorite(antMan);
        arthur.addFavorite(batman);
        arthur.addFavorite(thanos);

        ensar.addFavorite(thor);
        ensar.addFavorite(spiderMan);

        sarwish.addFavorite(captainAmerica);
        sarwish.addFavorite(killmonger);

        paco.addFavorite(sabretooth);
        paco.addFavorite(redSkull);
        paco.addFavorite(ultron);
        paco.addFavorite(hulk);
        paco.addFavorite(doctorStrange);

        ava.addFavorite(doctorOctopus);
        ava.addFavorite(blackWidow);
        ava.addFavorite(solarFlare);

        ethan.addFavorite(doctorStrange);
        ethan.addFavorite(hela);
        ethan.addFavorite(vulture);

        isabella.addFavorite(blackWidow);
        isabella.addFavorite(loki);
        isabella.addFavorite(ultron);
        isabella.addFavorite(solarFlare);

        mason.addFavorite(blackPanther);
        mason.addFavorite(loki);
        mason.addFavorite(doctorOctopus);

        mia.addFavorite(captainAmerica);
        mia.addFavorite(hulk);
        mia.addFavorite(thanos);
        mia.addFavorite(doctorStrange);
        mia.addFavorite(redSkull);

        logan.addFavorite(spiderMan);
        logan.addFavorite(wolverine);
        logan.addFavorite(greenGoblin);

        charlotte.addFavorite(doctorStrange);
        charlotte.addFavorite(blackPanther);
        charlotte.addFavorite(vulture);

        lucas.addFavorite(thor);
        lucas.addFavorite(blackPanther);
        lucas.addFavorite(loki);
        lucas.addFavorite(ultron);

        amelia.addFavorite(blackWidow);
        amelia.addFavorite(solarFlare);
        amelia.addFavorite(hela);

        jackson.addFavorite(hulk);
        jackson.addFavorite(antMan);
        jackson.addFavorite(killmonger);
        jackson.addFavorite(lexLuthor);

        harper.addFavorite(spiderMan);
        harper.addFavorite(blackWidow);
        harper.addFavorite(doctorOctopus);

        aiden.addFavorite(captainAmerica);
        aiden.addFavorite(thor);
        aiden.addFavorite(greenGoblin);
        aiden.addFavorite(doctorStrange);

        evelyn.addFavorite(wolverine);
        evelyn.addFavorite(blackWidow);
        evelyn.addFavorite(redSkull);

        james.addFavorite(blackPanther);
        james.addFavorite(hulk);
        james.addFavorite(loki);
        james.addFavorite(hela);

        abigail.addFavorite(captainAmerica);
        abigail.addFavorite(doctorStrange);
        abigail.addFavorite(ultron);
        abigail.addFavorite(doctorOctopus);

        alexander.addFavorite(spiderMan);
        alexander.addFavorite(blackPanther);
        alexander.addFavorite(vulture);
        alexander.addFavorite(lexLuthor);

        emily.addFavorite(blackWidow);
        emily.addFavorite(hulk);
        emily.addFavorite(joker);
        emily.addFavorite(killmonger);

        benjamin.addFavorite(thor);
        benjamin.addFavorite(hulk);
        benjamin.addFavorite(doctorOctopus);
        benjamin.addFavorite(redSkull);

        lily.addFavorite(blackWidow);
        lily.addFavorite(solarFlare);
        lily.addFavorite(loki);

        jack.addFavorite(spiderMan);
        jack.addFavorite(blackPanther);
        jack.addFavorite(doctorStrange);

        madison.addFavorite(captainAmerica);
        madison.addFavorite(hela);
        madison.addFavorite(vulture);
        madison.addFavorite(ultron);

        luke.addFavorite(thor);
        luke.addFavorite(antMan);
        luke.addFavorite(greenGoblin);
        luke.addFavorite(batman);

        sophie.addFavorite(blackWidow);
        sophie.addFavorite(doctorStrange);
        sophie.addFavorite(hela);
        sophie.addFavorite(loki);

        william.addFavorite(spiderMan);
        william.addFavorite(blackPanther);
        william.addFavorite(thanos);

        grace.addFavorite(blackWidow);
        grace.addFavorite(hulk);
        grace.addFavorite(killmonger);
        grace.addFavorite(redSkull);

        michael.addFavorite(spiderMan);
        michael.addFavorite(antMan);
        michael.addFavorite(doctorStrange);
        michael.addFavorite(vulture);

        chloe.addFavorite(wolverine);
        chloe.addFavorite(solarFlare);
        chloe.addFavorite(hela);
        chloe.addFavorite(ultron);

        owen.addFavorite(thor);
        owen.addFavorite(doctorOctopus);
        owen.addFavorite(sabretooth);

        ella.addFavorite(blackWidow);
        ella.addFavorite(spiderMan);
        ella.addFavorite(blackPanther);
        ella.addFavorite(superman);
    }
}
 
