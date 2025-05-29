package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    public static final String SEPARATOR = System.lineSeparator();
    private List<Comment> comments = new ArrayList<>();
    public static final int COUNT = 50;
    private List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;


    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < COUNT; i++) {
            StringBuilder text = new StringBuilder();
            text.append(phrases.get(random.nextInt(phrases.size()))).append(SEPARATOR)
                    .append(phrases.get(random.nextInt(phrases.size()))).append(SEPARATOR)
                    .append(phrases.get(random.nextInt(phrases.size())));
            comments.add(new Comment(text.toString(), userGenerator.randomUser()));
        }
    }
}