import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Ticket {

    private static int generalId = 10000;

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate = new Date(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double price; //Поле не может быть null, Значение поля должно быть больше 0
    private TicketType type; //Поле может быть null
    private Venue venue; //Поле может быть null

    public Ticket() {
        id = ++generalId;
        updateElement();
    }

    public Ticket(Double price) {
        id = ++generalId;
        this.price = price;
        updateElement();

    }

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

    public String getDateOfCreation() {
        return creationDate.toString();
    }

    public Double getPrice() {
        return price;
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public TicketType getType() {return type;}

    public String getCoordinates() {return coordinates.getX().toString() + " " + coordinates.getY().toString();}

    public Venue getVenue() {return venue;}

    public void updateElement() {
        String s = "";
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

        System.out.println("Куда билет? (если не хотите вводить, оставьте поле пустым, для продолжения напишите любой символ)");
        s = scanner.nextLine();
        if (!s.equals("")) venue = new Venue(s);
        scanner.close();
    }

    @Override
    public String toString() {
        return "Id: " + id + " " + name + ", стоимость билета - " + price + type;
    }
    public static int getGeneralId() {return  generalId;}
    public static void setGeneralId(int generalId) {Ticket.generalId = generalId;}
}

