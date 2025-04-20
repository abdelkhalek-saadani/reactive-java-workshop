package io.javabrains.reactiveworkshop.solutions;

import io.javabrains.reactiveworkshop.ReactiveSources;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumberMono().subscribe(
                System.out::println,
                (e) -> System.out.println(e.getMessage()),
                () -> System.out.println("Done")
        );

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>());

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribe Happened");
        request(1);
    }

    public void hookOnNext(T value) {
        System.out.println("Item received with value " + value);
        request(1);
    }

    public void hookOnComplete() {
        System.out.println("Complete Happened");
    }

}