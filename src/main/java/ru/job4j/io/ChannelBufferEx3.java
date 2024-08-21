package ru.job4j.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelBufferEx3 {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("data/testChannelBuffer.txt", "r");
             FileChannel channel = file.getChannel();) {

            ByteBuffer buffer = ByteBuffer.allocate(8);
            channel.read(buffer);
            buffer.flip();
            for (int i = 0; i < 4; i++) {
                System.out.println((char) buffer.get());
            }
            buffer.rewind();
            System.out.println();
            System.out.println((char) buffer.get());
            System.out.println((char) buffer.get());
            System.out.println((char) buffer.get());
            System.out.println();
            buffer.compact();
            channel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            System.out.println("Позиция 0");
            buffer.clear();
            channel.read(buffer);
            System.out.println((char) buffer.get());
            buffer.mark();
            System.out.println((char) buffer.get() + "!");
            System.out.println((char) buffer.get());
            buffer.reset();
            System.out.println();
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
