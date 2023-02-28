import commands.CommandRunner;
import data.Parser;
import exceptions.InvalidArgsException;
import org.xml.sax.SAXException;
import parameters.MusicBand;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, InvalidArgsException {
        String dataPath = "src/data/" + args[0];

        ArrayList<MusicBand> collection = new ArrayList<>();

        if (dataPath == null) {
            System.out.println("Не указан путь.");
        } else {
            try {
                collection = Parser.serialize(dataPath);
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print(">>> ");
                String line = scanner.readLine();

                if (line == null) {
                    System.exit(1);
                } else {
                    new CommandRunner(dataPath).runCommand(collection, line, dataPath);
                }

            }

        }
    }
}