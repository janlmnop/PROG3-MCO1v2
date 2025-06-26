import java.util.*;

/**
 * Represents a player in the game, containing their username and character information.
 */
public class Player {
    private String username;
    private ArrayList<Character> list = new ArrayList<>();
    private Character chosen;

    /**
     * Gets the player's username.
     *
     * @return the username
     */
    public String getUserName() {
        return username;
    }

    /**
     * Gets the player's list of characters.
     *
     * @return list of characters
     */
    public ArrayList<Character> getList() {
        return list;
    }

    /**
     * Gets the player's currently chosen character.
     *
     * @return the chosen character
     */
    public Character getChosen() {
        return chosen;
    }

    /**
     * Sets the player's username.
     *
     * @param username the new username
     */
    public void setName(String username) {
        this.username = username;
    }

    /**
     * Sets the player's list of characters.
     *
     * @param list the new list of characters
     */
    public void setList(ArrayList<Character> list) {
        this.list = list;
    }

    /**
     * Sets the player's chosen character.
     *
     * @param chosen the character to set as chosen
     */
    public void setChosen(Character chosen) {
        this.chosen = chosen;
    }
}