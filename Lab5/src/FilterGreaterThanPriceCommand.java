
import java.util.LinkedList;

public class FilterGreaterThanPriceCommand implements CommandWithAdditionalArgument{

    private LinkedList<Ticket> c;
    private Double price;

    public FilterGreaterThanPriceCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    @Override
    public void execute() {
        for (Ticket t : c) {
            if (t.getPrice() > price) System.out.println(t);
        }
    }

    @Override
    public void addArgument(String obj) {
        price = Double.parseDouble(obj);
    }

    @Override
    public String toString() {
        return "filter_greater_than_price <price> : вывести элементы, значение поля price которых больше заданного";
    }
}
