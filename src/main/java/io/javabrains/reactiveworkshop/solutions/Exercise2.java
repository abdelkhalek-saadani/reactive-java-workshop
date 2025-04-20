package io.javabrains.reactiveworkshop.solutions;

import io.javabrains.reactiveworkshop.ReactiveSources;
import io.javabrains.reactiveworkshop.User;

import java.io.IOException;

public class Exercise2 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux() and ReactiveSources.userFlux()

        // Print all numbers in the ReactiveSources.intNumbersFlux stream
        System.out.println("Printing all numbers from the Flux");
        ReactiveSources.intNumbersFlux()
                .subscribe(System.out::println);


        // Print all users in the ReactiveSources.userFlux stream
        System.out.println("Printing all users");
        ReactiveSources.userFlux()
                .map(User::getFirstName)
                .subscribe(System.out::println);


        System.out.println("Press a key to end");
        System.in.read();
    }

}
