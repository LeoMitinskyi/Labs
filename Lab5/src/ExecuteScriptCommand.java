public class ExecuteScriptCommand implements Command{
    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
