package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import br.com.zupacademy.carlivan.mercadolivre.model.Pergunta;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaForm {
    @NotBlank
    private String titulo;

    public PerguntaForm(String titulo) {
        this.titulo = titulo;
    }

    public PerguntaForm() {
    }

    public Pergunta toPergunta(Produto produto, Usuario usuario){
        return new Pergunta(titulo, produto, usuario);
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "PerguntaForm{" +
                "titulo='" + titulo + '\'' +
                '}';
    }
}
