import java.util.Date;
import java.util.Scanner;

public class Venue {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Integer capacity = -1; //Поле может быть null, Значение поля должно быть больше 0
    private VenueType type; //Поле может быть null

    public Venue() {
        id = new Date().getTime() + 1;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Введите название аудитории: ");
            name = scanner.nextLine();
        } while(name == null);

        do {
            System.out.println("Введите вместимость аудитории: (введите 0, если она не важна)");
            if (scanner.hasNextInt()) {
                capacity = scanner.nextInt();
                if (capacity == 0) {
                    capacity = null;
                }
            } else System.out.println("Введите корректное значение");
        } while (capacity == -1);

        do {
            String s;
            System.out.println("Введите тип билета: (оставьте поле пустым, если хотите)");
            System.out.println("Список возможным типов: VIP, USUAL, BUDGETARY, CHEAP");
            s = scanner.nextLine();
            if (s != null) {
                try {
                    type = VenueType.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {System.out.println("Введите корректное название типа");}
            } else break;
        } while (type == null);
    }

    public String getVenueName() {
        return name;
    }
}
