package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.Coordinates;
import parameters.MusicBand;
import parameters.MusicGenre;
import parameters.Studio;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Average implements Command {

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript){
        int NOPsum = 0;
        try {
            if(Command.isCorrectArgs(args, arguments)){
                for(int i = 0; i < list.size(); i++){
                    NOPsum += list.get(i).getNOP();
                }
                System.out.println("Среднее кол-во участиников: " + (NOPsum/list.size()));
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
