package commands.consoleCommands;

import commands.Receiver;
import parameters.MusicBand;

import java.util.ArrayList;

/**
 * This command prints elements of the collection ArrayList<MusicBand> with the specified genre parameter.
 */

public class Filter implements Command{

    public final static String[] args = new String[1];
    public static String[] inputs = new String[0];
    public String[] getInputs() {
        return inputs;
    }

    public String[] getArgs() {
        return args;
    }

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript){
        Receiver reciever = new Receiver(path);
        reciever.filterCommand(list, arguments, path, isScript);
        return list;
    }

    @Override
    public String[] args() {
        return args;
    }
}
