package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.util.ArrayList;

public interface Command {
    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path) throws InvalidArgsException;

    String[] args();

    public static boolean isCorrectArgs(String[] needArgs, String[] providedArgs) throws InvalidArgsException {
        if (needArgs.length != providedArgs.length) throw new InvalidArgsException("Некорректное количество аргументов команды.");
        return true;
    }
}
