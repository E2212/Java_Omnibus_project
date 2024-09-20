
public class SoloFight extends Fight {
    private final Hero hero;
    private final Villain villain;
    private Super winner;

    public SoloFight(Hero hero, Villain villain) {
        super();
        this.hero = hero;
        this.villain = villain;
        Main.soloFights.add(this);
    }

    @Override
    public void startFight(String userInput) {
        if (userInput.equals("h")) {
            winner = hero;
        } else if (userInput.equals("v")) {
            winner = villain;
        } else {
            determineWinner();
        }

        System.out.println(hero.getOneLiner());
        System.out.println(villain.getOneLiner());

        distributeFans();
    }

    @Override
    protected void determineWinner() {
        int heroPowerTotal = calculateTotalPower(hero);
        int villainPowerTotal = calculateTotalPower(villain);

        if(hero.getArchEnemy() == villain) {
            heroPowerTotal += 10;
        }

        if(villain.getArchEnemy() == hero) {
            villainPowerTotal += 10;
        }

        if (heroPowerTotal == calculateWinningPower(heroPowerTotal, villainPowerTotal)) {
            winner = hero;
        } else {
            winner = villain;
        }
    }

    @Override
    protected void distributeFans() {
        Super loser;
        if (winner == hero) {
            loser = villain;
        } else {
            loser = hero;
        }

        // verliezer verliest 30% van de fans en geeft deze aan de winnaar. Door het gebruik van floor zal
        // er altijd een geheel aantal fans verloren raken en nooit meer dan dat de verliezer heeft.
        int lostFans = (int) Math.floor(loser.getFans().size() * 0.3);
        for (int i = 0; i < lostFans; i++) {
            Fan fan = loser.getFans().get(i);
            fan.removeFavorite(loser);

            // Een fan kan maar 1 keer worden toegevoegd, wanneer de fan al fan is heeft de winnaar pech!
            if (!fan.getFavorites().contains(winner)) {
                fan.addFavorite(winner);
            }
        }
    }

    public Hero getHero() {
        return hero;
    }

    public Villain getVillain() {
        return villain;
    }

    public Super getWinner() {
        return winner;
    }
}
