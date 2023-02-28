package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.Coordinates;
import parameters.MusicBand;
import parameters.MusicGenre;
import parameters.Studio;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Clear implements Command {

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){
        try {
            if(Command.isCorrectArgs(args, arguments)){
                System.out.println("Вы уверены, что хотите очистить коллекцию? Данное действие необратимо. (y/n)");
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                if(confirm(input)){
                    list.clear();
                }
            }
        } catch (InvalidArgsException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public boolean confirm(String input){
        boolean start = false;
        if(Objects.equals(input, "y")) {
            System.out.println("Коллекция очищена.");
            start = true;
        } else if(Objects.equals(input, "n")){
            System.out.println("Операция отменена.");
        }
        return start;
    }

    @Override
    public String[] args() {
        return args;
    }
}
