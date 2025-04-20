package io.javabrains.reactiveworkshop.solutions;

import io.javabrains.reactiveworkshop.ReactiveSources;

import java.io.IOException;

public class Exercise9 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Print size of intNumbersFlux after the last item returns
        ReactiveSources.intNumbersFlux()
                .log()
                .count()
                .subscribe(System.out::println);

        // Collect all items of intNumbersFlux into a single list and print it
        ReactiveSources.intNumbersFlux()
                .collectList()
                .subscribe(System.out::println);

        // Transform to a sequence of sums of adjacent two numbers
        ReactiveSources.intNumbersFlux()
                .buffer(2)
                .map(list -> {
                    if (list.size() == 2) return list.get(0) + list.get(1);
                    else return list.get(0);
                })
                .subscribe(System.out::println);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
