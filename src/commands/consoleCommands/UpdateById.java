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

    public final static String[] args = {"id элемента"};
    public static String[] inputs = new String[6];
    public String[] getInputs() {
        return inputs;
    }
    public String[] getArgs() {
        return args;
    }

    public boolean complicated = true;

    public boolean isComplicated(){
        return complicated;
    }

    Receiver receiver;
    public UpdateById (Receiver receiver){
        this.receiver = receiver;
    }

    public void execute(String[] arguments, String path, boolean isScript) {
        receiver.updateByIdCommand(arguments, path, isScript);
    }

    @Override
    public String[] args() {
        return args;
    }

}
