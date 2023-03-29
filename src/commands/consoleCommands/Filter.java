package commands.consoleCommands;

import commands.Receiver;
import parameters.MusicBand;

import java.util.ArrayList;

/**
 * This command prints elements of the collection ArrayList<MusicBand> with the specified genre parameter.
 */

public class Filter implements Command{

    public final static String[] args = {"имя жанра"};
    public static String[] inputs = new String[0];
    public String[] getInputs() {
        return inputs;
    }

    public String[] getArgs() {
        return args;
    }

    public boolean complicated = false;

    public boolean isComplicated(){
        return complicated;
    }

    Receiver receiver;
    public Filter (Receiver receiver){
        this.receiver = receiver;
    }

    public void execute(String[] arguments, String path, boolean isScript){
        receiver.filterCommand(arguments, path, isScript);
    }

    @Override
    public String[] args() {
        return args;
    }
}
