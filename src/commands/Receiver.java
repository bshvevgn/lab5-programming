package commands;

import commands.consoleCommands.*;

import exceptions.InvalidArgsException;
import parameters.Coordinates;
import parameters.MusicBand;
import parameters.MusicGenre;
import parameters.Studio;
import validators.GenreValidator;
import validators.NameValidator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Long.parseLong;

/**
 * This class is necessary for reading and executing commands
 */

public class Receiver {

    String path = "";

    boolean isScriptRunning = false;

    public Receiver(String path) {
        this.path = path;
    }

    public boolean isScriptRunning() {
        return isScriptRunning;
    }

    public void setScriptRunning(boolean scriptRunning) {
        isScriptRunning = scriptRunning;
    }

    public static int inputsCount = 0;

    /*
     *This class has a static method runCommand which reads commands
     *and executes them.
     */
    public static void runCommand(ArrayList<MusicBand> list, String line, String path, boolean isScriptRunning) throws InvalidArgsException {
        if (line.equals("")) return;

        String commandWord = line.toLowerCase().split(" ")[0];
        String[] arguments = new String[line.split(" ").length - 1];
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = line.split(" ")[i + 1];
        }

        Invoker invoker = new Invoker(commandWord, arguments);
        invoker.push(list, path, isScriptRunning);

    }

    public ArrayList<MusicBand> addCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        MusicBand newBand = new MusicBand();

        String[] args = new String[0];
        String[] scrArgs = new String[6];

        AddOperations operations = new AddOperations();

        try {
            if (!isScript) {
                if (Command.isCorrectArgs(args, arguments)) {
                    operations.setName(newBand, sc, "");
                    operations.setGenre(newBand, sc, "");
                    operations.setX(newBand, sc, "");
                    operations.setY(newBand, sc, "");
                    operations.setStudio(newBand, sc, "");
                    operations.setNOP(newBand, sc, "");
                    operations.done(list, newBand);
                }
            } else {
                if (arguments.length == scrArgs.length) {
                    operations.setName(newBand, sc, arguments[0]);
                    operations.setGenre(newBand, sc, arguments[1]);
                    operations.setX(newBand, sc, arguments[2]);
                    operations.setY(newBand, sc, arguments[3]);
                    operations.setStudio(newBand, sc, arguments[4]);
                    operations.setNOP(newBand, sc, arguments[5]);
                    operations.done(list, newBand);
                } else {
                    System.out.println();
                }
            }
        } catch (InvalidArgsException | IOException e) {
            System.out.println("Введён неправильный тип данных...");
        }
        return list;
    }

    public static class AddOperations {
        Coordinates coordinates = new Coordinates();
        Studio studio = new Studio();
        String argument = "";

        public void setName(MusicBand newBand, BufferedReader sc, String scrArg) throws IOException {
            NameValidator nameValidator = new NameValidator();
            if (Objects.equals(scrArg, "")) {
                System.out.print("Введите значение поля Name: ");
                argument = checkLine(sc);
                newBand.setName(argument);
                if (!nameValidator.validate(argument)) {
                    setName(newBand, sc, scrArg);
                }
            } else {
                argument = scrArg;
                newBand.setName(argument);
                if (!nameValidator.validate(argument)) {
                    argument = checkLine(sc);
                    setName(newBand, sc, scrArg);
                }
            }
        }

        public void setGenre(MusicBand newBand, BufferedReader sc, String scrArg) throws IOException {
            GenreValidator genreValidator = new GenreValidator();
            if (Objects.equals(scrArg, "")) {
                System.out.print("\nВведите значение поля Genre\n(PSYCHEDELIC_ROCK\nPSYCHEDELIC_CLOUD_RAP\nMATH_ROCK\nPOST_ROCK): ");
                argument = checkLine(sc);
                if (genreValidator.validate(argument.toUpperCase())) {
                    MusicGenre mg = MusicGenre.valueOf(argument.toUpperCase());
                    newBand.setGenre(mg);
                } else {
                    setGenre(newBand, sc, scrArg);
                }
            } else {
                argument = scrArg;
                try {
                    MusicGenre mg = MusicGenre.valueOf(argument.toUpperCase());
                    newBand.setGenre(mg);
                } catch (IllegalArgumentException e) {
                    System.out.println("Введено неправильное значение.\n");
                    setGenre(newBand, sc, scrArg);
                }
            }
        }

        public void setX(MusicBand newBand, BufferedReader sc, String scrArg) throws IOException {
            if (Objects.equals(scrArg, "")) {
                System.out.print("\nВведите значение поля Coordinates.x: ");
                argument = checkLine(sc);
                try {
                coordinates.setX(parseDouble(argument));
                } catch (NumberFormatException e) {
                    System.out.println("Введён неправильный тип данных.\n");
                    setX(newBand, sc, scrArg);
                }
            } else {
                argument = scrArg;
                try {
                    coordinates.setX(parseDouble(argument));
                } catch (NumberFormatException e) {
                    System.out.println("Введён неправильный тип данных.\n");
                    setX(newBand, sc, scrArg);
                }
            }
        }

        public void setY(MusicBand newBand, BufferedReader sc, String scrArg) throws IOException {
            if (Objects.equals(scrArg, "")) {
                System.out.print("\nведите значение поля Coordinates.y: ");
                argument = checkLine(sc);
                try {
                    coordinates.setY(parseFloat(argument));
                } catch (NumberFormatException e) {
                    System.out.println("Введён неправильный тип данных.\n");
                    setY(newBand, sc, scrArg);
                }
            } else {
                argument = scrArg;
                try {
                    coordinates.setY(parseFloat(argument));
                } catch (NumberFormatException e) {
                    System.out.println("Введён неправильный тип данных.\n");
                    setY(newBand, sc, scrArg);
                }
            }
        }

        public void setStudio(MusicBand newBand, BufferedReader sc, String scrArg) throws IOException {
            NameValidator nameValidator = new NameValidator();
            if (Objects.equals(scrArg, "")) {
                System.out.print("\nВведите значение поля Studio.name: ");
                argument = sc.readLine();
                studio.setName(argument);
                newBand.setStudio(studio);
                if(!nameValidator.validate(argument)) {
                    setStudio(newBand, sc, scrArg);
                }
            } else {
                argument = scrArg;
                studio.setName(argument);
                newBand.setStudio(studio);
            }
        }

        public void setNOP(MusicBand newBand, BufferedReader sc, String scrArg) throws IOException {
            if (Objects.equals(scrArg, "")) {
                System.out.print("\nВведите значение поля NumberOfParticipants: ");
                argument = checkLine(sc);
                try {
                    if (parseLong(argument) < 0) {
                        System.out.println("Введите значение больше 0.");
                        argument = sc.readLine();
                        setNOP(newBand, sc, scrArg);
                    }
                    newBand.setNOP(parseLong(argument));
                } catch (NumberFormatException e) {
                    System.out.println("Введён неправильный тип данных.\n");
                    setNOP(newBand, sc, scrArg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                argument = scrArg;
                try {
                    if (parseLong(argument) < 0) {
                        System.out.println("Введите значение больше 0.");
                        argument = checkLine(sc);
                        setNOP(newBand, sc, scrArg);
                    }
                    newBand.setNOP(parseLong(argument));
                } catch (NumberFormatException e) {
                    System.out.println("Введён неправильный тип данных.\n");
                    setNOP(newBand, sc, scrArg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void done(ArrayList<MusicBand> list, MusicBand newBand) {
            newBand.setId(list.size());
            newBand.setCoordinates(coordinates);
            newBand.setStudio(studio);
            list.add(newBand);
            System.out.println("\nДобавлен новый объект: " + newBand.getName() + " (ID: " + newBand.getId() + ")");
        }

        public String checkLine(BufferedReader sc) throws IOException {
            argument = sc.readLine();
            if (argument == null) {
                System.exit(1);
            } else {
                return argument;
            }
            return argument;
        }
    }

    public void averageCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];

        int NOPsum = 0;
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                for (MusicBand musicBand : list) {
                    NOPsum += musicBand.getNOP();
                }
                System.out.println("Среднее кол-во участиников: " + (NOPsum / list.size()));
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public void clearCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];

        try {
            if (Command.isCorrectArgs(args, arguments)) {
                ClearOperations operations = new ClearOperations();
                System.out.println("Вы уверены, что хотите очистить коллекцию? Данное действие необратимо. (y/n)");
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                if (operations.confirm(input)) {
                    list.clear();
                }
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static class ClearOperations {
        public boolean confirm(String input) {
            boolean start = false;
            if (Objects.equals(input, "y")) {
                System.out.println("Коллекция очищена.");
                start = true;
            } else if (Objects.equals(input, "n")) {
                System.out.println("Операция отменена.");
            }
            return start;
        }
    }

    public void executeCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = {"file_name"};
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                String script_path = arguments[0];
                File script_file = new File(script_path);
                try {
                    Receiver runner = new Receiver("");
                    runner.setScriptRunning(true);
                    Scanner sc = new Scanner(script_file);
                    if (sc.hasNext()) {
                        do {
                            /*String line  = sc.nextLine();
                            String commandWord = line.toLowerCase().split(" ")[0];
                            Invoker.commands.get(commandWord).getArgs();
                            Integer inputsCount = Invoker.commands.get(commandWord).getArgs().length;
                            if(inputsCount == arguments.length-Invoker.commands.get(commandWord).getArgs().length){*/
                                runner.runCommand(list, sc.nextLine(), "", true);
                            /*} else {
                                System.out.println("Количество аргументов некорректно. Введено: " + arguments.length + ", необходимо: " + inputsCount);
                            }*/
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
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void exitCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                System.exit(0);
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void filterCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[1];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                if (list.size() == 0) {
                    System.out.println("Коллекция пуста.");
                } else {
                    int number = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (Objects.equals(list.get(i).getGenre().toString(), arguments[0].toUpperCase())) {
                            number++;
                            System.out.println("ID: " + list.get(i).getId() + "\nИмя: " + list.get(i).getName() + "\nЖанр: " + list.get(i).getGenre() + "\nX: " + list.get(i).getCoordinates().getX() + "\nY: " + list.get(i).getCoordinates().getY() + "\nСтудия: " + list.get(i).getStudio().getName() + "\nКол-во участников: " + list.get(i).getNOP());
                            System.out.println();
                        }
                    }
                    if (number == 0) {
                        System.out.println("Не найдено элементов с заданным критерием.");
                    }
                }
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public void helpCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                System.out.println("""
                        help : вывести справку по доступным командам
                        info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                        show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                        add {element} : добавить новый элемент в коллекцию
                        update_by_id id {element} : обновить значение элемента коллекции, id которого равен заданному
                        remove_by_id id : удалить элемент из коллекции по его id
                        clear : очистить коллекцию
                        save : сохранить коллекцию в файл
                        execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                        exit : завершить программу (без сохранения в файл)
                        shuffle : перемешать элементы коллекции в случайном порядке
                        reorder : отсортировать коллекцию в порядке, обратном нынешнему
                        history : вывести последние 15 команд (без их аргументов)
                        sort : отсортировать коллекцию в естественном порядке
                        average_of_number_of_participants : вывести среднее значение поля numberOfParticipants для всех элементов коллекции
                        filter_by_genre genre : вывести элементы, значение поля genre которых равно заданному
                        print_field_descending_genre : вывести значения поля genre всех элементов в порядке убывания""");
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void historyCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                ArrayList<String> history = Invoker.getLastCommands();
                for (int i = history.size() - 1; i >= 0; i--) {
                    System.out.println(history.get(i));
                }
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void infoCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                Path filepath = Paths.get(path);
                BasicFileAttributes attr;
                FileTime fileTime;

                System.out.println("Тип коллекции: " + list.getClass().getName());
                System.out.println("Размер коллекции: " + list.size());
                attr = Files.readAttributes(filepath, BasicFileAttributes.class);
                System.out.println("Дата инициализации: " + attr.creationTime());
                System.out.println("Дата изменения: " + attr.lastModifiedTime());
                for (MusicBand band : list) {
                    System.out.println("\t- " + band.getName());
                }
            }
        } catch (InvalidArgsException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public void printGenreCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                MusicGenre[] genres = MusicGenre.values();
                if (list.size() == 0) {
                    System.out.println("Коллекция пуста.");
                } else {
                    for (MusicGenre genre : genres) {
                        for (MusicBand musicBand : list) {
                            if (Objects.equals(musicBand.getGenre(), genre)) {
                                System.out.println("ID: " + musicBand.getId() + "\nИмя: " + musicBand.getName() + "\nЖанр: " + musicBand.getGenre());
                                System.out.println();
                            }
                        }
                    }
                }
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public void removeByIdCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[1];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                if (Integer.parseInt(arguments[0]) > list.size() - 1) {
                    System.out.println("Макимальный ID элемента: " + (list.size() - 1));
                } else {
                    System.out.println("Вы уверены, что хотите удалить элемент? Данное действие необратимо. (y/n)");
                    Scanner sc = new Scanner(System.in);
                    if (new RemoveOperations().confirm(sc.nextLine())) {
                        list.remove(Integer.parseInt(arguments[0]));
                        System.out.println("Из коллекции удалён объект с ID: " + arguments[0]);
                    }
                }
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public static class RemoveOperations {
        public boolean confirm(String input) {
            boolean start = false;
            if (Objects.equals(input, "y")) {
                start = true;
            } else if (Objects.equals(input, "n")) {
                System.out.println("Операция отменена.");
            }
            return start;
        }
    }

    public void reorderCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[1];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                Collections.reverse(list);
                System.out.println("Коллекция отсортирована в обратном порядке.");
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public void saveCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
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
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void showCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                if (list.size() == 0) {
                    System.out.println("Коллекция пуста.");
                } else {
                    for (MusicBand musicBand : list) {
                        System.out.println("ID: " + musicBand.getId() + "\nИмя: " + musicBand.getName() + "\nЖанр: " + musicBand.getGenre() + "\nX: " + musicBand.getCoordinates().getX() + "\nY: " + musicBand.getCoordinates().getY() + "\nСтудия: " + musicBand.getStudio().getName() + "\nКол-во участников: " + musicBand.getNOP());
                        System.out.println();
                    }
                }
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public void shuffleCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                Collections.shuffle(list);
                System.out.println("Коллекция перемешана в случайном порядке.");
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public void sortCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        String[] args = new String[0];
        try {
            if (Command.isCorrectArgs(args, arguments)) {
                for (int k = 0; k < list.size(); k++) {
                    if (k < list.size() - 1) {
                        if (list.get(k + 1).getId() < list.get(k).getId()) {
                            new SortOperations().sorting(list);
                        }
                    }
                }

                System.out.println("Коллекция отсортирована по ID.");
            }
        } catch (InvalidArgsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public static class SortOperations {
        public void sorting(ArrayList<MusicBand> list) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    if (list.get(i).getId() > list.get(i + 1).getId()) {
                        Collections.swap(list, i, i + 1);
                        sorting(list);
                    }
                }
            }
        }
    }
    public void updateByIdCommand(ArrayList<MusicBand> list, String[] arguments, String path, boolean isScript) {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        Integer idToReplace = Integer.parseInt(arguments[0]);
        String[] args = new String[1];
        String[] scrArgs = new String[6];

        MusicBand newBand = new MusicBand();

        AddOperations operations = new AddOperations();
        UpdateOperations updOperations = new UpdateOperations();

        if(Integer.parseInt(arguments[0]) > list.size()-1){
            System.out.println("Максимальный ID: " + (list.size()-1));
        } else {

            try {
                if (!isScript) {
                    if (Command.isCorrectArgs(args, arguments)) {
                        operations.setName(newBand, sc, "");
                        operations.setGenre(newBand, sc, "");
                        operations.setX(newBand, sc, "");
                        operations.setY(newBand, sc, "");
                        operations.setStudio(newBand, sc, "");
                        operations.setNOP(newBand, sc, "");
                        updOperations.done(list, newBand, idToReplace, operations.coordinates, operations.studio);
                    }
                } else {
                    if (arguments.length == scrArgs.length + args.length) {
                        operations.setName(newBand, sc, arguments[1]);
                        operations.setGenre(newBand, sc, arguments[2]);
                        operations.setX(newBand, sc, arguments[3]);
                        operations.setY(newBand, sc, arguments[4]);
                        operations.setStudio(newBand, sc, arguments[5]);
                        operations.setNOP(newBand, sc, arguments[6]);
                        updOperations.done(list, newBand, idToReplace, operations.coordinates, operations.studio);
                    } else {
                        System.out.println("Количество аргументов в скрипте в команде add не совпадает с нужным.");
                    }
                }
            } catch (InvalidArgsException | IOException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
    public static class UpdateOperations{
        public void done(ArrayList<MusicBand> list, MusicBand newBand, Integer idToReplace, Coordinates coordinates, Studio studio){
            newBand.setId(idToReplace);
            newBand.setCoordinates(coordinates);
            newBand.setStudio(studio);
            list.set(idToReplace, newBand);
            System.out.println("Объект заменён на: " + newBand.getName() + " (ID: " + newBand.getId() + ")");
        }
    }
}
