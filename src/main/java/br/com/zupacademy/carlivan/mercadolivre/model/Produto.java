package br.com.zupacademy.carlivan.mercadolivre.model;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.CaracteristicaForm;
import ch.qos.logback.core.util.COWArrayList;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull @Positive
    private int quantidade;
    @NotBlank @Length(max = 1000)
    private String descricao;
    @NotNull @Positive
    private BigDecimal valor;
    @NotNull @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas=new HashSet<>();
    private LocalDateTime dataCadastro;

    public Produto(String nome, int quantidade, String descricao, BigDecimal valor, Categoria categoria,
                   Usuario dono, @Size(min = 3) Collection<CaracteristicaForm> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.dono = dono;
        Set<CaracteristicaProduto> novasCaracteristicas =
                caracteristicas.stream().map(caracteristica -> caracteristica
                        .toModel(this)).collect(Collectors.toSet());
        this.caracteristicas.addAll(novasCaracteristicas);
        this.dataCadastro = LocalDateTime.now();
    }

    public Produto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", dono=" + dono +
                ", caracteristicas="+caracteristicas+
                '}';
    }
}
