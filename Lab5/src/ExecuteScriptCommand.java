import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScriptCommand implements CommandWithAdditionalArgument{

    private String filePath;

    @Override
    public void execute() {
        try {
            File file = new File(filePath);
            StringBuilder wrongCommands = new StringBuilder();
            Scanner scanner = new Scanner(file);
            CommandDecoder cd = new CommandDecoder();

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            try {
                if (command.equals("execute_script " + filePath)) wrongCommands.append(command).append(", ");
                else {
                    System.out.println(command);
                    if (command.equals("exit")) System.exit(0);
                    cd.decode(command);
                }
            } catch(NullPointerException | IllegalArgumentException | IllegalCountOfArgumentsException | IdNotFoundException e) {
                wrongCommands.append(command).append(", ");
            }
        }
        System.out.println("Не удалось исполнить следующие команды: " + wrongCommands);
        } catch (FileNotFoundException e) {
            System.out.println("Указанный файл не был найден.");
        }
    }

    @Override
    public void addArgument(String obj) {
        filePath = obj;
    }

    @Override
    public String toString() {
        return "execute_script <file_path> : считать и исполнить скрипт из указанного файла.";
    }
}
