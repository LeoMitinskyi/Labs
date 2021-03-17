/**
 * Basic interface for all commands without additional argument
 */
public interface CommandWithoutAdditionalArgument extends Command{

    /**
     * @param countOfArguments - count of arguments
     * @return true if count of arguments = 0, else false
     */
    @Override
    default boolean correctCountOfArguments(int countOfArguments) {
        return countOfArguments == 0;
    }
}
