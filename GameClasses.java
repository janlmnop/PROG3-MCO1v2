import java.util.*;

/**
 * Provides predefined character classes with their abilities for the game.
 */
public class GameClasses {

    /**
     * Creates and returns the Mage class with its abilities.
     *
     * @return the Mage class
     */
    public static Class Mage() {
        ArrayList<Ability> abs1 = new ArrayList<>();

        Ability m1 = new Ability("Arcane Bolt", "Launch a basic magical projectile that deals 20 arcane damage to the target.", 5, 20);
        Ability m2 = new Ability("Arcane Blast", "Unleash a burst of fiery energy, dealing 65 arcane damage to the target", 30, 65);
        Ability m3 = new Ability("Mana Channel", "Draw upon ambient magical energy to restore your own. Restores 15 EP.", 0, 15);
        Ability m4 = new Ability("Lesser Heal", "Weave a minor healing spell to mend your wounds. Restores 40 HP. ", 15, 40);
        Ability m5 = new Ability("Arcane Shield", "Conjure a protective barrier of mystical energy around yourself. You do not take any damage for the round", 12, 0);

        abs1.add(m1);
        abs1.add(m2);
        abs1.add(m3);
        abs1.add(m4);
        abs1.add(m5);
        
        return new Class("Mage", "command arcane energies, specializing in powerful spells and mystical manipulation", abs1);
    }
    
    /**
     * Creates and returns the Rogue class with its abilities.
     *
     * @return the Rogue class
     */
    public static Class Rogue() {
        ArrayList<Ability> abs2 = new ArrayList<>();

        Ability r1 = new Ability("Shiv", "A quick, precise stab that deals 20 physical damage.", 5, 20);
        Ability r2 = new Ability("Backstab", "Strike a vital point and deal 35 points of physical damage.", 15, 35);
        Ability r3 = new Ability("Focus", "Take a moment to concentrate, restoring your mental energy. Restores 10 EP.", 0, 10);
        Ability r4 = new Ability("Smoke Bomb", "Throw a smoke bomb, making you harder to hit. You have a 50% chance of evading any incoming attacks in the current round.", 15, 0);
        Ability r5 = new Ability("Sneak Attack", "You rely on your agility to evade your opponent, taking no damage from any of their attacks, while you deal 45 physical damage to them.", 25, 45);
        
        abs2.add(r1);
        abs2.add(r2);
        abs2.add(r3);
        abs2.add(r4);
        abs2.add(r5);

        return new Class("Rogue", "agile and tricky, relying on precision and debilitating opponents", abs2);
    }

    /**
     * Creates and returns the Warrior class with its abilities.
     *
     * @return the Warrior class
     */
    public static Class Warrior() {
        ArrayList<Ability> abs3 = new ArrayList<>();
        
        Ability w1 = new Ability("Cleave", "A sweeping strike that deals 20 physical damage.", 5, 20);
        Ability w2 = new Ability("Shield Bash", "Slam your shield into the opponent, dealing 35 physical damage.", 15, 35);
        Ability w3 = new Ability("Ironclad Defense", "Brace yourself, effectively taking no damage for the current round.", 15, 0);
        Ability w4 = new Ability("Bloodlust", "Tap into your inner fury, restoring a small amount of health. Restores 30 HP.", 12, 30);
        Ability w5 = new Ability("Rallying Cry", "Let out a powerful shout, inspiring yourself and recovering 12 EP.", 0, 12);

        abs3.add(w1);
        abs3.add(w2);
        abs3.add(w3);
        abs3.add(w4);
        abs3.add(w5);

        return new Class("Warrior", "tough, focusing on direct combat and robust defense", abs3);
    }

}