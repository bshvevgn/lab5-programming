package commands.consoleCommands;

import parameters.MusicBand;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Average implements Command {

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){
        int NOPsum = 0;
        for(int i = 0; i < list.size(); i++){
            NOPsum += list.get(i).getNOP();
        }
        System.out.println("Среднее кол-во участиников: " + (NOPsum/list.size()));
        return list;
    }

    @Override
    public String[] args() {
        return args;
    }
}
