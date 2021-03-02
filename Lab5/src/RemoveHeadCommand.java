import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Command class that removes first element in the collection
 */
public class RemoveHeadCommand implements Command{

    private final LinkedList<Ticket> c;

    /**
     * Constructor with parameter
     * @param c - collection
     */
    public RemoveHeadCommand(LinkedList<Ticket> c) {this.c = c;}

    /**
     * Remove first element in the collection
     */
    @Override
    public void execute() {
        try {
            System.out.println(c.getFirst());
            c.removeFirst();
        } catch (NoSuchElementException e) {System.out.println("Коллекция пуста");}
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "remove_head : вывести первый элемент коллекции и удалить его";
    }
}
