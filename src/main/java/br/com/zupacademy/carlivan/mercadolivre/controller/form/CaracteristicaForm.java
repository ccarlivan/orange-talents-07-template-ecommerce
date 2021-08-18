package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import br.com.zupacademy.carlivan.mercadolivre.model.CaracteristicaProduto;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaForm {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaForm(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto toModel(@NotNull Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }

    @Override
    public String toString() {
        return "CaracteristicaForm{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
