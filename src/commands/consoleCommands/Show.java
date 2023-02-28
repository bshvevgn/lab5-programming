package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.util.ArrayList;

/**
 * This command prints all elements of collection ArrayList<MusicBand> with their parameters.
 */

public class Show implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript){
        try {
            if(Command.isCorrectArgs(args, arguments)){
                if (list.size() == 0){
                    System.out.println("Коллекция пуста.");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("ID: " + list.get(i).getId() + "\nИмя: " + list.get(i).getName() + "\nЖанр: " + list.get(i).getGenre() + "\nX: " + list.get(i).getCoordinates().getX() + "\nY: " + list.get(i).getCoordinates().getY() + "\nСтудия: " + list.get(i).getStudio().getName() + "\nКол-во участников: " + list.get(i).getNOP());
                        System.out.println("");
                    }
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
