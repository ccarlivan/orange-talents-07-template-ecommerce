package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.Gateway;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CompraForm {
    @NotNull
    private Long idProduto;
    @Positive
    private int quantidade;
    @NotNull
    private Gateway gateway;

    public CompraForm(Long idProduto, int quantidade, Gateway gateway) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.gateway = gateway;
    }

    public Compra toCompra(EntityManager manager, Usuario usuario){
        BigDecimal total = BigDecimal.ZERO;
        Produto produto = manager.find(Produto.class, idProduto);
        BigDecimal valor = produto.getValor().multiply(new BigDecimal(quantidade));
        total = total.add(valor);

        return new Compra(produto,quantidade,usuario,gateway,total);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Gateway getGateway() {
        return gateway;
    }

    @Override
    public String toString() {
        return "CompraForm{" +
                "idProduto=" + idProduto +
                ", quantidade=" + quantidade +
                ", gateway=" + gateway +
                '}';
    }
}
