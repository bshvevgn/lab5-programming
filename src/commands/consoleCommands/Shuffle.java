package commands.consoleCommands;

import parameters.MusicBand;

import java.util.ArrayList;
import java.util.Collections;

public class Shuffle implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){
        Collections.shuffle(list);
        System.out.println("Коллекция перемешана в случайном порядке.");
        return list;
    }

    @Override
    public String[] args() {
        return args;
    }
}
