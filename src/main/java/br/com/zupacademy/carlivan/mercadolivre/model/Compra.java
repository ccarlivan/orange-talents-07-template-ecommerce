package br.com.zupacademy.carlivan.mercadolivre.model;

import br.com.zupacademy.carlivan.mercadolivre.pagamento.Gateway;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.RetornoGatewayPagamento;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gateway gateway;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

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

    public Gateway getGateway() {
        return gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Set<Transacao> getTransacoes() {
        return transacoes;
    }

    public Status getStatus() {
        return status;
    }

    public void adicionaTransacao(RetornoGatewayPagamento form) {
        Transacao transacao = form.toTransacao(this);
        if(this.transacoes.contains(transacao)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa transação já existe!!");
        }

        if(!transacoesConcluidasComSucesso().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento já concluído!!");
        }
        this.transacoes.add(transacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComsucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
        return transacoesConcluidasComsucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}
