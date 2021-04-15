package commands;

import serverok.collection.Ticket;

import java.util.LinkedList;

/**
 * Command class which outputs elements in the collection
 */
public class ShowCommand extends CommandWithoutAdditionalArgument{

    /**
     * Output elements in the collection
     * @param c - collection of tickets
     */
    public ShowCommand(LinkedList<Ticket> c) {
        this.c = c;
    }

    @Override
    public String execute() {
        StringBuilder result = new StringBuilder();
        if (c.size() != 0) {
            for (Ticket i : c) {
                result.append(i + "\n");
            }
            return result.toString();
        }
        return "Нечего показывать";
    }

    @Override
    public String toString() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
