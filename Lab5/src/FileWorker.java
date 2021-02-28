import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class FileWorker {

    private final LinkedList<Ticket> c;
    //private Scanner scanner;

    private  int id;
    private String name;
    private Coordinates coordinates;
    private Double price;
    private TicketType type;

    private String venueName;
    private Integer venueCapacity;
    private VenueType venueType;

    public FileWorker(LinkedList<Ticket> c) {
        this.c = c;
    }

    public void read(String filePath) {
        try{
            String fileNameDefined;
            if (filePath.equals("")) {
                fileNameDefined = "D:/Java/Labs/Lab5/Collection.csv";
            } else fileNameDefined = filePath;
            File file = new File(fileNameDefined);
            Scanner inputStream = new Scanner(file);
            //inputStream.useDelimiter(",");
            String[] queue = inputStream.nextLine().split(",");
            //System.out.println(Arrays.toString(queue) + queue.length);
            while(inputStream.hasNextLine()){

                String[] data = inputStream.nextLine().split(",");
                //System.out.println(data.length);
                for (int k = 0; k < data.length; k++) {
                    if (!data[k].equals("")) {
                        if (queue[k].equals("id")) {
                            int m = Integer.parseInt(data[k]);
                            if (m > 0) id = m;
                            else throw new IncorrectInputDataException();
                            for (Ticket t : c) {
                                if (t.getId() == m) throw new IncorrectInputDataException();
                            }
                            if (m > Ticket.getGeneralId()) Ticket.setGeneralId(m);
                        }
                        if (queue[k].equals("name")) if (!data[k].equals("")) name = data[k];
                        else throw new IncorrectInputDataException();
                        if (queue[k].equals("coordinates")) {
                            String[] s = data[k].split(" ");
                            if (s.length == 2 && Double.parseDouble(s[0]) > -48 && Double.parseDouble(s[1]) > -48) {
                                Double[] m = {Double.parseDouble(s[0]), Double.parseDouble(s[1])};
                                coordinates = new Coordinates(m[0], m[1]);
                            } else throw new IncorrectInputDataException();
                        }
                        if (queue[k].equals("price"))
                            if (Double.parseDouble(data[k]) > 0) price = Double.parseDouble(data[k]);
                            else throw new IncorrectInputDataException();
                        if (queue[k].equals("venueName")) if (!data[k].equals("")) venueName = data[k];
                        else throw new IncorrectInputDataException();
                        if (queue[k].equals("venueCapacity")) {
                            int m = Integer.parseInt(data[k]);
                            if (m > 0) venueCapacity = Integer.parseInt(data[k]);
                            else throw new IncorrectInputDataException();
                        }
                        try {
                            if (queue[k].equals("type") && !data[k].equals(""))
                                type = TicketType.valueOf(data[k].toUpperCase());
                            if (queue[k].equals("venueType") && !data[k].equals(""))
                                venueType = VenueType.valueOf(data[k].toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Один из типов был введён неправльно, поэтому = null");
                        }
                    }
                }
                    //inputStream.nextLine();
                if (coordinates == null || price == null) { throw new IncorrectInputDataException();
                } else {
                    c.add(new Ticket(id, name, coordinates, price, type, venueName, venueCapacity, venueType));
                    name = null;
                    coordinates = null;
                    price = null;
                    type = null;
                    venueName = null;
                    venueCapacity = null;
                    venueType = null;
                }
                //System.out.print(data + "|");
            }
            //inputStream.close();
            CommandDecoder cd = new CommandDecoder();
            cd.sort(c);
        } catch (FileNotFoundException e){
            System.out.println("Не удалось найти укзанный файл");
        }
    }

    public void write(String fileNameDefined) {
        try {
            File file = new File(fileNameDefined);
            String output;
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println("id,name,coordinates,price,type,venueName,venueCapacity,venueType");
                for (Ticket t : c) {
                    output = t.getId() + "," + t.getName() + "," + t.getCoordinates() + "," + t.getPrice() + "," + t.getType();
                    if (t.getVenue() != null) {
                        output = output + "," + t.getVenue().getName() + "," + t.getVenue().getCapacity() + "," + t.getVenue().getType();
                    }
                    printWriter.println(output.replace("null", ""));
                }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти указанный файл.");
        }
    }
}
