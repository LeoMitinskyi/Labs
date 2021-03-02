import java.util.LinkedList;

/**
 * Command class that adds the element to the collection
 */
public class AddCommand implements Command {

    private final LinkedList<Ticket> c;

    /**
     * Constructors with parameter
     * @param c - collection of tickets
     */
    public AddCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    /**
     * add element to the collection
     */
    @Override
    public void execute() {
        c.add(new Ticket());
        CommandDecoder cd = new CommandDecoder();
        cd.sort(c);
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "add : добавить новый элемент в коллекцию";
    }
}
