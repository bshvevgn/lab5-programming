package commands.consoleCommands;

import exceptions.InvalidArgsException;
import parameters.Coordinates;
import parameters.MusicBand;
import parameters.MusicGenre;
import parameters.Studio;

import java.util.ArrayList;

public class Dev implements Command{

    public final static String[] args = new String[0];

    public ArrayList<MusicBand> execute(ArrayList<MusicBand> list, String[] arguments, String path) throws InvalidArgsException {
        try {
            if(Command.isCorrectArgs(args, arguments)){
                for (int i = 0; i < 10; i++) {
                    MusicBand newBand = new MusicBand();
                    Studio studio = new Studio();
                    Coordinates coordinates = new Coordinates();
                    list.add(newBand);
                    newBand.setName("Band");
                    newBand.setId(list.size());
                    newBand.setGenre(MusicGenre.MATH_ROCK);
                    coordinates.setX(27.04);
                    coordinates.setY((float) 25.05);
                    newBand.setCoordinates(coordinates);
                    studio.setName("Studio");
                    newBand.setStudio(studio);
                    newBand.setNOP(4);
                }
                System.out.println("Добавлены новые объекты");
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
