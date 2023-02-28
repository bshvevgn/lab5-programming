package commands.consoleCommands;

import commands.CommandRunner;
import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class History implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){
        try {
            if(Command.isCorrectArgs(args, arguments)){
                ArrayList<String> history = new CommandRunner("").getLastCommands();
                for(int i = history.size()-1; i >= 0; i--){
                    System.out.println(history.get(i));
                }
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
