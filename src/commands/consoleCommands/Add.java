package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.Coordinates;
import parameters.MusicBand;
import parameters.MusicGenre;
import parameters.Studio;

import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Double.*;
import static java.lang.Float.parseFloat;
import static java.lang.Long.parseLong;

/**
 * This command adds new element into collection ArrayList<MusicBand>
 */

public class Add implements Command{

    public static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        MusicBand newBand = new MusicBand();
        Coordinates coordinates = new Coordinates();
        Studio studio = new Studio();
        /*if(isScript){
            args = new String[6];
            newBand.setName(arguments[0]);
            newBand.setGenre(MusicGenre.valueOf(arguments[1]));
            newBand.setId(list.size());
            try {
                coordinates.setX(parseDouble(arguments[2]));
            } catch (NumberFormatException e) {
                System.out.println("Введён неправильный тип данных.\n");
            }
            try {
                coordinates.setY(parseFloat(arguments[3]));
            } catch (NumberFormatException e) {
                System.out.println("Введён неправильный тип данных.\n");
            }
            studio.setName(arguments[4]);
            newBand.setStudio(studio);
            try {
                if (parseLong(arguments[5]) < 0) {
                    System.out.println("Введите значение number_of_participants больше 0.");
                }
                newBand.setNOP(parseLong(arguments[5]));
            } catch (NumberFormatException e) {
                System.out.println("Введён неправильный тип данных.\n");
            }
            list.add(newBand);
            System.out.println("Добавлен новый объект: " + newBand.getName() + " (ID: " + newBand.getId() + ")");

        } else { */

            Scanner sc = new Scanner(System.in);
            int stage = 0;
            String argument = "";
            try {
                if (Command.isCorrectArgs(args, arguments)) {
                    while (true) {
                        switch (stage) {
                            case 0 -> {
                                System.out.print("Введите значение поля Name: ");
                                argument = sc.nextLine();
                                if (Objects.equals(argument, "")) {
                                    System.out.println("Введите не пустую строку.\n");
                                    stage--;
                                }
                                newBand.setName(argument);
                                stage++;
                            }
                            case 1 -> {
                                System.out.print("Введите значение поля Genre\n(PSYCHEDELIC_ROCK\nPSYCHEDELIC_CLOUD_RAP\nMATH_ROCK\nPOST_ROCK): ");
                                argument = sc.nextLine();
                                try {
                                    MusicGenre mg = MusicGenre.valueOf(argument);
                                    newBand.setGenre(mg);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Введено неправильное значение.\n");
                                    stage--;
                                }
                                stage++;
                            }
                            case 2 -> {
                                newBand.setId(list.size());
                                stage++;
                            }
                            case 3 -> {
                                System.out.print("Введите значение поля Coordinates.x: ");
                                argument = sc.nextLine();
                                try {
                                    coordinates.setX(parseDouble(argument));
                                } catch (NumberFormatException e) {
                                    System.out.println("Введён неправильный тип данных.\n");
                                    stage--;
                                }
                                stage++;
                            }
                            case 4 -> {
                                System.out.print("Введите значение поля Coordinates.y: ");
                                argument = sc.nextLine();
                                try {
                                    coordinates.setY(parseFloat(argument));
                                } catch (NumberFormatException e) {
                                    System.out.println("Введён неправильный тип данных.\n");
                                    stage--;
                                }
                                newBand.setCoordinates(coordinates);
                                stage++;
                            }
                            case 5 -> {
                                System.out.print("Введите значение поля Studio.name: ");
                                argument = sc.nextLine();
                                studio.setName(argument);
                                newBand.setStudio(studio);
                                stage++;
                            }
                            case 6 -> {
                                System.out.print("Введите значение поля NumberOfParticipants: ");
                                argument = sc.nextLine();
                                try {
                                    if (parseLong(argument) < 0) {
                                        System.out.println("Введите значение больше 0.");
                                        stage--;
                                    }
                                    newBand.setNOP(parseLong(argument));
                                } catch (NumberFormatException e) {
                                    System.out.println("Введён неправильный тип данных.\n");
                                    stage--;
                                }
                                stage++;
                            }
                            case 7 -> {
                                list.add(newBand);
                                System.out.println("Добавлен новый объект: " + newBand.getName() + " (ID: " + newBand.getId() + ")");
                                stage++;
                                return list;
                            }
                        }
                        if (stage == 8) {
                            break;
                        }
                    }
                }
            } catch (InvalidArgsException e) {
                System.out.println(e.getMessage());
            }
        //}
        return list;
    }

    @Override
    public String[] args() {
        return args;
    }

}
