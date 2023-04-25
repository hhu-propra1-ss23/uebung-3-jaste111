package de.hhu.propra.streams;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ConfigParser {
    private static final String HORIZONTAL_RULER = "-".repeat(80);
    record ConfigEntry(String key, String value){}
    public static ConfigEntry parseConfigLine(String line){
        var keyValue = line.split(":");
        return new ConfigEntry(keyValue[0].trim(),keyValue[1].trim());
    }
    public static void addConfigEntry(Map<String,String> map, String line){
        //Das hier zu implementieren könnte helfen...
    }
    public static void main(String[] args) throws IOException {
        List<String> config = Files.readAllLines(Paths.get("my_config.yml")); // liest alle Zeilen der Datei in eine Liste ein
        Map<String,String> configMap = config.stream().collect(
                // TODO: collect implementieren
                null, // Supplier
                null, // Accumulator
                null  // Combiner
            );
        System.out.format("Read config. Result:\n%s\n\n%s\n\n%s", HORIZONTAL_RULER,configMap, HORIZONTAL_RULER);
    }
}
