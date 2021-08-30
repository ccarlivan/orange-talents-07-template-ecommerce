package br.com.zupacademy.carlivan.mercadolivre.service;

import br.com.zupacademy.carlivan.mercadolivre.model.Pergunta;
import org.springframework.stereotype.Service;

@Service
public class Email {
    public void send(Pergunta pergunta){
        System.out.println("De: usuario -> " + pergunta.getUsuario().getLogin());
        System.out.println("Para: dono -> " + pergunta.getProduto().getDono().getLogin());
        System.out.println("Titulo: " + pergunta.getTitulo());
    }
}
