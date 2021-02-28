import java.util.*;

public class CommandDecoder {

    private HashMap<String, Command> commands = new HashMap<>();
    private LinkedList<Ticket> c = new LinkedList<>();
    private Command cd;

    {
        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(c));
        commands.put("show", new ShowCommand(c));
        commands.put("add", new AddCommand(c));
        commands.put("update", new UpdateCommand(c));
        commands.put("remove_by_id", new RemoveByIdCommand(c));
        commands.put("clear", new ClearCommand(c));

    }

    public void decode(String com) {

            String[] s;
            s = com.split(" ");
            //System.out.println(s.toString());
            if (!s[0].equals("exit")) {
                cd = commands.get(s[0].toLowerCase());

                if (s.length == 1 && Arrays.asList(cd.getClass().getInterfaces()).contains(Command.class)) cd.execute();
                else if (s.length == 1) throw new IllegalCountOfArgumentsException();

                try {
                    if (s.length == 2 && Arrays.asList(cd.getClass().getInterfaces()).contains(CommandWithAdditionalArgument.class)) {
                        ((CommandWithAdditionalArgument) cd).addArgument(s[1]);
                        cd.execute();
                    } else if (s.length > 2) throw new IllegalCountOfArgumentsException();

                } catch (NumberFormatException e) {
                    System.out.println("Аргумент имеет неправльный тип (для id - int, для price - double)");
                }
            }
    }

    public void sort(LinkedList<Ticket> c) {
        Comparator<Ticket> comparator = Comparator.comparing(Ticket::getId);
        c.sort(comparator);
    }

    public void changeCollection(LinkedList<Ticket> c) {
        this.c = c;
    }
    public LinkedList<Ticket> getCollection() {
        return c;
    }

}
