import java.util.ArrayList;

/**
 * Represents a character class in the game, containing class-specific attributes and abilities.
 */
public class Class {
    private String name;
    private String desc;
    private ArrayList<Ability> abs;

    /**
     * Constructs a new Class with specified attributes.
     *
     * @param name the class name
     * @param desc the class description
     * @param abs  the list of abilities available to this class
     */
    public Class(String name, String desc, ArrayList<Ability> abs) {
        this.name = name;
        this.desc = desc;
        this.abs = new ArrayList<>(abs);
    }

    /**
     * Gets the class name.
     *
     * @return the class name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the class description.
     *
     * @return the class description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Gets the list of class abilities.
     *
     * @return list of abilities
     */
    public ArrayList<Ability> getAb() {
        return abs;
    }

    /**
     * Returns a string representation of the class.
     *
     * @return the class name
     */
    @Override
    public String toString() {
        return name;
    }
}