package io.javabrains.reactiveworkshop.solutions;

import io.javabrains.reactiveworkshop.ReactiveSources;
import reactor.core.publisher.Flux;

import java.io.IOException;

public class Exercise7 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Print all values from intNumbersFlux that's greater than 5
        ReactiveSources.intNumbersFlux()
                .filter(e -> e > 5)
                .log()  // Useful operator for logging
                .subscribe(System.out::println);


        // Print 10 times each value from intNumbersFlux that's greater than 5
        ReactiveSources.intNumbersFlux()
                .filter(e -> e > 5)
                .map(e -> 10 * e)
                .subscribe(System.out::println);

        // Print 10 times each value from intNumbersFlux for the first 3 numbers emitted that's greater than 5
        ReactiveSources.intNumbersFlux()
                .filter(e -> e > 5)
                .take(3)
                .map(e -> 10 * e)
                .subscribe(System.out::println);

        // Print each value from intNumbersFlux that's greater than 20. Print -1 if no elements are found
        ReactiveSources.intNumbersFlux()
                .filter(e -> e > 20)
                .defaultIfEmpty(-1)
                .subscribe(System.out::println);


        // Switch ints from intNumbersFlux to the right user from userFlux
        ReactiveSources.intNumbersFlux()
                .flatMap(e -> ReactiveSources.userFlux().filter(user -> user.getId() == e))
                .subscribe(System.out::println);

        // Print only distinct numbers from intNumbersFluxWithRepeat
        ReactiveSources.intNumbersFluxWithRepeat()
                .distinct()
                .subscribe(System.out::println);


        // Print from intNumbersFluxWithRepeat excluding immediately repeating numbers
        Flux<Integer> myFlux = ReactiveSources.intNumbersFluxWithRepeat()
                .distinctUntilChanged();

        myFlux.subscribe(System.out::println);

        myFlux.subscribe(e -> System.out.printf("Printing values from the second subscriber %d\n", e));


        System.out.println("Press a key to end");
        System.in.read();
    }

}
