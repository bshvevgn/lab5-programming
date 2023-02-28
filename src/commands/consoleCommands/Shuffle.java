package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This command shuffles the elements of the collection ArrayList<MusicBand> in random order.
 */

public class Shuffle implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript){
        try {
            if(Command.isCorrectArgs(args, arguments)){
                Collections.shuffle(list);
                System.out.println("Коллекция перемешана в случайном порядке.");
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
