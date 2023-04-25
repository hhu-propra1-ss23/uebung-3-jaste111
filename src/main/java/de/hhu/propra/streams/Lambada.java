package de.hhu.propra.streams;

import java.util.stream.IntStream;

public class Lambada {
    public static void main(String[] args) {
        IntStream.range(1, 10)
                .map(i -> i * i)
                .filter(i -> i % 2 == 0)
                .forEach(i -> System.out.println(i));
    }
}
