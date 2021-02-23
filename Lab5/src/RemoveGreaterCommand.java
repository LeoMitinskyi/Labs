public class RemoveGreaterCommand implements Command{
    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }
}
