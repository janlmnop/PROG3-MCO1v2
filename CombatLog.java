import java.util.*;

/**
 * Records and manages combat logs for game rounds.
 */
public class CombatLog {
    private ArrayList<String> logEntries;
    
    /**
     * Constructs a new empty CombatLog.
     */
    public CombatLog() {
        this.logEntries = new ArrayList<>();
    }

    /**
     * Logs the moves made in a round along with character states.
     *
     * @param round  the round number
     * @param p1Move player 1's move
     * @param p2Move player 2's move
     * @param p1Char player 1's character
     * @param p2Char player 2's character
     */
    public void logRoundMoves(int round, String p1Move, String p2Move, Character p1Char, Character p2Char)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("[Round ").append(round).append("]\n");
        sb.append("P1 ").append(p1Char.getName()).append(" move : ").append(p1Move).append("\n");
        sb.append("P2 ").append(p2Char.getName()).append(" move : ").append(p2Move).append("\n");
        sb.append("________________").append("\n");
        sb.append("P1 ").append(p1Char).append("\n");
        sb.append("P2 ").append(p2Char).append("\n\n");

        logEntries.add(sb.toString());
    }

    /**
     * Prints all logged combat entries to the console.
     */
    public void printLog()
    {
        for (String entry : logEntries) {
            System.out.println(entry);
        }
    }

    public ArrayList<String> getLog ()
    {
        return logEntries;
    }
}