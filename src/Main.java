import commands.Receiver;
import data.Parser;
import exceptions.InvalidArgsException;
import logic.Loader;
import org.xml.sax.SAXException;
import parameters.MusicBand;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Башаримов Евгений Александрович
 */

public class Main {
    public static void main(String[] args) throws IOException, InvalidArgsException {
        Client client = new Client(args);
        client.start();
    }
}