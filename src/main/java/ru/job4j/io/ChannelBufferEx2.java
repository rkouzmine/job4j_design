package ru.job4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ChannelBufferEx2 {
    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("data/random.txt", "rw");
            randomAccess.writeInt(100);
            randomAccess.writeChar('A');
            randomAccess.writeBoolean(true);

            /* Перемещаем указатель в начало с помощью метода seek(0); */
            randomAccess.seek(0);
            System.out.println(randomAccess.readInt());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());

            /* Читаем с символа A */
            randomAccess.seek(4);
            System.out.println(randomAccess.readChar());

            /* Проверяем положение указателя */
            System.out.println(randomAccess.getFilePointer());

            /* Переписывам символ A на символ B */
            randomAccess.seek(4);
            randomAccess.writeChar('B');
            randomAccess.seek(4);
            System.out.println(randomAccess.readChar());

            /* Выставляем указатель в конец и дописываем новые данные */
            randomAccess.seek(randomAccess.length());
            System.out.println("Положение указателя после boolean: " + randomAccess.getFilePointer());
            randomAccess.writeDouble(3.01);
            randomAccess.seek(7);
            System.out.println(randomAccess.readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}