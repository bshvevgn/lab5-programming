package commands.consoleCommands;

import commands.Receiver;
import parameters.MusicBand;

import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This command prints information about collection ArrayList<MusicBand>.
 */

public class Info implements Command{

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
    public Info (Receiver receiver){
        this.receiver = receiver;
    }

    public void execute(String[] arguments, String path, boolean isScript){
        receiver.infoCommand(arguments, path, isScript);
    }

    @Override
    public String[] args() {
        return args;
    }
}
