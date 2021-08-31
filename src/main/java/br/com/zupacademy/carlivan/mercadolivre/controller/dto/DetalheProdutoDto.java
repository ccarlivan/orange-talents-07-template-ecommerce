package br.com.zupacademy.carlivan.mercadolivre.controller.dto;

import br.com.zupacademy.carlivan.mercadolivre.model.CaracteristicaProduto;

public class DetalheProdutoDto {
    private String nome;
    private String descricao;

    public DetalheProdutoDto(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
