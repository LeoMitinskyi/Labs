import java.util.Date;
import java.util.Scanner;

/**
 * Class Ticket with fields generalId, id, name, coordinates, creationDate, price, type and venue
*/
public class Ticket {

    private static int generalId = 10000;
    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final Date creationDate = new Date(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double price; //Поле не может быть null, Значение поля должно быть больше 0
    private TicketType type; //Поле может быть null
    private Venue venue; //Поле может быть null

    /**
     * Constructor without parameters
     * @see Ticket#Ticket(Double)
     * @see Ticket#Ticket(int, String, Coordinates, Double, TicketType, String, Integer, VenueType)
     */
    public Ticket() {
        id = ++generalId;
        updateElement();
    }

    /**
     * Constructor with parameters
     * @see Ticket#Ticket()
     * @see Ticket#Ticket(int, String, Coordinates, Double, TicketType, String, Integer, VenueType)
     * @param price - price of the Ticket (> 0)
     */
    public Ticket(Double price) {
        id = ++generalId;
        this.price = price;
        updateElement();

    }

    /**
     * Constructor with parameters
     * @see Ticket#Ticket()
     * @see Ticket#Ticket(Double)
     * @param id - ticket id (generate automatically)
     * @param name - ticket name (!= null)
     * @param coordinates - ticket coordinates (x and y, more than -48)
     * @param price - ticket price (> 0)
     * @param type - ticket type (VIP, USUAL, BUDGETARY, CHEAP)
     * @param venueName - venue name (!= null)
     * @param venueCapacity - venue capacity (> 0)
     * @param venueType - vanue type (BAR, LOFT, THEATRE, MALL, STADIUM)
     */

    public Ticket(int id, String name, Coordinates coordinates, Double price, TicketType type, String venueName, Integer venueCapacity, VenueType venueType) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.type = type;
        if (venueName != null) {
            venue = new Venue(venueName, venueCapacity, venueType);
        }
    }

    /**
     * Getter {@link Ticket#creationDate}
     * @return creation date
     */

    public String getDateOfCreation() {
        return creationDate.toString();
    }

    /**
     * Getter {@link Ticket#price}
     * @return ticket price
     */

    public Double getPrice() {
        return price;
    }

    /**
     * Getter {@link Ticket#id}
     * @return ticket id
     */
    public int getId() {return id;}

    /**
     * Getter {@link Ticket#name}
     * @return ticket name
     */
    public String getName() {return name;}

    /**
     * Getter {@link Ticket#type}
     * @return ticket type
     */
    public TicketType getType() {return type;}

    /**
     * Getter {@link Ticket#coordinates}
     * @return ticket coordinates ("x y")
     */
    public String getCoordinates() {return coordinates.getX().toString() + " " + coordinates.getY().toString();}

    /**
     * Getter {@link Ticket#venue}
     * @return ticket venue
     */
    public Venue getVenue() {return venue;}

    /**
     * Method that updates the ticket fields using Scanner to enter values (used in constructors)
     */
    public void updateElement() {
        String s;
        //id = (int) (creationDate.getTime() + 724600000);
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Введите название билета: ");
            s = scanner.nextLine();
            name = s;
        } while(s.equals(""));

        do {
            System.out.println("Введите координаты: (в формате x y)");
            String[] j = scanner.nextLine().split(" ");
            try {
                if (j.length > 2 || j.length == 0) System.out.println("Введите корректное число аргументов");
                else if (Double.parseDouble(j[0]) > -48 && Double.parseDouble(j[1]) > -48) coordinates = new Coordinates(Double.parseDouble(j[0]), Double.parseDouble(j[1]));
                else System.out.println("Введите корректные значения x и y (они должны быть больше -48)");
            } catch (NumberFormatException e) {System.out.println("Введите корректные значения x и y (они должны быть больше -48)");}

        } while(coordinates == null);

        if (price == null) {
            do {
                System.out.println("Введите стоимость билета: (она должна быть больше 0)");
                String[] j = scanner.nextLine().split(" ");
                try {
                    if (j.length == 1) price = Double.parseDouble(j[0]);
                    else {
                        System.out.println("Введите корректное число аргументов");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Введите корректную стоимость");
                }
            } while (price <= 0);
        }

        do {
            System.out.println("Введите тип билета: (оставьте поле пустым, если хотите)");
            System.out.println("Список возможным типов: VIP, USUAL, BUDGETARY, CHEAP");
            s = scanner.nextLine();
            if (!s.equals("")) {
                try {
                    type = TicketType.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {System.out.println("Введите корректное название типа");}
            }
        } while (type == null && !s.equals(""));

        System.out.println("Куда билет? (если не хотите вводить, оставьте поле пустым, для продолжения напишите название места)");
        s = scanner.nextLine();
        if (!s.equals("")) venue = new Venue(s);
        //scanner.close();
    }

    /**
     *
     * @return String with ticket id, name, price and type
     */
    @Override
    public String toString() {
        return "Id: " + id + ", " + name + ", стоимость билета - " + price ;
    }

    /**
     * Getter {@link Ticket#generalId}
     * @return ticket general id (= max ticket id in Collection)
     */
    public static int getGeneralId() {return  generalId;}

    /**
     * Setter {@link Ticket#generalId}
     * @param generalId - general id
     */
    public static void setGeneralId(int generalId) {Ticket.generalId = generalId;}
}

