package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This command sorts collection ArrayList<MusicBand> in natural order.
 */

public class Sort implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        try {
            if(Command.isCorrectArgs(args, arguments)){
                for (int k = 0; k < list.size(); k++) {
                    if (k < list.size() - 1) {
                        if (list.get(k + 1).getId() < list.get(k).getId()) {
                            sorting(list);
                        }
                    }
                }

                System.out.println("Коллекция отсортирована по ID.");
            }
        } catch (InvalidArgsException e){
            System.out.println(e.getMessage());
        }

        return list;
    }

    private void sorting(ArrayList<MusicBand> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                if (list.get(i).getId() > list.get(i + 1).getId()) {
                    Collections.swap(list, i, i + 1);
                    sorting(list);
                }
            }
        }

    }

    @Override
    public String[] args() {
        return args;
    }
}
