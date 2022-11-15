package com.example.servingwebcontent.service;

public interface MailSender {
    public void send(String emailTo, String subject, String massage);
}
