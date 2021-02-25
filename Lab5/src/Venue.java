import java.text.NumberFormat;
import java.util.Date;
import java.util.Scanner;

public class Venue {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Integer capacity = -1; //Поле может быть null, Значение поля должно быть больше 0
    private VenueType type; //Поле может быть null

    public Venue(String name) {
        id = new Date().getTime() + 1;
        Scanner scanner = new Scanner(System.in);
        this.name = name;
        String s;
        do {
            System.out.println("Введите вместимость аудитории: (оставтье пустым, если она неизвестна)");
                try {
                    s = scanner.nextLine();
                    if (s.equals("")) capacity = null;
                    else if (Integer.parseInt(s) > 0) capacity = Integer.parseInt(s);
                } catch (NumberFormatException e) {System.out.println("Введите корректное значение");}
        } while (capacity == -1);

        do {
            System.out.println("Введите тип аудитории: (оставьте поле пустым, если хотите)");
            System.out.println("Список возможным типов:   BAR, LOFT, THEATRE, MALL, STADIUM");
            s = scanner.nextLine();
            if (!s.equals("")) {
                try {
                    type = VenueType.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {System.out.println("Введите корректное название типа");}
            }
        } while (type == null && !s.equals(""));
    }

    public Venue(String name, Integer capacity,VenueType type) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    public String getVenueName() {
        return name;
    }
}
