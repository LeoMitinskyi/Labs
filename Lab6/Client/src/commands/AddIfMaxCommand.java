package commands;

import mainPart.CommandDecoder;
import collection.Ticket;

import java.util.LinkedList;

/**
 * Command class that adds element to the collection if it price bigger than other
 */

public class AddIfMaxCommand extends CommandWithAdditionalArgument{
    /**price of a new ticket*/
    private Double price;
    /**collection of tickets*/

    public Ticket ticket;

    /**
     * Constructor with parameter
     * @param c - collection of tickets
     */
    public AddIfMaxCommand(LinkedList<Ticket> c) {this.c = c;}

    /**
     * add element to the collection, if it more than other
     */
    @Override
    public String execute() {
        if (price > c.getLast().getPrice()) c.add(ticket);
        else return "Новый элемент не может быть добавлен из-за низкой цены.";
        CommandDecoder.sort(c);
        return "Новый элемент был успешно добавлен в коллекцию";
    }

    public boolean canNewTicketBeAdded() {
        return price > c.getLast().getPrice();
    }

    /**
     * Getting price of ticket to compare with others {@link AddIfMaxCommand#price}
     * @param obj - additional argument
     */
    @Override
    public void addArgument(String obj) {
        price = Double.parseDouble(obj);
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "add_if_max <price> : добавить новый элемент в коллекцию, если его значение price превышает значение наибольшего элемента этой коллекции";
    }
}
