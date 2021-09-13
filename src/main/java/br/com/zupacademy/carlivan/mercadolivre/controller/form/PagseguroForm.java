package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.model.Transacao;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.RetornoGatewayPagamento;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.StatusPagseguro;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagseguroForm implements RetornoGatewayPagamento {
    @NotBlank
    private String idTransacao;
    @NotNull @Enumerated(EnumType.STRING)
    private StatusPagseguro status;

    public PagseguroForm(String idTransacao, StatusPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagseguroForm{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
