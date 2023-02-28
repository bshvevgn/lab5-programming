package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.io.*;
import java.util.ArrayList;

/**
 * This command saves main collection ArrayList<MusicBand> into XML document.
 */

public class Save implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript){
        try {
            if(Command.isCorrectArgs(args, arguments)){
                String data = "";
                try {
                    FileOutputStream file = new FileOutputStream(path);

                    BufferedOutputStream output = new BufferedOutputStream(file);

                    byte[] array = data.getBytes();

                    data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                    array = data.getBytes();
                    output.write(array);
                    data = "<MusicBands>\n";
                    array = data.getBytes();
                    output.write(array);
                    for (MusicBand band : list) {
                        data = "\t<MusicBand " + "id=\"" + band.getId() + "\" name=\"" + band.getName() + "\" genre=\"" + band.getGenre().toString() + "\" x=\"" + band.getCoordinates().getX() + "\" y=\"" + band.getCoordinates().getY() + "\" studio=\"" + band.getStudio().getName() + "\" number_of_participants=\"" + band.getNOP() + "\" />\n";
                        array = data.getBytes();
                        output.write(array);
                    }
                    data = "</MusicBands>\n";
                    array = data.getBytes();
                    output.write(array);
                    System.out.println("Коллекция сохранена в файл: " + path);

                    output.close();
                }

                catch (Exception e) {
                    e.getStackTrace();
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
