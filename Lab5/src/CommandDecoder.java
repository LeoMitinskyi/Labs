import java.util.*;

/**
 * Commands decoder class that which processes incoming commands and controls collection
 */
public class CommandDecoder {

    private final HashMap<String, Command> commands = new HashMap<>();
    private final LinkedList<Ticket> c = new LinkedList<>();

    {
        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(c));
        commands.put("show", new ShowCommand(c));
        commands.put("add", new AddCommand(c));
        commands.put("update", new UpdateCommand(c));
        commands.put("remove_by_id", new RemoveByIdCommand(c));
        commands.put("clear", new ClearCommand(c));
        commands.put("save", new SaveCommand(c));
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("remove_head", new RemoveHeadCommand(c));
        commands.put("add_if_max", new AddIfMaxCommand(c));
        commands.put("remove_greater", new RemoveGreaterCommand(c));
        commands.put("sum_of_price", new SumOfPriceCommand(c));
        commands.put("filter_greater_than_price", new FilterGreaterThanPriceCommand(c));
        commands.put("print_field_descending_type", new PrintFieldDescendingTypeCommand(c));

    }

    /**
     * Command decoder
     * @param com - incoming command
     */
    public void decode(String com) {

            String[] s;
            s = com.split(" ");
            //System.out.println(s.toString());
            if (!s[0].equals("exit")) {
                Command cd = commands.get(s[0].toLowerCase());
                try {
                if (s.length == 1 && Arrays.asList(cd.getClass().getInterfaces()).contains(Command.class)) cd.execute();
                else if (s.length == 1) throw new IllegalCountOfArgumentsException();


                    if (s.length == 2 && Arrays.asList(cd.getClass().getInterfaces()).contains(CommandWithAdditionalArgument.class)) {
                        ((CommandWithAdditionalArgument) cd).addArgument(s[1]);
                        cd.execute();
                    } else if (s.length > 2) throw new IllegalCountOfArgumentsException();

                } catch (NumberFormatException e) {
                    System.out.println("Аргумент имеет неправльный тип (для id - int, для price - double)");
                }
            }
    }

    /**
     * Collection of tickets sorter by price and id
     * @param c - collection of tickets
     */
    public void sort(LinkedList<Ticket> c) {
        Comparator<Ticket> comparator = Comparator.comparing(Ticket::getPrice).thenComparing(Ticket::getId);
        c.sort(comparator);
    }

    /**
     * Getter {@link CommandDecoder#c}
     * @return collection of tickets
     */
    public LinkedList<Ticket> getCollection() {
        return c;
    }

}
