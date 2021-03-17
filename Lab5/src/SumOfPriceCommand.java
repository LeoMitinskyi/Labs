import java.util.LinkedList;

/**
 * Command class that outputs sum of the ticket price
 */
public class SumOfPriceCommand implements CommandWithoutAdditionalArgument{
    /**collection of tickets*/
    private final LinkedList<Ticket> c;

    /**
     * Constructor with parameter
     * @param c - collection of tickets
     */
    public SumOfPriceCommand(LinkedList<Ticket> c) {this.c = c;}

    /**
     * Output sum of the ticket price
     */
    @Override
    public void execute() {
        double k = 0;
        for (Ticket t : c) {
            k += t.getPrice();
        }
        System.out.println("Общая стоимость билетов: " + k );
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "sum_of_price : вывести сумму значений поля price для всех элементов коллекции";
    }
}
