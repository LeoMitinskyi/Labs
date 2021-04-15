package serverok;

import serverok.collection.Ticket;

import java.nio.channels.ServerSocketChannel;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is main
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Ticket> collection = new LinkedList<>();
        try {
            ServerMaker serverMaker = new ServerMaker(8080);
            ServerPart serverPart = new ServerPart(collection, serverMaker);
            serverPart.readCommands();
        } catch (Exception e) {System.out.println("Что-то пошло не так или клиенту надоело здесь сидеть и вводить рандомные комманды");}
        FileWorker fl = new FileWorker(collection);
        fl.write("D:\\Java\\Labs\\Col.csv");
    }
}
