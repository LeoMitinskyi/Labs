import java.util.LinkedList;

public class RemoveGreaterCommand implements CommandWithAdditionalArgument{

    private String ticketName;
    private LinkedList<Ticket> c;

    public RemoveGreaterCommand(LinkedList<Ticket> c) {this.c = c;}

    @Override
    public void execute() {
       boolean b = false;
       boolean b1 = false;
        for (Ticket t : c) {
            if (b) b1 = c.remove(t);
            if (t.getName().equals(ticketName)) b = true;
        }
        if (!b) System.out.println("Элемента с таким именем найти не удалось.");
        if (b1) System.out.println("Сколько-то элементов было удалено");
        else System.out.println("К сожалению, ничего удалить не удалось");
    }

    @Override
    public void addArgument(String obj) {
        ticketName = obj;
    }

    @Override
    public String toString() {
        return "remove_greater <ticket name> : удалить из коллекции все элементы, превышающие заданный";
    }
}
