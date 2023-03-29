package commands.consoleCommands;

import commands.Receiver;
import parameters.MusicBand;

import java.util.ArrayList;

/**
 * This command prints the last 15 executed commands without their arguments.
 */

public class History implements Command{

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
    public History (Receiver receiver){
        this.receiver = receiver;
    }

    public void execute(String[] arguments, String path, boolean isScript){
        receiver.historyCommand(arguments, path, isScript);
    }

    @Override
    public String[] args() {
        return args;
    }
}
