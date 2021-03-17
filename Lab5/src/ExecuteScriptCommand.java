import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Command class what executes script
 */
public class ExecuteScriptCommand implements CommandWithAdditionalArgument{
    /**file path to execute the script*/
    private String filePath;
    /**collection of tickets*/
    private final LinkedList<Ticket> c;
    /**hash set that contains all execute script commands in file*/
    private static final HashSet<String> executeScriptCommands = new HashSet<>();


    public ExecuteScriptCommand(LinkedList<Ticket> c) {this.c = c;}
    /**
     * Execute script
     */
    @Override
    public void execute() {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            CommandDecoder cd = new CommandDecoder(c);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            try {
                if (executeScriptCommands.contains(command))
                {
                    System.out.println("Была встречена бесконечная рекурсия");
                    break;
                }
                if (command.contains("execute_script")) executeScriptCommands.add(command);
                System.out.println(command);
                if (command.equals("exit")) System.exit(0);
                cd.decode(command);
            } catch(NullPointerException | IllegalArgumentException | IllegalCountOfArgumentsException | IdNotFoundException e) {
                System.out.println("Не удалось выполнить команду");
            }
        }
        executeScriptCommands.removeAll(executeScriptCommands);
        } catch (FileNotFoundException e) {
            System.out.println("Указанный файл не был найден.");
        }
    }

    /**
     * Getting file path to execute script {@link ExecuteScriptCommand#filePath}
     * @param obj - file path
     */
    @Override
    public void addArgument(String obj) {
        filePath = obj;
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "execute_script <file_path> : считать и исполнить скрипт из указанного файла.";
    }
}
