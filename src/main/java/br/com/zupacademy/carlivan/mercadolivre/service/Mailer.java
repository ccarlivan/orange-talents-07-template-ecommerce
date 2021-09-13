package br.com.zupacademy.carlivan.mercadolivre.service;

public interface Mailer {

    void send(String body, String subject, String nameFrom, String from, String to);
}
