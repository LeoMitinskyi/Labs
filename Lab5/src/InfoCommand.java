import java.util.Collection;
import java.util.LinkedList;

public class InfoCommand implements Command {
    public LinkedList<Ticket> c;

    public InfoCommand(LinkedList<Ticket> c) {
        this.c = c;
    }
    public void execute() {
        System.out.println("Тип коллекции: " + c.getClass());
        System.out.println("Количество элементов: " + c.size());
        if (c.size() != 0 ) System.out.println("Время создания: " + c.getFirst().getDateOfCreation());
    }
    public void getCollection() {
        System.out.println(c);
    }

}
