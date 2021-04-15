package serverok;

import commands.*;
import serverok.collection.*;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Scanner;

public class ServerPart {

    private ServerMaker serverMaker;
    private LinkedList<Ticket> collection;
    private CommandDecoder cd;

    public ServerPart (LinkedList<Ticket> collection, ServerMaker serverMaker) {
        this.collection = collection;
        this.serverMaker = serverMaker;
        cd = new CommandDecoder(collection);
        serverMaker.writeData(serverMaker.serialize(cd));
        //serverMaker.deserialize(serverMaker.readData());
        FileWorker fw = new FileWorker(collection);
        String result = fw.read(((String) serverMaker.deserialize(serverMaker.readData())));
        serverMaker.writeData(serverMaker.serialize(result));
    }

    public void readCommands() {
        do {
            byte[] a = serverMaker.readData();
            Command command = ((Command) serverMaker.deserialize(a));
            if (command != null) {
                ((CommandWithoutAdditionalArgument) command).updateCollection(collection);
                Ticket newTicket = null;
                String s = "";
                if (command.getClass() == AddCommand.class) {
                    newTicket = ((AddCommand) command).ticket;
                }
                if (command.getClass() == AddIfMaxCommand.class && ((AddIfMaxCommand) command).canNewTicketBeAdded()) {
                    newTicket = ((AddIfMaxCommand) command).ticket;
                }
                if (command.getClass() == UpdateCommand.class) {
                    s = command.execute();
                    newTicket = ((UpdateCommand) command).ticket;
                }
                if (newTicket != null) updateTicketFields(newTicket);
                if (command.getClass() != UpdateCommand.class) s = command.execute();
                serverMaker.writeData(serverMaker.serialize(s));
            }

        } while (true);
    }

    public void updateTicketFields(Ticket ticket) {
        String s;
        ticket.setCoordinates(null);
        ticket.setType(null);
        do {
            serverMaker.writeData(serverMaker.serialize("Введите название билета: "));
            s = (String) serverMaker.deserialize(serverMaker.readData());
            System.out.println(s);
            ticket.setName(s);
        } while(s.equals(""));

        do {
            serverMaker.writeData(serverMaker.serialize("Введите координаты: (в формате x y)"));
            String p = (String) serverMaker.deserialize(serverMaker.readData());
            String[] j = p.split(" ");
            String[] jj = p.split("\t");
            if (jj.length > j.length) j = jj;
            try {
                if (j.length != 2) serverMaker.writeData(serverMaker.serialize("Введите корректное число аргументов NRCL"));
                else if (Double.parseDouble(j[0]) > -48 && Double.parseDouble(j[1]) > -48) ticket.setCoordinates(new Coordinates(Double.parseDouble(j[0]), Double.parseDouble(j[1])));
                else serverMaker.writeData(serverMaker.serialize("Введите корректные значения x и y (они должны быть больше -48) NRCL"));
            } catch (NumberFormatException e) {serverMaker.writeData(serverMaker.serialize("Введите корректные значения x и y (они должны быть больше -48) NRCL"));}

        } while(ticket.getCoordinates() == null);

        if (ticket.getId() != 0 || ticket.getPrice() == null) {
            ticket.setPrice(-1.0);
            do {
                serverMaker.writeData(serverMaker.serialize("Введите стоимость билета: (она должна быть больше 0)"));
                String[] j = ((String) serverMaker.deserialize(serverMaker.readData())).split(" ");
                try {
                    if (j.length == 1) ticket.setPrice(Double.parseDouble(j[0]));
                    else {
                        serverMaker.writeData(serverMaker.serialize("Введите корректное число NRCL"));
                    }
                } catch (NumberFormatException e) {
                    serverMaker.writeData(serverMaker.serialize("Введите корректную стоимость NRCL"));
                }
            } while (ticket.getPrice() <= 0);
        }
        if (ticket.getId() == 0) ticket.setId(Ticket.generalId++);
        do {
            serverMaker.writeData(serverMaker.serialize("Введите тип билета: (оставьте поле пустым, если хотите) \n Список возможным типов: VIP, USUAL, BUDGETARY, CHEAP"));
            s = (String) serverMaker.deserialize(serverMaker.readData());
            if (!s.equals("")) {
                try {
                    ticket.setType(TicketType.valueOf(s.toUpperCase()));
                } catch (IllegalArgumentException e) {serverMaker.writeData(serverMaker.serialize("Введите корректное название типа NRCL"));}
            }
        } while (ticket.getType() == null && !s.equals(""));

        serverMaker.writeData(serverMaker.serialize("Куда билет? (если не хотите вводить, оставьте поле пустым, для продолжения напишите название места)"));
        s = (String) serverMaker.deserialize(serverMaker.readData());

        if (!s.equals("")) {
            ticket.setVenue(new Venue(s));
            s = "";
            do {
                serverMaker.writeData(serverMaker.serialize("Введите вместимость аудитории: (оставтье пустым, если она неизвестна)"));
                try {
                    s = (String) serverMaker.deserialize(serverMaker.readData());
                    if (s != null && !s.equals("") && Integer.parseInt(s) > 0) ticket.getVenue().setCapacity(Integer.parseInt(s));
                } catch (NumberFormatException | NullPointerException e) {serverMaker.writeData(serverMaker.serialize("Введите корректное значение NRCL"));}
            } while (ticket.getVenue().getCapacity() == null && s != null && !s.equals(""));

            do {
                serverMaker.writeData(serverMaker.serialize("Введите тип аудитории: (оставьте поле пустым, если хотите) \n Список возможным типов:   BAR, LOFT, THEATRE, MALL, STADIUM"));
                s = (String) serverMaker.deserialize(serverMaker.readData());
                if (!s.equals("")) {
                    try {
                        ticket.getVenue().setType(VenueType.valueOf(s.toUpperCase()));
                    } catch (IllegalArgumentException e) {serverMaker.writeData(serverMaker.serialize("Введите корректное название типа NRCL"));}
                }
            } while (ticket.getVenue().getType() == null && !s.equals(""));
        }
        serverMaker.writeData(serverMaker.serialize("Ticket created"));
    }

}
