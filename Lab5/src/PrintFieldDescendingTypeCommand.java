import java.util.LinkedList;

public class PrintFieldDescendingTypeCommand implements Command{

    private final LinkedList<Ticket> c;

    public PrintFieldDescendingTypeCommand(LinkedList<Ticket> c) {this.c = c;}

    @Override
    public void execute() {
        StringBuilder s = new StringBuilder();
        for (Ticket t : c) {
            if (t.getType() != null) s.insert(0, t.getType() + "\n");
            else s.insert(0, "Тип этого билета не был указан \n");
        }
        System.out.println(s);
    }

    @Override
    public String toString() {
        return "print_field_descending_type : вывести значения поля type всех элементов в порядке убывания";
    }
}
