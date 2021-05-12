package commands;
import collection.Ticket;

import java.util.LinkedList;

/**
 * Command class that outputs sum of the ticket price
 */
public class SumOfPriceCommand extends CommandWithoutAdditionalArgument{

    /**
     * Constructor with parameter
     * @param c - collection of tickets
     */
    public SumOfPriceCommand(LinkedList<Ticket> c) {this.c = c;}

    /**
     * Output sum of the ticket price
     */
    @Override
    public String execute() {
        double k = c.stream().mapToDouble(Ticket::getPrice).sum();
        return "Общая стоимость билетов: " + k;
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "sum_of_price : вывести сумму значений поля price для всех элементов коллекции";
    }
}
