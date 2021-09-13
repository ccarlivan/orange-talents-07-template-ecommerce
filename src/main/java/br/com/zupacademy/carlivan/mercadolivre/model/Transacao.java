package br.com.zupacademy.carlivan.mercadolivre.model;

import br.com.zupacademy.carlivan.mercadolivre.pagamento.StatusTransacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Enumerated(EnumType.STRING)
    private StatusTransacao status;
    private String idTransacao;
    @NotNull
    @ManyToOne
    private Compra compra;
    private LocalDateTime instante;

    public Transacao(StatusTransacao status, @NotBlank String idTransacao, Compra compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.compra = compra;
        this.instante = LocalDateTime.now();
    }

    public Transacao() {
    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.sucesso);
    }

    public Long getId() {
        return id;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public Compra getCompra() {
        return compra;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(getIdTransacao(), transacao.getIdTransacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdTransacao());
    }
}
