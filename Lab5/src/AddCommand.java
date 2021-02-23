import java.util.LinkedList;

public class AddCommand implements Command {

    private LinkedList<Ticket> c;

    public AddCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    @Override
    public void execute() {
        c.add(new Ticket());
    }

    @Override
    public String toString() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
