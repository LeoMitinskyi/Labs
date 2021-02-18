import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class CommandDecoder {

    private HashMap<String, Command> commands = new HashMap<>();
    private Collection<Ticket> c = new LinkedList<>();
    private Command cd;

    {
        commands.put("info", new InfoCommand(c));
    }

    public void decode(String com) {

            cd = commands.get(com);
            cd.execute();

    }

    public void changeCollection(Collection<Ticket> c) {
        this.c = c;
    }
    public Collection<Ticket> getCollection() {
        return c;
    }
}
