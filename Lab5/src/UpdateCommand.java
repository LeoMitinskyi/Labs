import java.util.LinkedList;

public class UpdateCommand implements CommandWithAdditionalArgument{
    private final LinkedList<Ticket> c;
    private int ID;

    public UpdateCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    @Override
    public void execute() {
        int k = 0;
        for (Ticket i : c) {
            if (i.getId() == ID) {
                i.updateElement();
            } else k++;
        }
        if (k == c.size()) throw new IdNotFoundException();
    }

    @Override
    public void addArgument(String obj) {
        ID = Integer.parseInt(obj);
    }

    @Override
    public String toString() {
        return "update id : обновить значение элемента коллекции, id которого равен заданному";
    }
}
