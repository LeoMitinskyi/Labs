package commands;

import clientik.CommandDecoder;
import clientik.collection.Ticket;
import exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Command class what executes script
 */
public class ExecuteScriptCommand extends CommandWithAdditionalArgument{
    /**file path to execute the script*/
    private String filePath;
    /**hash set that contains all execute script commands in file*/
    private static final HashSet<String> executeScriptCommands = new HashSet<>();


    public ExecuteScriptCommand(LinkedList<Ticket> c) {
        this.c = c;
    }
    /**
     * Execute script
     */
    @Override
    public String execute() {
        StringBuilder result = new StringBuilder();
        boolean isItContainsExit = false;
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            CommandDecoder cd = new CommandDecoder(c);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            try {
                if (executeScriptCommands.contains(command))
                {
                    result.append("Была встречена бесконечная рекурсия \n");
                    break;
                }
                if (command.contains("execute_script")) executeScriptCommands.add(command);
                result.append(command);
                if (command.equals("exit")) return result.toString();
                cd.decode(command);
            } catch(NullPointerException | IllegalArgumentException | IllegalCountOfArgumentsException | IdNotFoundException e) {
                result.append("Не удалось выполнить команду \n");
            }
        }
        executeScriptCommands.removeAll(executeScriptCommands);
        } catch (FileNotFoundException e) {
            return "Указанный файл не был найден.";
        }
        result.append("Выполнение скрипта было завершенно");
        return result.toString();
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
