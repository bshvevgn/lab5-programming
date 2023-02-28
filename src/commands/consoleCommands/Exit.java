package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.Coordinates;
import parameters.MusicBand;
import parameters.MusicGenre;
import parameters.Studio;

import java.util.ArrayList;

public class Exit implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){
        try {
            if(Command.isCorrectArgs(args, arguments)){
                System.exit(0);
            }
        } catch (InvalidArgsException e){
            System.out.println(e.getMessage());
        }
            return list;
    }

    @Override
    public String[] args() {
        return args;
    }
}
