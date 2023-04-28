package de.hhu.propra.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Studis {
    public static void main(String[] args) throws IOException {
        List<String> studis = Files.readAllLines(Paths.get("studis.txt"));

        //Gesamtzahl der Studis, welche die Zulassung erhalten haben (Punkte > 300)
        long gesamtzahlStudisZulassung = studis
                .stream()
                .map(Studis::splitLine)
                .filter(Studis::istZugelassen)
                .count();
        System.out.println("Gesamtzahl der Studis, die die Zulassung haben: " + gesamtzahlStudisZulassung);

        //Mittelwert aller Punkte, welche die Zulassung haben

        DoubleSummaryStatistics doubleSummaryStatistics = studis
                .stream()
                .map(Studis::splitLine)
                .filter(Studis::istZugelassen)
                .mapToDouble(Studis::parseKlausurPunkte)
                .summaryStatistics();

        System.out.println("Mittelwert aller Punkte der Studis ,die  die Zulassung haben: " + doubleSummaryStatistics.getAverage());

        //Anzahl der Personen, die Informatik studieren
        long anzahlInformatikStudis = studis
                .stream()
                .map(Studis::splitLine)
                .filter(Studis::filterInformatikStudis)
                .count();
        System.out.println("Anzahl der Informatik Studis: " + anzahlInformatikStudis);

        //Namen aller Informatiker:innen Filter Lösung
        studis
                .stream()
                .map(Studis::splitLine)
                .filter(Studis::filterInformatikStudis)
                .forEach(Studis::printStudiName);

        // Namen aller Informatiker:innen Grouping By Lösung
        Map<String, List<String>> studisNachFaechern = studis
                .stream()
                .collect(Collectors.groupingBy(line -> line.split(" ")[2]));

        studisNachFaechern
                .get("Informatik")
                .stream()
                .map(Studis::splitLine)
                .forEach(Studis::printStudiName);

        // Sortierte Liste aller Studiengänge
        studis
                .stream()
                .map(Studis::splitLine)
                .map(Studis::getStudiengang)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        // Häufigkeiten jedes Studiengangs
        Map<String, Long> haeufigkeitenStudiengaenge = studis
                .stream()
                .map(Studis::splitLine)
                .collect(Collectors.groupingBy(Studis::getStudiengang, Collectors.counting()));

        for(var studiengang: haeufigkeitenStudiengaenge.entrySet()){
            System.out.printf("%4d %s%n", studiengang.getValue(), studiengang.getKey());
            System.out.println();
        }

        //Häufigkeiten jedes Studiengangs sortiert
        studisNachFaechern.keySet()
                .stream()
                .sorted(Comparator.comparingInt(studiengang -> studisNachFaechern.get(studiengang).size()))
                .forEach(studiengang -> System.out.println(studisNachFaechern.get(studiengang).size() + " " + studiengang));
    }


    private static String getStudiengang(String[] lineParts){
        return lineParts[2];
    }

    private static double parseKlausurPunkte(String[] lineParts) {
        return Double.parseDouble(lineParts[3]);
    }

    private static boolean istZugelassen(String[] lineParts) {
        return parseKlausurPunkte(lineParts) > 300;
    }

    private static String[] splitLine(String line){
        return line.split(" ");
    }

    private static void printStudiName(String[] lineParts){
        System.out.println(lineParts[0] + " " + lineParts[1]);
    }

    private static boolean filterInformatikStudis(String[] lineParts){
        return lineParts[2].equals("Informatik");
    }

}
