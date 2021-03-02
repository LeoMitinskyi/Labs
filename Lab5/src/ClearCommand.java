import java.util.LinkedList;

/**
 * Command class that clears the collection
 */
public class ClearCommand implements Command{

    private final LinkedList<Ticket> c;

    /**
     * Constructor with parameter
     * @param c - collection of tickets
     */
    public ClearCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    /**
     * Clear the collection
     */
    @Override
    public void execute() {
        boolean b = c.removeAll(c);
        if (b) System.out.println("Коллекция была очищена.");
        else System.out.println("Коллекция итак была пуста.");
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "clear : очистить коллекцию";
    }
}
