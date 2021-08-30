package br.com.zupacademy.carlivan.mercadolivre.model;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Pergunta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime dataCriacao;

    public Pergunta(String titulo, Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
        this.dataCriacao = LocalDateTime.now();
    }

    public Pergunta() {
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "titulo='" + titulo + '\'' +
                ", produto=" + produto +
                ", usuario=" + usuario +
                ", dataCriacao=" + dataCriacao +
                '}';
    }

    public void sendEmail() {
        System.out.println("De: Usuario -> " + usuario.getLogin());
        System.out.println("Para: Dono -> " + produto.getDono().getLogin());
        System.out.println("Pergunta -> "+ titulo);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
