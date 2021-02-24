import java.util.LinkedList;

public class ShowCommand implements Command{

    private LinkedList<Ticket> c;

    public ShowCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    @Override
    public void execute() {
        if (c.size() != 0) {
            for (Ticket i : c) {
                System.out.println(i);
            }
        } else System.out.println("Нечего показывать");
    }

    @Override
    public String toString() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}