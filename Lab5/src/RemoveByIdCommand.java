import java.util.LinkedList;

/**
 * Command class that outputs the ticket by its id and delete it
 */
public class RemoveByIdCommand implements CommandWithAdditionalArgument{
    /**collection of tickets*/
    private final LinkedList<Ticket> c;
    /**ticket id*/
    private int ID;

    /**
     * Constructor with parameter
     * @param c - collection of tickets
     */
    public RemoveByIdCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    /**
     * Output the ticket by its id and delete it
     */
    @Override
    public void execute() {
        if (!c.removeIf(i -> i.getId() == ID)) throw new IdNotFoundException();
        System.out.println("Билет с id: " + ID + " был успешно удалён.");
    }

    /**
     * Getting ticket id
     * @param obj - ticket id
     */
    @Override
    public void addArgument(String obj) {
        ID = Integer.parseInt(obj);
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "remove_by_id <id> : удалить элемент из коллекции по его id";
    }
}
