/**
 * Basic interface for all commands with additional argument
 */
public interface CommandWithAdditionalArgument extends CommandWithoutAdditionalArgument{

    /**
     * Add additional argument to command field
     * @param obj - additional argument
     */
    void addArgument(String obj);

    /**
     * @param countOfArguments - count of arguments
     * @return true if count of arguments = 1, else false
     */
    @Override
    default boolean correctCountOfArguments(int countOfArguments) {
        return countOfArguments == 1;
    }
}
