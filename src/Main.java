import commands.CommandRunner;
import data.Parser;
import exceptions.InvalidArgsException;
import org.xml.sax.SAXException;
import parameters.MusicBand;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Башаримов Евгений Александрович
 */

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, InvalidArgsException {
        String dataPath = "src/data/" + args[0];

        ArrayList<MusicBand> collection = new ArrayList<>();

            try {
                collection = Parser.serialize(dataPath);
            } catch (ParserConfigurationException e) {
                System.out.print(e.getMessage() + "\n");
            } catch (SAXException e) {
                System.out.print(e.getMessage() + "\n");
            } catch (IOException e) {
                System.out.print(e.getMessage() + "\n");
            }

            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print(">>> ");
                String line = scanner.readLine();

                if (line == null) {
                    System.exit(1);
                } else new CommandRunner(dataPath).runCommand(collection, line, dataPath, false);
            }
    }
}