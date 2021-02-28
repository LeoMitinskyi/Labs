import java.util.LinkedList;

public class AddCommand implements Command {

    private final LinkedList<Ticket> c;

    public AddCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    @Override
    public void execute() {
        c.add(new Ticket());
        CommandDecoder cd = new CommandDecoder();
        cd.sort(c);
    }

    @Override
    public String toString() {
        return "add : добавить новый элемент в коллекцию";
    }
}
