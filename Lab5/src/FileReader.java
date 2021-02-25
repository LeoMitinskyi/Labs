import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class FileReader {

    private LinkedList<Ticket> c;

    private String name;
    private Coordinates coordinates;
    private Double price;
    private TicketType type;

    private String venueName;
    private Integer venueCapacity;
    private VenueType venueType;

    public FileReader(LinkedList<Ticket> c) {
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
                for (int k = 0; k < data.length; k++) {
                    if (queue[k].equals("name")) if (!data[k].equals("")) name = data[k];
                    else throw new IncorrectInputDataException();
                    if (queue[k].equals("coordinates")) {
                        String[] s = data[k].split(" ");
                        if (s.length == 2 && Double.parseDouble(s[0]) > -48 && Double.parseDouble(s[1]) > -48) {
                            coordinates = new Coordinates(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
                        } else throw new IncorrectInputDataException();
                    }
                    if (queue[k].equals("price"))
                        if (Double.parseDouble(data[k]) > 0) price = Double.parseDouble(data[k]);
                        else throw new IncorrectInputDataException();
                    if (queue[k].equals("venueName")) if (!data[k].equals("")) venueName = data[k];
                    else throw new IncorrectInputDataException();
                    if (queue[k].equals("venueCapacity"))
                        if (Integer.parseInt(data[k]) > 0) venueCapacity = Integer.parseInt(data[k]);
                        else throw new IncorrectInputDataException();
                    try {
                        if (queue[k].equals("type") && !data[k].equals(""))
                            type = TicketType.valueOf(data[k].toUpperCase());
                        if (queue[k].equals("venueType") && !data[k].equals(""))
                            venueType = VenueType.valueOf(data[k].toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Один из типов был введён неправльно, поэтому = null");
                    }
                }
                    //inputStream.nextLine();
                if (coordinates == null || price == null) { throw new IncorrectInputDataException();
                } else c.add(new Ticket(name, coordinates, price, type, venueName, venueCapacity, venueType));
                        name = null;
                        coordinates = null;
                        price = null;
                        type = null;
                        venueName = null;
                        venueCapacity = null;
                        venueType = null;
                //System.out.print(data + "|");
            }
            inputStream.close();


        } catch (FileNotFoundException e){
            System.out.println("Не удалось найти укзанный файл");
        }
    }
}
