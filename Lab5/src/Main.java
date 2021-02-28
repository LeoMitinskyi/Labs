import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;



public class Main {
    public static void main(String[] args) {
        String command;
        Scanner scanner = new Scanner(System.in);
        CommandDecoder commandDecoder = new CommandDecoder();
        System.out.println("Введите полный путь к файлу, из которого нужно считать коллекцию (оставьте пустым, если хотите оставить файл по умолчанию)");
        command = scanner.nextLine();
        FileWorker fileReader = new FileWorker(commandDecoder.getCollection());
        try {
            fileReader.read(command);
        } catch (NumberFormatException | IncorrectInputDataException e) {
            System.out.println("Файл содержит некрректно введённые данные (убедитесь в и их правильности)" + e);
        }

        do {
            System.out.println("Введите команду: (help - узнать список команд, exit - выход из программы (без сохранения))");
            command = scanner.nextLine();
            try {
               commandDecoder.decode(command);
            } catch(NullPointerException | IllegalArgumentException  e) {System.out.println("Такой команды не существует");}
            catch (IllegalCountOfArgumentsException e) {System.out.println("Неправльное количество аргументов");}
            catch(IdNotFoundException e) {System.out.println("Билета с таким id не было найдено");};
            /*try {
                Command com = (Command) Class.forName(command+"Command").newInstance();
                com.execute();
            } catch(Exception e) {System.out.println("Такой команды не существует!"+e.toString());};*/

        } while(! command.equals("exit"));

    }
}
