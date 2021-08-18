package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import br.com.zupacademy.carlivan.mercadolivre.model.Categoria;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import br.com.zupacademy.carlivan.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.carlivan.mercadolivre.validation.ExistId;
import br.com.zupacademy.carlivan.mercadolivre.validation.IfExistId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoForm {
    @NotBlank
    private String nome;
    @NotNull @Positive
    private int quantidade;
    @NotBlank @Length(max = 1000)
    private String descricao;
    @NotNull @Positive
    private BigDecimal valor;
    @ExistId(domainClass = Categoria.class, fieldName="id")
    private Long idCategoria;
    @Size(min = 3)
    private List<CaracteristicaForm> caracteristicas = new ArrayList<>();

    public ProdutoForm(String nome, int quantidade, String descricao, BigDecimal valor, Long idCategoria,
                       List<CaracteristicaForm> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);

    }

    public Produto toProduto(EntityManager manager, Usuario dono){
        Categoria categoria = manager.find(Categoria.class, idCategoria);

        return new Produto(nome, quantidade, descricao, valor, categoria, dono, caracteristicas);
    }


    @Override
    public String toString() {
        return "ProdutoForm{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", idCategoria=" + idCategoria +
                ", caracteristicas=" + caracteristicas +
                '}';
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        for(CaracteristicaForm caracteristica : caracteristicas){
            String nome = caracteristica.getNome();
            if(!nomesIguais.add(nome)){
                resultados.add(nome);
            }
        }
        return resultados;
    }

    public List<CaracteristicaForm> getCaracteristicas() {
        return caracteristicas;
    }
}
