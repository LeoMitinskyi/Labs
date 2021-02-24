import java.util.*;

public class CommandDecoder {

    private HashMap<String, Command> commands = new HashMap<>();
    private LinkedList<Ticket> c = new LinkedList<>();
    private Command cd;

    {
        commands.put("info", new InfoCommand(c));
        commands.put("show", new ShowCommand(c));
        commands.put("add", new AddCommand(c));
        commands.put("update <id>", new UpdateCommand(c));

    }

    public void decode(String com) {
            String[] s;
            s = com.split(" ");
            for (String i:s) System.out.println(i);
            if (s.length == 1) {
                cd = commands.get(s[0].toLowerCase());
            } else if (s.length == 2) {
                try{
                if (s[0].toLowerCase().equals("update") || s[0].toLowerCase().equals("remove_by_id")) {
                    cd = commands.get((s[0] + " <id>").toLowerCase());
                    int i = Integer.parseInt(s[1]);
                    for (Ticket ticket:c) {
                        if (ticket.getId() == i) {
                            ticket.mark();
                        } else throw new IdNotFoundException();
                    }
                }
                if (s[0].toLowerCase().equals("filter_greater_than_price")) {
                    cd = commands.get((s[0] + " <price>").toLowerCase());
                    double i = Double.parseDouble(s[1]);
                    for (Ticket ticket:c) {
                        if (ticket.getPrice() > i) {
                            ticket.mark();
                        }
                    }
                }
                    } catch (NumberFormatException e) {
                        System.out.println("Аргумент имеет неправльный тип (для id - int, для price - double)");
                    }
                }
            else if (s.length > 2) System.out.println("Неправильное значение аргументов или неверно введённая команда");
            cd.execute();

    }

    public void sort(LinkedList<Ticket> c) {
        Comparator<Ticket> comparator = Comparator.comparing(Ticket::getPrice);
        c.sort(comparator);
    }

    public void changeCollection(LinkedList<Ticket> c) {
        this.c = c;
    }
    public Collection<Ticket> getCollection() {
        return c;
    }

}
