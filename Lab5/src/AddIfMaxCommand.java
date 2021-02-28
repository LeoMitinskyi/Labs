import java.util.LinkedList;

public class AddIfMaxCommand implements CommandWithAdditionalArgument{

    private Double price;
    private final LinkedList<Ticket> c;

    public AddIfMaxCommand(LinkedList<Ticket> c) {this.c = c;}

    @Override
    public void execute() {
        if (price > c.getLast().getPrice()) c.add(new Ticket(price));
        else System.out.println("Новый элемент не может быть добавлен из-за низкой цены.");
        CommandDecoder cd = new CommandDecoder();
        cd.sort(c);
    }

    @Override
    public void addArgument(String obj) {
        price = Double.parseDouble(obj);
    }

    @Override
    public String toString() {
        return "add_if_max <price> : добавить новый элемент в коллекцию, если его значение price превышает значение наибольшего элемента этой коллекции";
    }
}
