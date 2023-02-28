package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;
import parameters.MusicGenre;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This command prints elements of the collection ArrayList<MusicBand> in descending order of genre value.
 */

public class PrintGenre implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript){
        try {
            if(Command.isCorrectArgs(args, arguments)){
                MusicGenre[] genres = MusicGenre.values();
                if (list.size() == 0){
                    System.out.println("Коллекция пуста.");
                } else {
                    for (int k = 0; k < genres.length; k++) {
                        for (int i = 0; i < list.size(); i++) {
                            if (Objects.equals(list.get(i).getGenre(), genres[k])) {
                                System.out.println("ID: " + list.get(i).getId() + "\nИмя: " + list.get(i).getName() + "\nЖанр: " + list.get(i).getGenre());
                                System.out.println("");
                            }
                        }
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
