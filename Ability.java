/**
 * Represents an ability with a name, description, energy point cost, and effect value.
 * This class encapsulates the properties and behaviors of character abilities in the game.
 */
public class Ability {
    /** The name of the ability */
    private String name;

    /** The description of the ability */
    private String desc;

    /** The energy point cost required to use the ability */
    private int epCost;

    /** The effect value of the ability */
    private int effectVal;

    /**
     * Constructs a new Ability with the specified name, description, EP cost, and effect value.
     *
     * @param name      the name of the ability
     * @param desc      the description of the ability
     * @param epCost    the energy points required to use the ability
     * @param effectVal the effect value of the ability
     */
    public Ability(String name, String desc, int epCost, int effectVal) {
        this.name = name;
        this.desc = desc;
        this.epCost = epCost;
        this.effectVal = effectVal;
    }

    /**
     * Returns the name of this ability.
     *
     * @return the ability's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of this ability.
     *
     * @return the ability's description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns the energy point cost of this ability.
     *
     * @return the EP cost
     */
    public int getEc() {
        return epCost;
    }

    /**
     * Returns the effect value of this ability.
     *
     * @return the effect value
     */
    public int getEv() {
        return effectVal;
    }

    /**
     * Returns a string representation of this ability, including its name, EP cost, and effect value.
     *
     * @return a formatted string representing the ability
     */
    @Override
    public String toString() {
        return String.format("%s (Cost: %d EP, Effect: %d)", name, epCost, effectVal);
    }
}