package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import javax.validation.constraints.NotNull;

public class NovaCompraNotaForm {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NovaCompraNotaForm(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NovaCompraNotaForm{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
