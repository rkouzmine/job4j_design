package ru.job4j.ood.ocp.messagesender;

public class WhatsAppSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Отправка в WhatsApp: " + message);
    }
}
