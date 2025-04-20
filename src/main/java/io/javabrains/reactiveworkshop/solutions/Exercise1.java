package io.javabrains.reactiveworkshop.solutions;

import com.codepoetics.protonpack.StreamUtils;
import io.javabrains.reactiveworkshop.StreamSources;
import io.javabrains.reactiveworkshop.User;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        System.out.println("Print all numbers in the intNumbersStream stream");
        StreamSources.intNumbersStream().forEach(System.out::println);

        // Print numbers from intNumbersStream that are less than 5
        System.out.println("Print numbers from intNumbersStream that are less than 5");
        Predicate<Integer> isLowerThanFive = number -> number < 5;
        StreamSources.intNumbersStream().filter(isLowerThanFive).forEach(System.out::println);

        // Print the second and third numbers in intNumbersStream that's greater than 5
        /*
         * This is a way
         */

        System.out.println("Print the second and third numbers in intNumbersStream that's greater than 5");
        Predicate<Integer> isGreaterThanFive = number -> number > 5;
        StreamSources.intNumbersStream()
                .filter(isGreaterThanFive)
                .skip(1)
                .limit(2)
                .forEach(System.out::println);

        /*
         * This is another way
         */

        System.out.println("Now We'll do it using StreamUtils.zipWithIndex");
        StreamUtils.zipWithIndex(StreamSources.intNumbersStream().filter(isGreaterThanFive))
                .filter(e -> e.getIndex() == 1 || e.getIndex() == 2)
                .map(e -> e.getValue())
                .forEach(System.out::println);

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        /*
         * This is a way
         */
        System.out.println("Print the first number in intNumbersStream that's greater than 5");
        StreamSources.intNumbersStream()
                .filter(isGreaterThanFive)
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("-1"));

        /*
         * This is another way
         */
        System.out.println("The same but differently");
        int value = StreamSources.intNumbersStream()
                .filter(isGreaterThanFive)
                .findFirst()
                .orElse(-1);
        System.out.println(value);

        // Print first names of all users in userStream
        /*
         * This is a way
         */
        System.out.println("Print first names of all users in userStream");
        Consumer<User> printFirstName = e -> System.out.println(e.getFirstName());
        StreamSources.userStream().forEach(printFirstName);

        /*
         * Another way
         */
        System.out.println("Another way");
        StreamSources.userStream()
                .map(User::getFirstName)
                .forEach(System.out::println);

        // Print first names in userStream for users that have IDs from number stream
        /*
         * This is a way
         */
        System.out.println("Print first names in userStream for users that have IDs from number stream");
        StreamSources.intNumbersStream()
                .forEach(
                        (n) -> {
                            StreamSources.userStream().filter(user -> user.getId() == n).forEach(printFirstName);
                        });

        /*
         * This is another way
         */
        System.out.println("Another way");
        StreamSources.userStream()
                .filter(
                        (user) -> StreamSources.intNumbersStream().anyMatch(number -> user.getId() == number)
                )
                .map(User::getFirstName)
                .forEach(System.out::println);
        /*
         * This another way too
         */
        System.out.println("Differently");
        StreamSources.intNumbersStream()
                .flatMap(number -> StreamSources.userStream().filter(user -> user.getId() == number))
                .map(User::getFirstName)
                .forEach(System.out::println);

    }

}
