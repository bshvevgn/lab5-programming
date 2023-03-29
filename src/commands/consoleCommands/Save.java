package commands.consoleCommands;

import commands.Receiver;
import parameters.MusicBand;

import java.util.ArrayList;

/**
 * This command saves main collection ArrayList<MusicBand> into XML document.
 */

public class Save implements Command{

    public final static String[] args = new String[0];
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
    public Save (Receiver receiver){
        this.receiver = receiver;
    }

    public void execute(String[] arguments, String path, boolean isScript){
        receiver.saveCommand(arguments, path, isScript);
    }

    @Override
    public String[] args() {
        return args;
    }
}
