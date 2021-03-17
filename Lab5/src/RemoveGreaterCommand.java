import java.util.LinkedList;

/**
 * Command class that remove tickets from the collection greater than given one
 */
public class RemoveGreaterCommand implements CommandWithAdditionalArgument{
    /**ticket name*/
    private String ticketName;
    /**collection of tickets*/
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
       Ticket ticket = null;
        for (Ticket t : c) {
            if (t.getName().equals(ticketName)) ticket = t;
        }
        if (ticket == null) System.out.println("Элемента с таким именем найти не удалось.");
        else {
            Ticket finalTicket = ticket;
            if (c.removeIf(i -> i.getPrice() > finalTicket.getPrice())) System.out.println("Сколько-то элементов было удалено");
            else System.out.println("К сожалению, ничего удалить не удалось");
        }
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
