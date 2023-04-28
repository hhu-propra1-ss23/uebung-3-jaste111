package de.hhu.propra.streams;

import java.math.BigInteger;
import java.util.List;

public class Reduce {

    public static void main(String[] args) {
        List<Integer> integerList = List.of(30, 36, 42, 48);

        BigInteger gcd = integerList
                .stream()
                .map(BigInteger::valueOf)
                .reduce(BigInteger.valueOf(0), BigInteger::gcd);

        System.out.println(gcd);


    }
}
