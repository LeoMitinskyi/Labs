import java.util.LinkedList;
import java.util.NoSuchElementException;

public class RemoveHeadCommand implements Command{

    private final LinkedList<Ticket> c;

    public RemoveHeadCommand(LinkedList<Ticket> c) {this.c = c;}

    @Override
    public void execute() {
        try {
            System.out.println(c.getFirst());
            c.removeFirst();
        } catch (NoSuchElementException e) {System.out.println("Коллекция пуста");}
    }

    @Override
    public String toString() {
        return "remove_head : вывести первый элемент коллекции и удалить его";
    }
}
