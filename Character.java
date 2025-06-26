import java.util.*;
import java.lang.Math;

/**
 * Represents a game character with attributes like name, class, health points, energy points, and abilities.
 * This class manages character state and combat actions.
 */
public class Character {
    private final String name;
    private final Class cl;
    private int hp;
    private int ep;
    private ArrayList<Ability> charAbs;

    /**
     * Constructs a new Character with specified attributes.
     *
     * @param name    the character's name
     * @param cl      the character's class
     * @param hp      the character's initial health points
     * @param ep      the character's initial energy points
     * @param charAbs the list of abilities the character can use
     */
    public Character(String name, Class cl, int hp, int ep, ArrayList<Ability> charAbs)
    {
        this.name = name;
        this.cl = cl;
        this.hp = hp;
        this.ep = ep;
        this.charAbs = new ArrayList<>(charAbs);
    }

    /**
     * Gets the character's name.
     *
     * @return the character's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the character's class.
     *
     * @return the character's class
     */
    public Class getCl() {
        return cl;
    }

    /**
     * Gets the character's current health points.
     *
     * @return current HP value
     */
    public int getHp() {
        return hp;
    }

    /**
     * Gets the character's current energy points.
     *
     * @return current EP value
     */
    public int getEp() {
        return ep;
    }

    /**
     * Gets the list of character's abilities.
     *
     * @return list of abilities
     */
    public ArrayList<Ability> getCharAbs() {
        return charAbs;
    }

    /**
     * Sets the character's health points.
     *
     * @param hp the new HP value
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Sets the character's energy points.
     *
     * @param ep the new EP value
     */
    public void setEp(int ep) {
        this.ep = ep;
    }

    /**
     * Sets the character's abilities by adding three new abilities.
     *
     * @param ab1 the first ability to add
     * @param ab2 the second ability to add
     * @param ab3 the third ability to add
     */
    public void setCharAbs(Ability ab1, Ability ab2, Ability ab3) {
        charAbs.add(ab1);
        charAbs.add(ab2);
        charAbs.add(ab3);
    }

    /**
     * Returns a compact string representation of the character for list views.
     *
     * @return formatted string with character summary
     */
    public String toCompactString() 
    {
        String abilityNames = "";

        for (Ability ab : charAbs) {
            if (!abilityNames.isEmpty()) 
                abilityNames += ", ";

            abilityNames += ab.getName();
        }

        return String.format("%-15s | %-10s | HP: %-4d | EP: %-4d | Abilities: %s",
                           name, cl.getName(), hp, ep, abilityNames);
    }

    /**
     * Returns a detailed string representation of the character.
     *
     * @return formatted string with character details
     */
    public String toDetailedString() 
    {
        String result = String.format("%-15s | %-10s | HP: %-4d | EP: %-4d\n", name, cl.getName(), hp, ep);
        result += "Chosen Abilities:\n";

        for (Ability ability : charAbs)
            result += String.format("%-15s (Cost: %2d)\n    - %s\n", ability.getName(), ability.getEc(), ability.getDesc());

        return result;
    }

    /**
     * Simulates a combat round between this character and an opponent.
     *
     * @param playerMove  the ability used by this character
     * @param enemyMove   the ability used by the opponent
     * @param enemyHP     the opponent's current HP
     * @param yourHP      this character's current HP
     * @param yourEP      this character's current EP
     * @return an array containing updated enemyHP, yourHP, and yourEP values
     */
    public int[] fight(Ability playerMove, Ability enemyMove, int enemyHP, int yourHP, int yourEP)
    {
        boolean evades;

        //Mage Abilities Effect------------------------------------------------------
        if("Arcane Bolt".equals(playerMove.getName()))
        {
            enemyHP -= playerMove.getEv();
            yourEP -= playerMove.getEc();
        }

        if("Arcane Blast".equals(playerMove.getName()))
        {
            enemyHP -= playerMove.getEv();
            yourEP -= playerMove.getEc();
        }

        if("Mana Channel".equals(playerMove.getName()))
        {
            yourEP += playerMove.getEv();
            yourEP = Math.min(yourEP, 50);
        }  
        
        if("Lesser Heal".equals(playerMove.getName()))
        {
            yourHP += playerMove.getEv();
            yourHP = Math.min(yourHP, 100);
            yourEP -= playerMove.getEc();
        }
        
        if("Arcane Shield".equals(playerMove.getName()))
        {
            yourEP -= playerMove.getEc();
            yourHP += enemyMove.getEv();
            yourHP = Math.min(yourHP, 100);
        }

            
        //Rogue Abilities Effect------------------------------------------------------
        if("Shiv".equals(playerMove.getName()))
        {
            enemyHP -= playerMove.getEv();
            yourEP -= playerMove.getEc();
        }

        if("Backstab".equals(playerMove.getName()))
        {
            enemyHP -= playerMove.getEv();
            yourEP -= playerMove.getEc();
        }

        if("Focus".equals(playerMove.getName()))
        {
            yourEP += playerMove.getEv();
            yourEP = Math.min(yourEP, 50);
        }   
    
        if("Smoke Bomb".equals(playerMove.getName()))
        {
            yourEP -= playerMove.getEc();
            evades = new Random().nextBoolean();

            if(evades) {
                yourHP += enemyMove.getEv();
                yourHP = Math.min(yourHP, 100);
            } 
        }
        
        if("Sneak Attack".equals(playerMove.getName()))
        {
            yourHP += enemyMove.getEv(); // have no damage
            yourHP = Math.min(yourHP, 100);
            enemyHP -= playerMove.getEv(); // deal 45 damage
            yourEP -= playerMove.getEc(); // 25 cost
        }


        //Warrior Abilities Effect------------------------------------------------------
        if("Cleave".equals(playerMove.getName()))
        {
            enemyHP -= playerMove.getEv();
            yourEP -= playerMove.getEc();
        }

        if("Shield Bash".equals(playerMove.getName()))
        {
            enemyHP -= playerMove.getEv();
            yourEP -= playerMove.getEc();
        }

        if("Ironclad Defense".equals(playerMove.getName()))
        {
            yourHP += enemyMove.getEv();
            yourHP = Math.min(yourHP, 100);
            yourEP -= playerMove.getEc();
        }
            
    
        if("Bloodlust".equals(playerMove.getName()))
        {
            yourHP += playerMove.getEv();
            yourHP = Math.min(yourHP, 100);
            yourEP -= playerMove.getEc();
        }
        
        if("Rallying Cry".equals(playerMove.getName()))
        {
            yourEP += playerMove.getEv();
            yourEP = Math.min(yourEP, 50);
        }

        //Defend & Recharge Effects------------------------------------------------------
        if("Defend".equals(playerMove.getName()))
        {
            yourEP -= playerMove.getEc();
            yourHP += enemyMove.getEv()/2;
            yourHP = Math.min(yourHP, 100);
        }

        if("Recharge".equals(playerMove.getName()))
        {
            yourEP += playerMove.getEv();
            yourEP = Math.min(yourEP, 50);
        }

        return new int[]{enemyHP, yourHP, yourEP};
    }

    /**
     * Returns a string representation of the character.
     *
     * @return formatted string with character summary
     */
    @Override
    public String toString() {
        return String.format("%-15s | %-10s | HP: %-4d | EP: %-4d", 
                           name, cl.getName(), hp, ep);
    }


}