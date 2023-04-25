package de.hhu.propra.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Punkte {
    public static void main(String[] args) throws IOException {
        final List<String> zeilen = Files.readAllLines(Paths.get("punkte.csv"));

    }
}
