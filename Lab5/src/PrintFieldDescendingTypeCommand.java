import java.util.LinkedList;

/**
 * Command class that outputs the types of tickets in collection in descending order
 */
public class PrintFieldDescendingTypeCommand implements CommandWithoutAdditionalArgument{
    /**collection of tickets*/
    private final LinkedList<Ticket> c;

    /**
     * Constructor with parameter
     * @param c - collection of tickets
     */
    public PrintFieldDescendingTypeCommand(LinkedList<Ticket> c) {this.c = c;}

    /**
     * Output types of tickets in collection in descending order
     */
    @Override
    public void execute() {
        StringBuilder s = new StringBuilder();
        for (Ticket t : c) {
            if (t.getType() != null) s.insert(0, t.getType() + "\n");
            else s.insert(0, "Тип этого билета не был указан \n");
        }
        System.out.println(s);
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "print_field_descending_type : вывести значения поля type всех элементов в порядке убывания";
    }
}
