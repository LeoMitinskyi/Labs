package serverok;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerMaker {
    protected SocketAddress socketAddress;
    protected ServerSocketChannel server;
    protected SocketChannel channel;

    public ServerMaker (int PORT) {
        this.socketAddress = new InetSocketAddress(PORT);
        try {
            this.server = ServerSocketChannel.open().bind(socketAddress);
            channel = server.accept();
            System.out.println("Ожидание подключения.");
        } catch (IOException e) {
            System.out.println("Клиент не подключён к серверу");
            System.exit(1);
        }
    }

    public synchronized byte[] readData() {
        byte[] a = new byte[10000];
        ByteBuffer buffer = ByteBuffer.wrap(a);
        try {
            buffer.clear();
            this.channel.read(buffer);
            return a;
        } catch (IOException e) {
            System.out.println("Клиент не подключёнк серверу для получения данных");
        }
        buffer.flip();
        buffer.clear();
        return null;
    }

    public byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object deserialize (byte[] rawData) {

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(rawData);
            ObjectInputStream objectInputStream = new ObjectInputStream(bis);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public synchronized void writeData(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        try {
            this.channel.write(buffer);
        } catch (IOException e) {
            System.out.println("Клиент не подключён к серверу для отправки данных");
        }
        buffer.flip();
        buffer.clear();
    }
}
