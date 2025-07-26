package ru.job4j.ood.ocp.messagesender;

public class TelegramSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Отправка в Telegram: " + message);
    }
}
