package io.javabrains.reactiveworkshop.solutions;

import io.javabrains.reactiveworkshop.ReactiveSources;

import java.io.IOException;

public class Exercise4 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        System.out.println("Print the value from intNumberMono when it emits");
        ReactiveSources.intNumberMono().subscribe(System.out::println);

        // Get the value from the Mono into an integer variable
        System.out.println("Get the value from the Mono into an integer variable");
        // A way
        Integer number1 = ReactiveSources.intNumberMono().block();

        // Another way
        int number = ReactiveSources.intNumberMono().blockOptional().orElse(-1);


        System.out.println(number);
        System.out.println(number1);
        
    }
}
