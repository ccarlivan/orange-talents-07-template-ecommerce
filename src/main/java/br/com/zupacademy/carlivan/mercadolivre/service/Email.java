package br.com.zupacademy.carlivan.mercadolivre.service;

import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.model.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Email {
    @Autowired
    private Mailer mailer;

    public void send(Pergunta pergunta){
        mailer.send("<html>///</html>","pergunta",pergunta.getUsuario().getLogin(),
                "newanswer@ml.com.br",pergunta.getProduto().getDono().getLogin());
        System.out.println("De: usuario -> " + pergunta.getUsuario().getLogin());
        System.out.println("Para: dono -> " + pergunta.getProduto().getDono().getLogin());
        System.out.println("Titulo: " + pergunta.getTitulo());
    }

    public void sendCompra(Compra compra) {
        mailer.send("<html>///</html>","Produto",compra.getUsuario().getLogin(),
                "newanswer@ml.com.br",compra.getProduto().getDono().getLogin());
        System.out.println("De: usuario-> " + compra.getUsuario().getLogin());
        System.out.println("Para: dono-> " + compra.getProduto().getDono());
        System.out.println("Assunto: Quero comprar o seguinte produto -> " + compra.getProduto().getNome());
    }

    public void sendSucesso(Compra compra) {
        mailer.send("<html>///</html>","Sucesso",compra.getProduto().getDono().getLogin(),
                "newanswer@ml.com.br",compra.getUsuario().getLogin());
    }

    public void sendFail(Compra compra) {
        String gateway = compra.getGateway().toString().toLowerCase();
        String url = "http://localhost:8080/retorno-"+gateway+"/"+compra.getId();

        mailer.send("<html>/" +url+ "/</html>","Falha na Compra",compra.getProduto().getDono().getLogin(),
                "newanswer@ml.com.br",compra.getUsuario().getLogin());
    }
}
