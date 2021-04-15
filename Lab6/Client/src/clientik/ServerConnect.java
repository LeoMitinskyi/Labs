package clientik;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ServerConnect implements Serializable {
    protected SocketAddress serverAddress;
    protected SocketChannel client;
    protected byte[] lastCommand;

    public ServerConnect (InetAddress ADDR, int PORT) throws UnknownHostException, InterruptedException {
        this.serverAddress = new InetSocketAddress(ADDR, PORT);
        int t = 0;
        while (true) {
            try {
                client = SocketChannel.open(serverAddress);
                break;
            } catch (IOException e) {
                e.printStackTrace();
                Thread.sleep(1000);

                if (t == 60) {
                    System.out.println("Серверу нехорошо");
                    System.exit(1);
                }
                if (t == 0) {
                    System.out.println("Переподключение...");
                }
                t++;
            }
        }
    }

    public synchronized void writeData(byte[] bytes) {
        this.lastCommand = bytes;
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        try {
            this.client.write(buffer);
        } catch (IOException e) {
            System.out.println("Нет соединения с сервером, чтобы отправить данные");
            e.printStackTrace();
            System.exit(1);
        }
        buffer.flip();
        buffer.clear();
    }

    public synchronized byte[] readData() {
        byte[] a = new byte[10000];
        ByteBuffer buffer = ByteBuffer.wrap(a);
        buffer.clear();
        try {
            this.client.read(buffer);
        } catch (IOException e) {
            System.out.println("Нет соединения с сервером, чтобы получить данные");
            int t = 0;
            while (true) {
                try {
                    client = SocketChannel.open(serverAddress);
                    writeData(lastCommand);
                    client.read(buffer);
                    return a;
                } catch (IOException ex) {
                    if (t == 60) {
                        System.out.println("Серверу нехорошо.");
                        System.exit(1);
                    }

                    if (t == 0) {
                        System.out.println("Переподключение...");
                    }
                    t++;
                }
            }
        }
        buffer.flip();
        return a;
    }
}
