package de.hhu.propra.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Punkte {
    public static void main(String[] args) throws IOException {
        final List<String> zeilen = Files.readAllLines(Paths.get("punkte.csv"));

        // Ermitteln der Anzahl der Zeilen ohne Stream
        System.out.println(zeilen.size());

        //Namen aller Studis ausgeben
        zeilen
                .stream()
                .skip(1)
                .limit(5)
                .map(Punkte::splitLine)
                .forEach(Punkte::printStudiName);

        // Welche Studis haben mindestens 50 Punkte in der Klausur
        zeilen
                .stream()
                .skip(1)
                .map(Punkte::splitLine)
                .filter(lineParts -> safeParseDouble(lineParts) > 50)
                .forEach(Punkte::printStudiName);
    }

    private static String[] splitLine(String line){
        return line.split(",");
    }

    private static void printStudiName(String[] line) {
        System.out.println(line[1]);
    }

    private static double safeParseDouble(String[] line){
        if(line[0].equals("")){
            return 0;
        }
       return Double.parseDouble(line[0]) ;
    }
}
