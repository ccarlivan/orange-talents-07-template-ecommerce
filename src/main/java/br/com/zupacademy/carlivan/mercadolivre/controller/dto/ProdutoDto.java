package br.com.zupacademy.carlivan.mercadolivre.controller.dto;

import br.com.zupacademy.carlivan.mercadolivre.model.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.IntStream;

public class ProdutoDto {
    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Set<DetalheProdutoDto> caracteristica;
    private Set<String> links;
    private Set<String> perguntas;
    private Set<Map<String,String>> opinioes;
    private double mediaNotas;
    private long total;

    public ProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristica = produto.mapeiaCaracteristicas(DetalheProdutoDto::new);
        this.links = produto.mapeiaImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
        this.opinioes = produto.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
        });
        Set<Integer> notas = produto.mapeiaOpinioes(opiniao -> opiniao.getNota());
        OptionalDouble media = notas.stream().mapToInt(nota -> nota).average();
        if(media.isPresent()){
            this.mediaNotas = media.getAsDouble();
            this.total = notas.stream().mapToInt(nota -> nota).count();
        }else {
            this.mediaNotas = 0.0;
        }
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<DetalheProdutoDto> getCaracteristica() {
        return caracteristica;
    }

    public Set<String> getLinks() {
        return links;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public long getTotal() {
        return total;
    }
}
