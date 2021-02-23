import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class CommandDecoder {

    private HashMap<String, Command> commands = new HashMap<>();
    private LinkedList<Ticket> c = new LinkedList<>();
    private Command cd;
    private Scanner scanner;

    {
        commands.put("info", new InfoCommand(c));
    }

    public void decode(String com) {

            cd = commands.get(com.toLowerCase());
            cd.execute();

    }

    public void changeCollection(LinkedList<Ticket> c) {
        this.c = c;
    }
    public Collection<Ticket> getCollection() {
        return c;
    }
}
