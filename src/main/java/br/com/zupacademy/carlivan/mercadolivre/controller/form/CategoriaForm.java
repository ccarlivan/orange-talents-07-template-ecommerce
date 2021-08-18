package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import br.com.zupacademy.carlivan.mercadolivre.model.Categoria;
import br.com.zupacademy.carlivan.mercadolivre.validation.IfExistId;
import br.com.zupacademy.carlivan.mercadolivre.validation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoriaForm {
    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @IfExistId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    public CategoriaForm(String nome) {
        this.nome = nome;
    }

    public CategoriaForm() {
    }

    public Categoria toCategoria(EntityManager manager){
        Categoria categoria = new Categoria(nome);
        if(idCategoriaMae != null){
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            if(categoriaMae!= null){
                categoria.setCategoriaMae(categoriaMae);
            }
        }
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }
}
