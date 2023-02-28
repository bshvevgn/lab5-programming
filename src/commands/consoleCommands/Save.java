package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Save implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){
        try {
            if(Command.isCorrectArgs(args, arguments)){
                try {
                    File file = new File(path);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    PrintWriter pw = new PrintWriter(file);
                    pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                    pw.println("<MusicBands>");
                    for (MusicBand band : list) {
                        pw.println("\t<MusicBand " + "id=\"" + band.getId() + "\" name=\"" + band.getName() + "\" genre=\"" + band.getGenre().toString() + "\" x=\"" + band.getCoordinates().getX() + "\" y=\"" + band.getCoordinates().getY() + "\" studio=\"" + band.getStudio().getName() + "\" number_of_participants=\"" + band.getNOP() + "\" />");
                    }
                    pw.println("</MusicBands>");
                    System.out.println("Коллекция сохранена в файл: " + path);
                    pw.close();
                } catch (IOException e) {
                    System.out.println("Ошибка " + e);
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
