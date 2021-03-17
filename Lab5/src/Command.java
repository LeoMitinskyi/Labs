/**
 * Basic interface for all commands
 */
public interface Command {
    /**
     * Method which executes the command
     */
    void execute();

    /**
     * @param countOfArguments - count of arguments
     * @return true or false depending on the count of arguments
     */
    boolean correctCountOfArguments(int countOfArguments);
}

