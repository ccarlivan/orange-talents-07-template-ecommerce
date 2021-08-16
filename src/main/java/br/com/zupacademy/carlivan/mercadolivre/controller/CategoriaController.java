package br.com.zupacademy.carlivan.mercadolivre.controller;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.CategoriaForm;
import br.com.zupacademy.carlivan.mercadolivre.model.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> salvar(@RequestBody @Valid CategoriaForm form){
        Categoria categoria = form.toCategoria(manager);
        manager.persist(categoria);
        return ResponseEntity.ok().build();
    }
}
