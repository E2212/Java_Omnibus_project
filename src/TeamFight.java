import java.util.ArrayList;
import java.util.List;

public class TeamFight extends Fight {
    private final HeroTeam heroTeam;
    private final VillainTeam villainTeam;
    private Team winner;

    public TeamFight(HeroTeam heroTeam, VillainTeam villainTeam) {
        super();
        this.heroTeam = heroTeam;
        this.villainTeam = villainTeam;
        Main.teamFights.add(this);
    }

    @Override
    public void startFight(String userInput) {
        if (userInput.equals("h")) {
            winner = heroTeam;
        } else if (userInput.equals("v")) {
            winner = villainTeam;
        } else {
            determineWinner();
        }

        for (Hero hero : heroTeam.getHeroes()) {
            System.out.println(hero.getOneLiner());
        }

        for (Villain villain : villainTeam.getVillains()) {
            System.out.println(villain.getOneLiner());
        }

        distributeFans();
    }

    @Override
    protected void determineWinner() {
        int heroTeamPowerTotal = calculateHeroTeamPower();
        int villainTeamPowerTotal = calculateVillainTeamPower();

        if (heroTeamPowerTotal == calculateWinningPower(heroTeamPowerTotal, villainTeamPowerTotal)) {
            winner = heroTeam;
        } else {
            winner = villainTeam;
        }
    }

    @Override
    protected void distributeFans() {
        // tijdelijke list omdat de methodes die de leden ophalen niet gedeeld zijn vanuit de parent class.
        List<Super> losers = new ArrayList<>();
        List<Super> winners = new ArrayList<>();
        if (winner == heroTeam) {
            losers.addAll(villainTeam.getVillains());
            winners.addAll(heroTeam.getHeroes());
        } else {
            losers.addAll(heroTeam.getHeroes());
            winners.addAll(villainTeam.getVillains());
        }


        // Tijdelijke list om deze daarna eerlijk te verdelen over de winnaars.
        List<Fan> newFans = new ArrayList<>();

        for (Super loser : losers) {
            // verliezer verliest 30% van de fans en geeft deze aan de winnaar. Door het gebruik van floor zal
            // er altijd een geheel aantal fans verloren raken en nooit meer dan dat de verliezer heeft.
            int lostFans = (int) Math.floor(loser.getFans().size() * 0.3);
            for (int i = 0; i < lostFans; i++) {
                Fan fan = loser.getFans().get(i);
                fan.removeFavorite(loser);

                newFans.add(fan);
            }
        }


        int fanPerWinner = (int) Math.floor((double) newFans.size() / winners.size());
        for (Super winner : winners) {
            for (int i = 0; i < fanPerWinner; i++) {
                Fan fan = newFans.get(i);

                // Een fan kan alleen worden toegevoegd als deze niet al fan was van de winnaar, anders gaat deze fan "verloren".
                if (!fan.getFavorites().contains(winner)) {
                    fan.addFavorite(winner);
                }

                // Haal de fan uit de lijst zodat deze niet aan een andere winnaar kan worden toegevoegd.
                newFans.remove(fan);
            }
        }

    }

    private int calculateHeroTeamPower() {
        int totalPower = 0;

        for (Hero hero : heroTeam.getHeroes()) {
            totalPower += calculateTotalPower(hero);

            if (villainTeam.getVillains().contains(hero.getArchEnemy())) {
                totalPower += 10;
            }
        }

        return totalPower;
    }

    private int calculateVillainTeamPower() {
        int totalPower = 0;

        for (Villain villain : villainTeam.getVillains()) {
            totalPower += calculateTotalPower(villain);

            if (heroTeam.getHeroes().contains(villain.getArchEnemy())) {
                totalPower += 10;
            }
        }

        return totalPower;
    }

    public Team getHeroTeam() {
        return heroTeam;
    }

    public Team getVillainTeam() {
        return villainTeam;
    }

    public Team getWinner() {
        return winner;
    }
}
