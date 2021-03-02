import java.util.LinkedList;

/**
 * Command class that remove tickets from the collection greater than given one
 */
public class RemoveGreaterCommand implements CommandWithAdditionalArgument{

    private String ticketName;
    private final LinkedList<Ticket> c;

    /**
     * Constructor with parameter
     * @param c - collection of tickets
     */
    public RemoveGreaterCommand(LinkedList<Ticket> c) {this.c = c;}

    /**
     * Remove tickets from the collection greater than given one
     */
    @Override
    public void execute() {
       boolean b = false;
       boolean b1 = false;
        for (Ticket t : c) {
            if (b) b1 = c.remove(t);
            if (t.getName().equals(ticketName)) b = true;
        }
        if (!b) System.out.println("Элемента с таким именем найти не удалось.");
        else if (b1) System.out.println("Сколько-то элементов было удалено");
        else System.out.println("К сожалению, ничего удалить не удалось");
    }

    /**
     * Getting ticket name
     * @param obj - ticket name
     */
    @Override
    public void addArgument(String obj) {
        ticketName = obj;
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "remove_greater <ticket name> : удалить из коллекции все элементы, превышающие заданный";
    }
}
