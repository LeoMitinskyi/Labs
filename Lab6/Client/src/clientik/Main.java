package clientik;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is main
 */
public class Main {
    public static void main(String[] args) {
        try {
            ServerConnect serverConnect = new ServerConnect(InetAddress.getLocalHost(), 8080);
            ClientPart clientPart = new ClientPart(serverConnect, System.in);
            clientPart.understanding();
        } catch (InterruptedException | UnknownHostException e) {
            System.out.println("Что-то пошло не так.");
        }
    }
}
