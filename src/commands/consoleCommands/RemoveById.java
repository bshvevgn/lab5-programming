package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.MusicBand;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class RemoveById implements Command {

    public final static String[] args = {"id"};

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path) {
        try {
            if(Command.isCorrectArgs(args, arguments)){
                if (Integer.parseInt(arguments[0]) > list.size() - 1){
                    System.out.println("Макимальный ID элемента: " + (list.size() - 1));
                } else {
                    System.out.println("Вы уверены, что хотите удалить элемент? Данное действие необратимо. (y/n)");
                    Scanner sc = new Scanner(System.in);
                    if(confirm(sc.nextLine())) {
                        list.remove(Integer.parseInt(arguments[0]));
                        System.out.println("Из коллекции удалён объект с ID: " + arguments[0]);
                /*for (int i = Integer.parseInt(arguments[0]); i < list.size(); i++) {
                    list.get(i).setId(list.get(i).getId() - 1);
                }*/
                    }
                }
            }
        } catch (InvalidArgsException e){
            System.out.println(e.getMessage());
        }

        return list;
    }

    public boolean confirm(String input){
        boolean start = false;
        if(Objects.equals(input, "y")) {
            start = true;
        } else if(Objects.equals(input, "n")){
            System.out.println("Операция отменена.");
        }
        return start;
    }

    @Override
    public String[] args() {
        return args;
    }
}
