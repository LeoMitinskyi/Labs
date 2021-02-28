import java.util.LinkedList;

public class InfoCommand implements Command {
    private final LinkedList<Ticket> c;

    public InfoCommand(LinkedList<Ticket> c) {
        this.c = c;
    }
    public void execute() {
        System.out.println("Тип коллекции: " + c.getClass());
        System.out.println("Количество элементов: " + c.size());
        if (c.size() != 0 ) System.out.println("Время создания: " + c.getFirst().getDateOfCreation());
    }

    @Override
    public String toString() {
        return "info : вывести в стандартный поток вывода информацию о коллекции.";
    }
}
