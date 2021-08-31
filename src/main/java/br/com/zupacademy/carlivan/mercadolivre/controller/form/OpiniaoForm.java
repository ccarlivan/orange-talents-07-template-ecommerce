package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import br.com.zupacademy.carlivan.mercadolivre.model.Opiniao;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class OpiniaoForm {
    @Min(1) @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank @Length(max = 500)
    private String descricao;

    public OpiniaoForm(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Opiniao toOpiniao(Produto produto, Usuario usuario) {
        return new Opiniao(nota, titulo, descricao, produto, usuario);
    }

    @Override
    public String toString() {
        return "OpiniaoForm{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
