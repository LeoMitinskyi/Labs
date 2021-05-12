package commands;

import collection.Ticket;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Command class that removes first element in the collection
 */
public class RemoveHeadCommand extends CommandWithoutAdditionalArgument{

    /**
     * Constructor with parameter
     * @param c - collection
     */
    public RemoveHeadCommand(LinkedList<Ticket> c) {this.c = c;}

    /**
     * Remove first element in the collection
     */
    @Override
    public String execute() {
        try {
            String result = c.getFirst().toString();
            c.removeFirst();
            return result;
        } catch (NoSuchElementException e) {return "Коллекция пуста";}
    }

    /**
     * @return info about command
     */
    @Override
    public String toString() {
        return "remove_head : вывести первый элемент коллекции и удалить его";
    }
}
