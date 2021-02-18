import java.util.Collection;

public class InfoCommand implements Command {
    public Collection<Ticket> c;

    public InfoCommand(Collection<Ticket> c) {
        this.c = c;
    }
    public void execute() {
        System.out.println(c.getClass());
        System.out.println(c.size());
    }
    public void getCollection() {
        System.out.println(c);
    }

}
