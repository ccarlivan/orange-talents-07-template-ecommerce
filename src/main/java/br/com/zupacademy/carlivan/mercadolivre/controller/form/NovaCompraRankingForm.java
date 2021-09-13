package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import javax.validation.constraints.NotNull;

public class NovaCompraRankingForm {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public NovaCompraRankingForm(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor= idVendedor;
    }

    @Override
    public String toString() {
        return "NovaCompraRankingForm{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
