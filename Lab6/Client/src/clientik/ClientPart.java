package clientik;

import com.sun.xml.internal.ws.api.pipe.Fiber;
import commands.*;
import exceptions.*;
import java.io.*;
import java.nio.channels.CompletionHandler;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.Future;

public class ClientPart {
    private String lastString;
    private InputStream inputStream;
    private Scanner in;
    private final ServerConnect serverDeliver;
    private CommandDecoder cd = new CommandDecoder();

    public ClientPart (ServerConnect serverDeliver, InputStream inputStream) {
        this.serverDeliver = serverDeliver;
        this.inputStream = inputStream;
        this.in = new Scanner(this.inputStream);
        cd = (CommandDecoder) deserialize(serverDeliver.readData());
        String filePath = safeRead("Введите путь к файлу, из которого необходимо считать коллекцию:");
        serverDeliver.writeData(serialize(filePath));
        byte[] a = serverDeliver.readData();
        System.out.println((String) deserialize(a));
    }

    private byte[] serialize(Object obj) {
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

    private String safeRead(String field) {
        if (this.inputStream == System.in) {
            System.out.println(field.replace("NRCL", ""));
        }
            try {
                if (!field.endsWith("NRCL")) {
                    lastString = in.nextLine();
                } else lastString = "NRCL";
            } catch (NoSuchElementException e) {
                in.close();
                System.exit(0);
            }
            return lastString;
    }

    public void understanding() {
        String command = "";

        while (!command.equals("exit")) {
            try {
                command = safeRead("Введите команду: (help - узнать список команд, exit - выход из программы (без сохранения))");
                Command inputCommand = cd.decode(command);
                this.serverDeliver.writeData(serialize(inputCommand));
                if (inputCommand.getClass() == AddCommand.class || inputCommand.getClass() == AddIfMaxCommand.class || inputCommand.getClass() == UpdateCommand.class) {
                    String s = ((String) deserialize(serverDeliver.readData()));
                    while (!s.equals("Ticket created")) {
                        String safeRead = safeRead(s);
                        if (!safeRead.equals("NRCL")) {
                            serverDeliver.writeData(serialize(safeRead));
                            System.out.println(safeRead);
                        }
                        System.out.println(safeRead);
                        s = ((String) deserialize(serverDeliver.readData()));
                    }
                }
                String output = (String) deserialize(serverDeliver.readData());
                System.out.println(output);
                if (inputCommand.getClass() == ExecuteScriptCommand.class) {
                    if (output.endsWith("exit")) {
                        System.exit(0);
                    }
                }
            } catch(NullPointerException | IllegalArgumentException e){
                if (command.equals("exit")) {System.exit(0);}
                System.out.println("Такой команды не существует.");
            }
            catch(IllegalCountOfArgumentsException e){
                e.getMessage();
            }
        }
    }
}
