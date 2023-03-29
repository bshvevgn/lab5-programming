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

    public boolean complicated = true;

    public boolean isComplicated(){
        return complicated;
    }

    Receiver receiver;
    public Add (Receiver receiver){
        this.receiver = receiver;
    }

    public void execute(String[] arguments, String path, boolean isScript) {
        receiver.addCommand(arguments, path, isScript);
    }

    @Override
    public String[] args() {
        return args;
    }

}
