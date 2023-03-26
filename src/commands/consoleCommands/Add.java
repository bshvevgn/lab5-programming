package commands.consoleCommands;

import commands.Receiver;
import parameters.MusicBand;

import java.util.ArrayList;

import static java.lang.Long.parseLong;

/**
 * This command adds new element into collection ArrayList<MusicBand>
 */

public class Add implements Command{

    public static String[] args = new String[0];
    public static String[] scrArgs = new String[6];
    public String[] getArgs() {
        return args;
    }

    public static String[] inputs = new String[6];
    public String[] getInputs() {
        return inputs;
    }


    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        Receiver reciever = new Receiver(path);
        reciever.addCommand(list, arguments, path, isScript);
        return list;
    }

    @Override
    public String[] args() {
        return args;
    }

}
