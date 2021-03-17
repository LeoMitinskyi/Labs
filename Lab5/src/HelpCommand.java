import java.util.HashMap;

/**
 * Command class that outputs other commands
 */
public class HelpCommand implements CommandWithoutAdditionalArgument {
    /**hash map with all commands and their classes*/
    private final HashMap<String, Command> h;

    /**
     * Constructor with parameter
     * @param h - collection of commands
     */
    public HelpCommand(HashMap<String, Command> h) {this.h = h;}

    /**
     * Output other commands
     */
    @Override
    public void execute() {
        for (Command com : h.values()) {
            if (com.getClass() != HelpCommand.class) System.out.println(com);
        }
    }
}
