package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.util.ArrayList;

/**
 * This command prints information about all available commands
 */

public class Help implements Command {

    public final static String[] args = new String[0];

    @Override
    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        try {
            if(Command.isCorrectArgs(args, arguments)){
                System.out.println("""
                    help : вывести справку по доступным командам
                    info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                    show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                    add {element} : добавить новый элемент в коллекцию
                    update id {element} : обновить значение элемента коллекции, id которого равен заданному
                    remove_by_id id : удалить элемент из коллекции по его id
                    clear : очистить коллекцию
                    save : сохранить коллекцию в файл
                    execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                    exit : завершить программу (без сохранения в файл)
                    shuffle : перемешать элементы коллекции в случайном порядке
                    reorder : отсортировать коллекцию в порядке, обратном нынешнему
                    history : вывести последние 15 команд (без их аргументов)
                    sort : отсортировать коллекцию в естественном порядке
                    average_of_number_of_participants : вывести среднее значение поля numberOfParticipants для всех элементов коллекции
                    filter_by_genre genre : вывести элементы, значение поля genre которых равно заданному
                    print_field_descending_genre : вывести значения поля genre всех элементов в порядке убывания""");
            }
        } catch (InvalidArgsException e){
            System.out.println(e.getMessage());
        }
            return list;
    }

    @Override
    public String[] args() {
        return args;
    }
}
