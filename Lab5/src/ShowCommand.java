import java.util.LinkedList;

/**
 * Command class which outputs elements in the collection
 */
public class ShowCommand implements Command{

    private final LinkedList<Ticket> c;

    /**
     * Output elements in the collection
     * @param c - collection of tickets
     */
    public ShowCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    @Override
    public void execute() {
        if (c.size() != 0) {
            for (Ticket i : c) {
                System.out.println(i);
            }
        } else System.out.println("Нечего показывать");
    }

    @Override
    public String toString() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
