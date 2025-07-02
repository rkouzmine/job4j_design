package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    public static String expectedAnswer(int number) {
        String result = String.valueOf(number);
        if (number % 3 == 0 && number % 5 == 0) {
            result = "FizzBuzz";
        } else if (number % 3 == 0) {
            result = "Fizz";
        } else if (number % 5 == 0) {
            result = "Buzz";
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz");
        var startAt = 1;
        var input = new Scanner(System.in);

        while (startAt <= 100) {
            String result = expectedAnswer(startAt);
            if (startAt % 2 == 0) {
                System.out.print("Игрок: ");
                var answer = input.nextLine();
                if (!result.equals(answer)) {
                    System.out.println("Ошибка. Начинай снова");
                    startAt = 1;
                    continue;
                }
            } else {
                System.out.println("Компьютер: " + result);
            }
            startAt++;
        }
        System.out.println("Игра пройдена");
    }
}
