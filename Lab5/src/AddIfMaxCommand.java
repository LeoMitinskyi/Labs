public class AddIfMaxCommand implements Command{
    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
