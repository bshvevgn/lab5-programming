package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.Coordinates;
import parameters.MusicBand;
import parameters.MusicGenre;
import parameters.Studio;

import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.*;
import static java.lang.Float.parseFloat;
import static java.lang.Long.parseLong;

public class UpdateById implements Command{

    public final static String[] args = new String[1];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path) {
        try {
            if(Command.isCorrectArgs(args, arguments)){
                if(Integer.parseInt(arguments[0]) <= list.size() - 1) {
                    MusicBand newBand = new MusicBand();
                    Coordinates coordinates = new Coordinates();
                    Studio studio = new Studio();

                    Scanner sc = new Scanner(System.in);

                    System.out.print("Введите значение поля Name: ");
                    String argument = sc.nextLine();
                    newBand.setName(argument);

                    System.out.print("Введите значение поля Genre\n(PSYCHEDELIC_ROCK\nPSYCHEDELIC_CLOUD_RAP\nMATH_ROCK\nPOST_ROCK): ");
                    argument = sc.nextLine();

                    MusicGenre mg = MusicGenre.valueOf(argument);
                    if (mg == null) {
                        System.out.println("Неправильное значение Genre.");
                    } else {
                        newBand.setGenre(mg);
                    }

                    newBand.setId(Integer.parseInt(arguments[0]));
                    System.out.print("Введите значение поля Coordinates.x: ");
                    argument = sc.nextLine();
                    try {
                        coordinates.setX(parseDouble(argument));
                    } catch (NumberFormatException e) {
                        System.out.println("Введён неправильный тип данных.");
                    }
                    System.out.print("Введите значение поля Coordinates.y: ");
                    argument = sc.nextLine();
                    try {
                        coordinates.setY(parseFloat(argument));
                    } catch (NumberFormatException e) {
                        System.out.println("Введён неправильный тип данных.");
                    }
                    newBand.setCoordinates(coordinates);
                    System.out.print("Введите значение поля Studio.name: ");
                    argument = sc.nextLine();
                    studio.setName(argument);
                    newBand.setStudio(studio);
                    System.out.print("Введите значение поля NumberOfParticipants: ");
                    argument = sc.nextLine();
                    try {
                        newBand.setNOP(parseLong(argument));
                    } catch (NumberFormatException e) {
                        System.out.println("Введён неправильный тип данных.");
                    }

                    list.set(Integer.parseInt(arguments[0]), newBand);
                    System.out.println("Объект с ID: " + Integer.parseInt(arguments[0]) + " заменён на: " + newBand.getName());
                } else {
                    System.out.println("Максимальный ID элемента: " + (list.size() - 1));
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
