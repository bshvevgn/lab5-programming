package commands.consoleCommands;

import commands.CommandRunner;
import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Execute implements Command{
    public final static String[] args = {"file_name"};

    @Override
    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){

        String script_path = arguments[0];
        File script_file = new File(script_path);
        try {
            Scanner sc = new Scanner(script_file);
            if (sc.hasNext()) {
                do {
                    CommandRunner.runCommand(list, sc.nextLine(), "");
                } while (sc.hasNext());
            } else {
                System.out.println("Скрипт не содержит команд.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка. Файл не обнаружен");
        } catch (InvalidArgsException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public String[] args() {
        return args;
    }
}
