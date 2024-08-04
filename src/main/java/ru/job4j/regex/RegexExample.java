package ru.job4j.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        System.out.println("Проверка на полное совпадение шаблона с помощью matches()");
        Pattern pattern1 = Pattern.compile("Job4j");

        String text1 = "Job4j";
        Matcher matcher1 = pattern1.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);
        String text2 = "JOB4J";
        Matcher matcher2 = pattern1.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);
        System.out.println();

        System.out.println("Если регистр букв при поиске не принципиален используем в compile() параметр CASE_INSENSITIVE");
        Pattern pattern2 = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);

        String text3 = "Job4j";
        Matcher matcher3 = pattern2.matcher(text3);
        boolean isPresent3 = matcher3.matches();
        System.out.println(isPresent3);
        String text4 = "JOB4J";
        Matcher matcher4 = pattern2.matcher(text4);
        boolean isPresent4 = matcher4.matches();
        System.out.println(isPresent4);
        System.out.println();

        System.out.println("Проверка шаблона на его присутствие в тексте с помощью find()");
        String text5 = "Я учусь на курсе Job4j";
        Matcher matcher5 = pattern1.matcher(text5);
        boolean isPresent5 = matcher5.find();
        System.out.println(isPresent5);
        System.out.println();

        System.out.println("Метод find() ищет ближайшее совпадение. Его можно применять многократно. "
                + "Каждый вызов метода find() начинается с места, где закончился его предыдущий вызов. "
                + "Чтобы найти многократные вхождения шаблона в коде, нужно использовать find() в цикле:");
        String text6 = "Job4j and Job4j and Job4j";
        Matcher matcher6 = pattern1.matcher(text6);
        while (matcher6.find()) {
            System.out.println("Найдено совпадение");
        }
        System.out.println();

        System.out.println("Получить найденное совпадение в виде строки можно с помощью метода group(). "
                + "Этот метод выводит ту часть текста, которая совпадает с шаблоном регулярного выражения. "
                + "В данном случае это \"Job4j\".");
        String text7 = "Job4j1 and Job4j2 and Job4j3";
        Matcher matcher7 = pattern1.matcher(text7);
        while (matcher7.find()) {
            System.out.println("Найдено совпадение: " + matcher7.group());
        }
        System.out.println();

        System.out.println("Метод start() - получает индекс, в котором находится первый символ искомой последовательности символов.\n"
                + "Метод end() - получает индекс, следующий за последним символом искомой последовательности символов.");
        String text8 = "Job4j1 and Job4j2 and Job4j3";
        Matcher matcher8 = pattern1.matcher(text8);
        while (matcher8.find()) {
           System.out.printf("Найдено совпадение. iStart: %d iEnd %d%n", matcher8.start(), matcher8.end());
        }
        System.out.println();

        System.out.println("Найденные совпадения можно заменить другой строкой с помощью метода replaceAll(), "
                + "который применяется к сопоставителю matcher.");
        Pattern pattern3 = Pattern.compile("123");

        String text9 = "123-1 and 123+2 and 123=3";
        Matcher matcher9 = pattern3.matcher(text9);
        String result = matcher9.replaceAll("Job4j");
        System.out.println(result);
        System.out.println();

        System.out.println("String.split()");
        String string = "123+456:2/#789";
        String[] strings = string.split("\\D+");
        System.out.println(Arrays.toString(strings));
        System.out.println();

        Pattern pattern4 = Pattern.compile("\\b\\d{2}\\.\\d{2}\\.\\d{4}\\b");
        String text10 = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher10 = pattern4.matcher(text10);
        while (matcher10.find()) {
            System.out.println("Найдено совпадение: " + matcher10.group());
        }
        System.out.println();

        Pattern pattern5 = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String email = "rkouzmine@ya.ru example@mail.ru ggwp@example.com";
        Matcher matcher11 = pattern5.matcher(email);
        while (matcher11.find()) {
            System.out.println("Найдено совпадение: " + matcher11.group());
        }
    }
}
