package commands;

import clientik.collection.Ticket;
import exceptions.IdNotFoundException;

import java.util.LinkedList;

/**
 * Command class that updates the ticket with the given id
 */
public class UpdateCommand extends CommandWithAdditionalArgument{
    /**ticket id*/
    private int ID;

    public Ticket ticket;

    /**
     * Constructor with additional argument
     * @param c - collection of tickets
     */
    public UpdateCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    /**
     * Update the ticket with the given id
     */
    @Override
    public String execute() {
        int k = 0;
        for (Ticket i : c) {
            if (i.getId() == ID) {
                ticket = i;
            } else k++;
        }
        if (k == c.size()) throw new IdNotFoundException();
        return "Элемент с заданным id был успешно обновлён";
    }

    /**
     * Getiing ticket id {@link UpdateCommand#ID}
     * @param obj - ticket id
     */
    @Override
    public void addArgument(String obj) {
        ID = Integer.parseInt(obj);
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "update <id> : обновить значение элемента коллекции, id которого равен заданному";
    }
}
