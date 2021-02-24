import java.util.LinkedList;

public class UpdateCommand implements Command{
    private LinkedList<Ticket> c;

    public UpdateCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
