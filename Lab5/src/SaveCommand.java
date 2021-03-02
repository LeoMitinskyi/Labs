import java.util.LinkedList;
import java.util.Scanner;

/**
 * Command class that saves a collection of tickets
 */
public class SaveCommand implements Command{

    private final LinkedList<Ticket> c;

    /**
     * Constructor with parameter
     * @param c - collection of tickets
     */
    public SaveCommand(LinkedList<Ticket> c) {this.c = c;}

    /**
     * Save collection of tickets
     */
    @Override
    public void execute(){
        String fileNameDefined = "D:/Java/Labs/Lab5/Col.csv";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь до файла сохранения: (оставьте поле пустым, чтобы оставить файл по умолчанию)");
        String filePath = scanner.nextLine();
        if (!filePath.equals("")) fileNameDefined = filePath;
        FileWorker fileWorker = new FileWorker(c);
        fileWorker.write(fileNameDefined);
        System.out.println("Коллекция была успешно сохранена в файл.");
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "save : сохранить коллекцию в файл";
    }
}
