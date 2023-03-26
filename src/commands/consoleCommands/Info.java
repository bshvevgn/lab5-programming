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

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript){
        Receiver reciever = new Receiver(path);
        reciever.infoCommand(list, arguments, path, isScript);
        return list;
    }

    private static void printFileTime(FileTime fileTime) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        System.out.println("Дата изменения: " + dateFormat.format(fileTime.toMillis()));
    }

    @Override
    public String[] args() {
        return args;
    }
}
