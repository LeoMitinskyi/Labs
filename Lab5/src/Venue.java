import java.util.Scanner;

/**
 * Class Venue with fields id, name, capacity and type
 */
public class Venue {
    private static int generalID = 10000;
    /**Venue id*/
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**Venue name*/
    private final String name; //Поле не может быть null, Строка не может быть пустой
    /**Venue capacity*/
    private Integer capacity; //Поле может быть null, Значение поля должно быть больше 0
    /**Venue type*/
    private VenueType type; //Поле может быть null

    /**
     * Constructor with parameter
     * @param name - venue name {@link Venue#name}
     * @see Venue#Venue(String, Integer, VenueType)
     */
    public Venue(String name) {
        id = generalID++;
        Scanner scanner = new Scanner(System.in);
        this.name = name;
        String s = "";
        do {
            System.out.println("Введите вместимость аудитории: (оставтье пустым, если она неизвестна)");
                try {
                    s = scanner.nextLine();
                    if (!s.equals("") && Integer.parseInt(s) > 0) capacity = Integer.parseInt(s);
                } catch (NumberFormatException e) {System.out.println("Введите корректное значение");}
        } while (capacity == null && !s.equals(""));

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
        //scanner.close();
    }

    /**
     * Constructor with parameters
     * @param name - venue name {@link Venue#name}
     * @param capacity - venue capacity {@link Venue#capacity}
     * @param type - venue type {@link Venue#type}
     * @see Venue#Venue(String)
     */
    public Venue(String name, Integer capacity,VenueType type) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    /**
     * Getter {@link Venue#name}
     * @return venue name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter {@link Venue#capacity}
     * @return venue capacity
     */
    public Integer getCapacity() {return capacity;}

    /**
     * Getter {@link Venue#type}
     * @return venue type
     */
    public VenueType getType() {return type;}

}
