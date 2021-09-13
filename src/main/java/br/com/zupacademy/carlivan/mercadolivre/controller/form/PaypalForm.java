package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.model.Transacao;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.RetornoGatewayPagamento;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.StatusTransacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PaypalForm implements RetornoGatewayPagamento {
    @Min(0) @Max(1)
    private int status;
    @NotBlank
    private String idTransacao;

    public PaypalForm(int status, String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toTransacao(Compra compra){
        return new Transacao(this.status == 0 ? StatusTransacao.erro:StatusTransacao.sucesso,idTransacao,compra);
    }

    public int getStatus() {
        return status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    @Override
    public String toString() {
        return "PaypalForm{" +
                "status=" + status +
                ", idTransacao='" + idTransacao + '\'' +
                '}';
    }
}
