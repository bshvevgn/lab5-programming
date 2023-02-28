package commands.consoleCommands;

import parameters.MusicBand;

import java.util.ArrayList;
import java.util.Collections;

public class Reorder implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){
        Collections.reverse(list);
        System.out.println("Коллекция отсортирована в обратном порядке.");
        return list;
    }

    @Override
    public String[] args() {
        return args;
    }
}
