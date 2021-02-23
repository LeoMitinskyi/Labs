import java.util.*;

public class CommandDecoder {

    private HashMap<String, Command> commands = new HashMap<>();
    private LinkedList<Ticket> c = new LinkedList<>();
    private Command cd;

    {
        commands.put("info", new InfoCommand(c));
        commands.put("show", new ShowCommand(c));
        commands.put("add", new AddCommand(c));
    }

    public void decode(String com) {

            cd = commands.get(com.toLowerCase());
            cd.execute();

    }

    public void sort(LinkedList<Ticket> c) {
        Comparator<Ticket> comparator = Comparator.comparing(Ticket::getPrice);
        c.sort(comparator);
    }

    public void changeCollection(LinkedList<Ticket> c) {
        this.c = c;
    }
    public Collection<Ticket> getCollection() {
        return c;
    }

}
