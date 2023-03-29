import commands.Invoker;
import commands.Receiver;
import exceptions.InvalidArgsException;
import logic.Loader;
import org.xml.sax.SAXException;
import parameters.MusicBand;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Client {
    public String dataPath;
    public Invoker invoker;

    public Receiver receiver;

    BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    public Client(String[] args) throws IOException, InvalidArgsException {
        try {
            this.dataPath = args[0];
        } catch (Exception e){
            System.out.println("Не введён путь к файлу.");
            System.exit(1);
        }
        this.receiver = new Receiver(this.dataPath);
        this.invoker = new Invoker(receiver);

    }

    public void start() throws IOException, InvalidArgsException {
        System.out.println("Введите help для просмотра доступных команд.");
        while (true) {
            System.out.print(">>> ");
            String line = this.scanner.readLine();
            if (line == null) {
                System.exit(1);
            } else receiver.runCommand(line, dataPath, false);
        }
    }
}


