package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Info implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){
        try {
            if(Command.isCorrectArgs(args, arguments)){
                Path filepath = Paths.get(path);
                BasicFileAttributes attr;
                FileTime fileTime;

                System.out.println("Тип коллекции: " + list.getClass().getName());
                System.out.println("Размер коллекции: " + list.size());
                try {
                    attr = Files.readAttributes(filepath, BasicFileAttributes.class);
                    System.out.println("Дата инициализации: " + attr.creationTime());

                } catch (IOException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
                try {
                    fileTime = Files.getLastModifiedTime(filepath);
                    printFileTime(fileTime);
                } catch (IOException e) {
                    System.err.println("Cannot get the last modified time - " + e);
                }
                for (MusicBand band : list) {
                    System.out.println("\t-" + band.getName());
                }
            }
        } catch (InvalidArgsException e){
            System.out.println(e.getMessage());
        }

        return list;
    }

    private static void printFileTime(FileTime fileTime) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        System.out.println("Дата изменения: " + dateFormat.format(fileTime.toMillis()));
    }

    @Override
    public String[] args() {
        return args;
    }
}
