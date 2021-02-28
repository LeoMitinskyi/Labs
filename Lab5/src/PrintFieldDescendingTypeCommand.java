import java.util.LinkedList;

public class PrintFieldDescendingTypeCommand implements Command{

    private LinkedList<Ticket> c;

    public PrintFieldDescendingTypeCommand(LinkedList<Ticket> c) {this.c = c;}

    @Override
    public void execute() {
        String s = "";
        for (Ticket t : c) {
            if (t.getType() != null) s = t.getType() + "\n" + s;
            else s = "Тип этого билета не был указан \n" + s;
        }
        System.out.println(s);
    }

    @Override
    public String toString() {
        return "print_field_descending_type : вывести значения поля type всех элементов в порядке убывания";
    }
}
