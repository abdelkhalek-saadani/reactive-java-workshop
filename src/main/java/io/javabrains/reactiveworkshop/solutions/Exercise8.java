package io.javabrains.reactiveworkshop.solutions;

import io.javabrains.reactiveworkshop.ReactiveSources;
import reactor.core.publisher.Flux;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens
        ReactiveSources.intNumbersFluxWithException()
                .subscribe(
                        System.out::println,
                        e -> System.out.println(e.getMessage())
                );

        ReactiveSources.intNumbersFluxWithException()
                .doOnError(e -> System.out.println(e.getMessage())) // Doesn't swallow the error; it does a side effect, so the execution will stop because of the error
                .subscribe(
                        System.out::println
                );

        // Print values from intNumbersFluxWithException and continue on errors
        ReactiveSources.intNumbersFluxWithException()
                .onErrorContinue(
                        (e1, e2) -> {
                            System.out.println(e1.getMessage());
                            System.out.println(e2);
                        })
                .subscribe(System.out::println);

        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        ReactiveSources.intNumbersFluxWithException()
                .onErrorResume(e -> Flux.just(-1, -2))
                .subscribe(System.out::println);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
