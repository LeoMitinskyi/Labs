/**
 * Basic interface for all commands with additional argument
 */
public interface CommandWithAdditionalArgument extends Command{
    /**
     * Add additional argument to command field
     * @param obj - additional argument
     */
    void addArgument(String obj);
}
