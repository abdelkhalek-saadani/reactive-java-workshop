package io.javabrains.reactiveworkshop.solutions;

import io.javabrains.reactiveworkshop.ReactiveSources;

import java.io.IOException;
import java.util.List;

public class Exercise3 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Get all numbers in the ReactiveSources.intNumbersFlux stream
        // into a List and print the list and its size
        List<Integer> numbers = ReactiveSources.intNumbersFlux()
                .doOnNext(e -> System.out.println("element " + e + " arrived"))
                .toStream().toList();

        System.out.println(numbers);
        System.out.println("Its size is " + numbers.size());


        System.out.println("Press a key to end");
        System.in.read();
    }

}
