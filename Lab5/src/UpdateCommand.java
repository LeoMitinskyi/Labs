import java.util.LinkedList;

/**
 * Command class that updates the ticket with the given id
 */
public class UpdateCommand implements CommandWithAdditionalArgument{
    private final LinkedList<Ticket> c;
    private int ID;

    /**
     * Constructor with additional argument
     * @param c - collection of tickets
     */
    public UpdateCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    /**
     * Update the ticket with the given id
     */
    @Override
    public void execute() {
        int k = 0;
        for (Ticket i : c) {
            if (i.getId() == ID) {
                i.updateElement();
            } else k++;
        }
        if (k == c.size()) throw new IdNotFoundException();
    }

    /**
     * Getiing ticket id {@link UpdateCommand#ID}
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
        return "update <id> : обновить значение элемента коллекции, id которого равен заданному";
    }
}
