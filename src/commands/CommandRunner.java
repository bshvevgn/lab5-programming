package commands;

import commands.consoleCommands.*;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.io.BufferedReader;
import java.util.*;

/**
 * This class is necessary for reading and executing commands
 */

public class CommandRunner {

    String path = "";

    boolean isScriptRunning = false;

    public CommandRunner(String path) {
        this.path = path;
    }

    static ArrayList<String> lastCommands = new ArrayList<String>();

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
        commands.put("dev", new Dev());
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

    public static ArrayList<String> getLastCommands() {
        return lastCommands;
    }

    public boolean isScriptRunning() {
        return isScriptRunning;
    }

    public void setScriptRunning(boolean scriptRunning) {
        isScriptRunning = scriptRunning;
    }

    /*
     *This class has a static method runCommand which reads commands
     *and executes them.
     */
    public static void runCommand(ArrayList<MusicBand> list, String line, String path, boolean isScriptRunning) throws InvalidArgsException {
        if(line.equals("")) return;
        String commandWord = line.toLowerCase().split(" ")[0];
        String[] arguments = new String[line.split(" ").length - 1];
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = line.split(" ")[i + 1];
        }

        if(commands.containsKey(commandWord)) {
            if(lastCommands.size() == 15) {
                lastCommands.remove(0);
                lastCommands.add(commandWord);
            } else {
                lastCommands.add(commandWord);
            }
            try {
                commands.get(commandWord).execute(list, arguments, path, isScriptRunning);
            } catch (IllegalArgumentException exc) {
                System.out.print(exc.getMessage() + "\n");
            }
        } else {
            System.out.print("Неизвестная команда " + commandWord + ". Введите help для просмтора списка доступных команд.\n");
        }

    }
}
