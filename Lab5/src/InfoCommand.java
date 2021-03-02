import java.util.LinkedList;

/**
 * Command class which outputs info about collection
 */
public class InfoCommand implements Command {
    private final LinkedList<Ticket> c;

    /**
     * Constructor with argument
     * @param c - collection
     */
    public InfoCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    /**
     * Output info about collection (type, count of elements, creation time)
     */
    public void execute() {
        System.out.println("Тип коллекции: " + c.getClass());
        System.out.println("Количество элементов: " + c.size());
        if (c.size() != 0 ) System.out.println("Время создания: " + c.getFirst().getDateOfCreation());
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "info : вывести в стандартный поток вывода информацию о коллекции.";
    }
}
