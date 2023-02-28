package commands.consoleCommands;

import parameters.MusicBand;

import java.util.ArrayList;

public class Exit implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){
            System.exit(0);
            return list;
    }

    @Override
    public String[] args() {
        return args;
    }
}
