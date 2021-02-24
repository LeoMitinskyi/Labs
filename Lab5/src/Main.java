import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;



public class Main {
    public static void main(String[] args) {
        String command;
        Scanner scanner = new Scanner(System.in);
        CommandDecoder commandDecoder = new CommandDecoder();
        //commandDecoder.getCollection().add(new Ticket());
        do {
            System.out.println("Введите команду: (help - узнать список команд, exit - выход из программы (без сохранения))");
            command = scanner.nextLine();
            try {
                commandDecoder.decode(command);
            } catch(NullPointerException | IllegalArgumentException  e) {System.out.println("Такой команды не существует или аргумент имеет неправильный тип");}
            catch(IdNotFoundException e) {System.out.println("Билета с таким id не было найдено");};
            /*try {
                Command com = (Command) Class.forName(command+"Command").newInstance();
                com.execute();
            } catch(Exception e) {System.out.println("Такой команды не существует!"+e.toString());};*/

        } while(! command.equals("exit"));

    }
}
