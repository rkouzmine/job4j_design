package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String YEAH = "да";
    private static final String NOPE = "нет";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> listQuestions = readPhrases();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String phrase;
        boolean isBotSays = true;
        String greetings = """
                Bot: Добро пожаловать в консольный чат!
                Я буду задавать тебе вопросы, а ты будешь отвечать на них 'да' или 'нет'.
                Начнем?""";
        System.out.println(greetings);
        log.add(greetings);

        do {
            String randomQuestion = listQuestions.get(random.nextInt(listQuestions.size()));
            phrase = scanner.nextLine().trim().toLowerCase();
            log.add("User: " + phrase);
            if (OUT.equals(phrase)) {
                break;
            } else if (STOP.equals(phrase)) {
                isBotSays = false;
            } else if (CONTINUE.equals(phrase)) {
                System.out.println("Bot: " + randomQuestion);
                log.add("Bot: " + randomQuestion);
                isBotSays = true;
            } else if (YEAH.equals(phrase) || NOPE.equals(phrase)) {
                if (isBotSays) {
                    System.out.println("Bot: " + randomQuestion);
                    log.add("Bot: " + randomQuestion);
                }
            } else {
                if (isBotSays) {
                    System.out.println("Пожалуйста, отвечайте 'да' или 'нет'.");
                    log.add("Bot: Пожалуйста, отвечайте 'да' или 'нет'.");
                }
            }
        }
        while (!OUT.equals(phrase));
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data\\answers_log.txt",
                "data\\random_question.txt");
        consoleChat.run();
    }
}