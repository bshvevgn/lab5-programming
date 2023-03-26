package commands;

import commands.consoleCommands.*;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.io.BufferedReader;
import java.util.*;

/**
 * This class is necessary for reading and executing commands
 */

public class Invoker {

    public static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("help", new Help());
        commands.put("add", new Add());
        commands.put("show", new Show());
        commands.put("exit", new Exit());
        commands.put("clear", new Clear());
        commands.put("reorder", new Reorder());
        commands.put("shuffle", new Shuffle());
        commands.put("sort", new Sort());
        commands.put("history", new History());
        commands.put("remove_by_id", new RemoveById());
        commands.put("update_by_id", new UpdateById());
        commands.put("execute_script", new Execute());
        commands.put("average_of_number_of_participants", new Average());
        commands.put("average_of_nop", new Average());
        commands.put("info", new Info());
        commands.put("filter_by_genre", new Filter());
        commands.put("print_field_descending_genre", new PrintGenre());
        commands.put("save", new Save());
    }

    public boolean isScriptRunning = false;

    static Command command = new Command() {
        @Override
        public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) throws InvalidArgsException {
            return list;
        }

        @Override
        public String[] args() {
            return new String[0];
        }

        @Override
        public String[] getArgs() {
            return new String[0];
        }

        @Override
        public String[] getInputs() {
            return new String[0];
        }
    };

    public static String[] arguments;

    public static String commandWord;

    public Invoker(String command, String[] arguments) {
        this.commandWord = command;
        this.arguments = arguments;
    }

    static ArrayList<String> lastCommands = new ArrayList<String>();

    public static ArrayList<String> getLastCommands() {
        return lastCommands;
    }


    public static int inputsCount = 0;

    /*
     *This class has a static method push which reads commands
     *and executes them.
     */
    public static void push(ArrayList<MusicBand> list, String path, boolean isScriptRunning) throws InvalidArgsException {
        if(commands.containsKey(commandWord)) {
            if(lastCommands.size() == 15) {
                lastCommands.remove(0);
                lastCommands.add(commandWord);
            } else {
                lastCommands.add(commandWord);
            }
            command = commands.get(commandWord);
            try {
                if(isScriptRunning){
                    inputsCount = (command.getInputs().length);
                    if(inputsCount == arguments.length-command.getArgs().length){
                        command.execute(list, arguments, path, isScriptRunning);
                    } else {
                        System.out.println("Количество аргументов некорректно. Введено: " + arguments.length + ", необходимо: " + inputsCount);
                    }
                } else {
                    command.execute(list, arguments, path, isScriptRunning);
                }
            } catch (IllegalArgumentException exc) {
                System.out.print("Введён неправильный тип данных.\n");
            }
        } else {
            System.out.print("Неизвестная команда " + commandWord + ". Введите help для просмтора списка доступных команд.\n");
        }

    }
}
