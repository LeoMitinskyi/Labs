import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;



public class Main {
    public static void main(String[] args) {
        String command;
        Scanner scanner = new Scanner(System.in);
        CommandDecoder commandDecoder = new CommandDecoder();

        commandDecoder.getCollection().add(new Ticket());
        do {
            command = scanner.nextLine();
            try {
                commandDecoder.decode(command);
            } catch(NullPointerException e) {System.out.println("Такой команды не существует!");}
            /*try {
                Command com = (Command) Class.forName(command+"Command").newInstance();
                com.execute();
            } catch(Exception e) {System.out.println("Такой команды не существует!"+e.toString());};*/

        } while(! command.equals(""));

    }
}
