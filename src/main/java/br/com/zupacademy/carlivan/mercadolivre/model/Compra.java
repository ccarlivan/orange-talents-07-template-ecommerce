package br.com.zupacademy.carlivan.mercadolivre.model;

import br.com.zupacademy.carlivan.mercadolivre.pagamento.Gateway;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Compra {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Produto produto;
    @Positive
    private int quantidade;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @Enumerated
    @NotNull
    private Gateway gateway;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Compra(Produto produto, int quantidade, Usuario usuario,Gateway gateway, BigDecimal total) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.gateway = gateway;
        this.total = total;
        this.status = Status.INICIADA;
    }

    public Compra() {
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", usuario=" + usuario +
                ", gateway=" + gateway +
                ", total=" + total +
                '}';
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
