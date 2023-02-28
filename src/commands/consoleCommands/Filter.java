package commands.consoleCommands;

import parameters.MusicBand;

import java.util.ArrayList;
import java.util.Objects;

public class Filter implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path){
        if (list.size() == 0){
            System.out.println("Коллекция пуста.");
        } else {
            int number = 0;
            for (int i = 0; i < list.size(); i++) {
                if(Objects.equals(list.get(i).getGenre().toString(), arguments[0])) {
                    number++;
                    System.out.println("ID: " + list.get(i).getId() + "\nИмя: " + list.get(i).getName() + "\nЖанр: " + list.get(i).getGenre() + "\nX: " + list.get(i).getCoordinates().getX() + "\nY: " + list.get(i).getCoordinates().getY() + "\nСтудия: " + list.get(i).getStudio().getName() + "\nКол-во участников: " + list.get(i).getNOP());
                    System.out.println("");
                }
            }
            if(number == 0){
                System.out.println("Не найдено элементов с заданным критерием.");
            }
        }
        return list;
    }

    @Override
    public String[] args() {
        return args;
    }
}
