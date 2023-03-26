package commands.consoleCommands;

import commands.Receiver;
import parameters.Coordinates;
import parameters.MusicBand;
import parameters.Studio;

import java.util.ArrayList;

import static java.lang.Long.parseLong;

/**
 * This command updates element of collection ArrayList<MusicBand> which ID equals argument.
 */

public class UpdateById implements Command{

    public final static String[] args = {"id"};
    public static String[] inputs = new String[6];
    public String[] getInputs() {
        return inputs;
    }

    public String[] getArgs() {
        return args;
    }

    public static String[] scrArgs = new String[6];

    public MusicBand newBand = new MusicBand();
    public Coordinates coordinates = new Coordinates();
    public Studio studio = new Studio();
    public int idToReplace = 0;
    String argument = "";

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        Receiver receiver = new Receiver(path);
        receiver.updateByIdCommand(list, arguments, path, isScript);
        return list;
    }

    @Override
    public String[] args() {
        return args;
    }

}
