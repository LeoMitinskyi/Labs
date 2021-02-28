import java.util.HashMap;

public class HelpCommand implements Command {

    private HashMap<String, Command> h;

    public HelpCommand(HashMap<String, Command> h) {this.h = h;}

    @Override
    public void execute() {
        for (Command com : h.values()) {
            if (com.getClass() != HelpCommand.class) System.out.println(com);
        }
    }
}
