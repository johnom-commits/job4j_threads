package ru.job4j.concurrent;

public class ClientMailing {

    public static void main(String[] args) {
        EmailNotification email = new EmailNotification();
        email.emailTo(User.of("Billi"));
        email.close();
    }
}
