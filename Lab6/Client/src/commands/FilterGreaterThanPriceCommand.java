package commands;
import clientik.collection.Ticket;

import java.util.LinkedList;

/**
 * Command class that outputs elements which price is greater than given one
 */
public class FilterGreaterThanPriceCommand extends CommandWithAdditionalArgument{
    /**price of the ticket*/
    private Double price;

    /**
     * Constructor with argument
     * @param c - collection of tickets
     */
    public FilterGreaterThanPriceCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    /**
     * If price is bigger than price of the given ticket, then print info about ticket
     * @see Ticket#toString()
     */
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Ticket t : c) {
            if (t.getPrice() > price) stringBuilder.append(t + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Getting price of the ticket {@link FilterGreaterThanPriceCommand#price}
     * @param obj - price of the ticket
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
        return "filter_greater_than_price <price> : вывести элементы, значение поля price которых больше заданного";
    }
}
