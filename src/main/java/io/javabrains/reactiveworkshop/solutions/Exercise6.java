package io.javabrains.reactiveworkshop.solutions;


import io.javabrains.reactiveworkshop.ReactiveSources;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Exercise6 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.unresponsiveFlux() and ReactiveSources.unresponsiveMono()

        // Get the value from the Mono into a String variable but give up after 5 seconds
        String str = ReactiveSources.unresponsiveMono().block(Duration.ofSeconds(5));

        // Get the value from unresponsiveFlux into a String list but give up after 5 seconds
        List<String> list = ReactiveSources.unresponsiveFlux().collectList().block(Duration.ofSeconds(5));
        // NOTE: The difference between toStream().toList() and collectList.block() is that the collectList()
        // is actually transforming a Flux of T into a Mono of List<T>


        System.out.println("Press a key to end");
        System.in.read();
    }

}
