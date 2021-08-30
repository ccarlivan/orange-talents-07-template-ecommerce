package br.com.zupacademy.carlivan.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nota;
    private String titulo;
    private String descricao;
    @ManyToOne @NotNull
    private Produto produto;
    @ManyToOne @NotNull
    private Usuario usuario;

    public Opiniao(int nota, String titulo, String descricao, Produto produto, Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Opiniao() {
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

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produto=" + produto +
                ", usuario=" + usuario +
                '}';
    }
}
