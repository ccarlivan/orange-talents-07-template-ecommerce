package br.com.zupacademy.carlivan.mercadolivre.service;

import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.model.Pergunta;
import org.springframework.stereotype.Service;

@Service
public class Email {
    public void send(Pergunta pergunta){
        System.out.println("De: usuario -> " + pergunta.getUsuario().getLogin());
        System.out.println("Para: dono -> " + pergunta.getProduto().getDono().getLogin());
        System.out.println("Titulo: " + pergunta.getTitulo());
    }

    public void sendCompra(Compra compra) {
        System.out.println("De: usuario-> " + compra.getUsuario().getLogin());
        System.out.println("Para: dono-> " + compra.getProduto().getDono());
        System.out.println("Assunto: Quero comprar o seguinte produto -> " + compra.getProduto().getNome());
    }
}
