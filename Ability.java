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
     * Constructs a new Ability only with the specified name.
     *
     * @param name      the name of the ability
     */
    public Ability(String name) {
        this.name = name;
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
     * Sets the ability's name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the ability's description.
     *
     * @param desc the new description
     */
    public void setDescription(String desc) {
        this.desc = desc;
    }

    /**
     * Sets the ability's EP cost
     *
     * @param epCost the new EP cost
     */
    public void setCost(int epCost) {
        this.epCost = epCost;
    }

    /**
     * Sets the ability's effect value
     *
     * @param effectVal the new effect value
     */
    public void setEffectVal(int effectVal) {
        this.effectVal = effectVal;
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