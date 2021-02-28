import java.util.LinkedList;

public class SumOfPriceCommand implements Command{

    private LinkedList<Ticket> c;

    public SumOfPriceCommand(LinkedList<Ticket> c) {this.c = c;}

    @Override
    public void execute() {
        double k = 0;
        for (Ticket t : c) {
            k += t.getPrice();
        }
        System.out.println("Общая стоимость билетов: " + k );
    }

    @Override
    public String toString() {
        return "sum_of_price : вывести сумму значений поля price для всех элементов коллекции";
    }
}
