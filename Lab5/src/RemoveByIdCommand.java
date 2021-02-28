import java.util.LinkedList;

public class RemoveByIdCommand implements CommandWithAdditionalArgument{

    private LinkedList<Ticket> c;
    private int ID;

    public RemoveByIdCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    @Override
    public void execute() {
        if (!c.removeIf(i -> i.getId() == ID)) throw new IdNotFoundException();
        System.out.println("Билет с id: " + ID + " был успешно удалён.");
    }

    @Override
    public void addArgument(String obj) {
        ID = Integer.parseInt(obj);
    }

    @Override
    public String toString() {
        return "remove_by_id <id> : удалить элемент из коллекции по его id";
    }
}
