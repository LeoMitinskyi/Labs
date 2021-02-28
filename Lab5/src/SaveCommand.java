import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveCommand implements Command{

    private LinkedList<Ticket> c;

    public SaveCommand(LinkedList<Ticket> c) {this.c = c;}

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

    @Override
    public String toString() {
        return "save : сохранить коллекцию в файл";
    }
}
