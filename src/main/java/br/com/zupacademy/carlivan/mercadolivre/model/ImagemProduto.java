package br.com.zupacademy.carlivan.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class ImagemProduto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    private Produto produto;
    @NotBlank
    private String link;

    public ImagemProduto(Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }

    public ImagemProduto() {
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }
}
