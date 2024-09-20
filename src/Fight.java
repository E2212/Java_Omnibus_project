import java.util.Random;

public abstract class Fight {
    public Fight() {

    }

    public abstract void startFight(String userInput);

    protected abstract void determineWinner();

    protected abstract void distributeFans();

    protected int calculateTotalPower(Super superCharacter) {
        int totalPower = superCharacter.getPowerLevel();

        for (Gadget gadget : superCharacter.getGadgets()) {
            if (gadget instanceof Weapon) {
                totalPower += ((Weapon) gadget).getPowerLevel();
            }
        }

        totalPower += superCharacter.getFans().size();

        return totalPower;
    }

    protected int calculateWinningPower(int power1, int power2) {
        int totalPower = power1 + power2;

        // Bereken een deel van het geheel, dus het percentage wat power1(0.xx) van het totalPower(1) is.
        double probability = (double) power1 / totalPower;

        // probability kan nooit lager zijn dat 10% en tegelijkertijd niet hoger dan 90% (deze twee moeten samen 100% zijn).
        // Dit zorgt ervoor dat er altijd een kans is om te winnen.
        probability = Math.min(Math.max(probability, 0.1), 0.9);

        // We maken gebruik van Random om willekeurig een double te verkrijgen(0.xx).
        // Wanneer deze lager is dan power 1 dan wint deze en anders wint power 2.
        Random random = new Random();
        if (random.nextDouble() < probability) {
            return power1;
        } else {
            return power2;
        }
    }
}
