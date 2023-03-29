package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.util.ArrayList;

/**
 * This interface is implemented by all commands.
 */

public interface Command {
    public void execute(String[] arguments, String path, boolean isScript) throws InvalidArgsException;

    String[] args();

    boolean complicated = false;

    static boolean isCorrectArgs(String[] needArgs, String[] providedArgs) throws InvalidArgsException {
        if (needArgs.length != providedArgs.length) throw new InvalidArgsException("Некорректное количество аргументов команды.");
        return true;
    }

    boolean isComplicated();

    public String[] getArgs();
    public String[] getInputs();
}
