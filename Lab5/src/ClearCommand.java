import java.util.LinkedList;

/**
 * Command class that clears the collection
 */
public class ClearCommand implements CommandWithoutAdditionalArgument{
    /**collection of tickets*/
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
        String s = c.removeAll(c) ? "Коллекция была очищена." : "Коллекция итак была пуста.";
        System.out.println(s);
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "clear : очистить коллекцию";
    }
}
