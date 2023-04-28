package de.hhu.propra.streams;

import java.util.stream.IntStream;

public class Lambada {
    public static void main(String[] args) {
        var integers = IntStream.range(1, 10)
                .map(i -> i * i)
                .filter(i -> i % 2 == 0)
                .boxed()
                .toList();

        System.out.println(integers);
    }
}
