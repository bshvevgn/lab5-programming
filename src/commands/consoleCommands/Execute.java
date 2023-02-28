package commands.consoleCommands;

import commands.CommandRunner;
import exceptions.InvalidArgsException;
import parameters.Coordinates;
import parameters.MusicBand;
import parameters.MusicGenre;
import parameters.Studio;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This command executes commands from the script file.
 */

public class Execute implements Command{
    public final static String[] args = {"file_name"};

    @Override
    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript){
        try {
            if(Command.isCorrectArgs(args, arguments)){
                String script_path = arguments[0];
                File script_file = new File(script_path);
                try {
                    CommandRunner runner = new CommandRunner("");
                    runner.setScriptRunning(true);
                    Scanner sc = new Scanner(script_file);
                    if (sc.hasNext()) {
                        do {
                            runner.runCommand(list, sc.nextLine(), "", isScript);
                        } while (sc.hasNext());
                    } else {
                        System.out.println("Скрипт не содержит команд.");
                    }
                    runner.setScriptRunning(false);
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не обнаружен.");
                } catch (InvalidArgsException e) {
                    System.out.println(e.getMessage());
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
