import java.util.LinkedList;

public class ClearCommand implements Command{

    private final LinkedList<Ticket> c;

    public ClearCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    @Override
    public void execute() {
        boolean b = c.removeAll(c);
        if (b) System.out.println("Коллекция была очищена.");
        else System.out.println("Коллекция итак была пуста.");
    }

    @Override
    public String toString() {
        return "clear : очистить коллекцию";
    }
}
