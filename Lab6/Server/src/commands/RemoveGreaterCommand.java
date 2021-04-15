package commands;

import serverok.collection.Ticket;

import java.util.LinkedList;

/**
 * Command class that remove tickets from the collection greater than given one
 */
public class RemoveGreaterCommand extends CommandWithAdditionalArgument{
    /**ticket name*/
    private String ticketName;

    /**
     * Constructor with parameter
     * @param c - collection of tickets
     */
    public RemoveGreaterCommand(LinkedList<Ticket> c) {this.c = c;}

    /**
     * Remove tickets from the collection greater than given one
     */
    @Override
    public String execute() {
       Ticket ticket = null;
        for (Ticket t : c) {
            if (t.getName().equals(ticketName)) ticket = t;
        }
        if (ticket == null) return "Элемента с таким именем найти не удалось.";
            Ticket finalTicket = ticket;
            if (c.removeIf(i -> i.getPrice() > finalTicket.getPrice())) return "Сколько-то элементов было удалено";
        return "К сожалению, ничего удалить не удалось";
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
