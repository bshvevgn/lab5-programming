package commands.consoleCommands;

import commands.Receiver;
import parameters.MusicBand;

import java.util.ArrayList;

/**
 * This command end shuts down the program
 */

public class Exit implements Command{

    public final static String[] args = new String[0];
    public static String[] inputs = new String[0];
    public String[] getInputs() {
        return inputs;
    }

    public String[] getArgs() {
        return args;
    }

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript){
        Receiver reciever = new Receiver(path);
        reciever.exitCommand(list, arguments, path, isScript);
        return list;
    }

    @Override
    public String[] args() {
        return args;
    }
}
