package data;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import parameters.Coordinates;
import parameters.MusicBand;
import parameters.MusicGenre;
import parameters.Studio;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Long.parseLong;

/**
 * This class makes conversion between MusicBand instances and XML structure.
 */

public class Parser {

    public static ArrayList<MusicBand> list = new ArrayList<>();

    /**
     * Static method used for conversation XML structure into MusicBand instances.
     *
     * @param input - path of XML document
     */
    public static ArrayList<MusicBand> serialize(String input) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        InputSource isource = new InputSource();
        InputStream istream = new BufferedInputStream(new FileInputStream(input));
        isource.setByteStream(istream);

        XMLHandler handler = new XMLHandler();
        parser.parse(isource, handler);
        return list;
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("MusicBand")) {
                MusicBand band = new MusicBand();
                Coordinates coordinates = new Coordinates();
                Studio studio = new Studio();
                band.setId(list.size());
                band.setName(attributes.getValue("name"));
                coordinates.setX(Double.parseDouble(attributes.getValue("x")));
                coordinates.setY(Float.parseFloat(attributes.getValue("y")));
                band.setCoordinates(coordinates);
                studio.setName(attributes.getValue("studio"));
                band.setStudio(studio);
                band.setGenre(MusicGenre.valueOf(attributes.getValue("genre")));
                band.setNOP(parseLong(attributes.getValue("number_of_participants")));
                list.add(band);
            }
        }
    }
}