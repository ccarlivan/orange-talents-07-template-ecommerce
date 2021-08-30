package br.com.zupacademy.carlivan.mercadolivre.controller;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.OpiniaoForm;
import br.com.zupacademy.carlivan.mercadolivre.model.Opiniao;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import br.com.zupacademy.carlivan.mercadolivre.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("{id}/opinioes")
    @Transactional
    public ResponseEntity<?> adiciona(@RequestBody @Valid OpiniaoForm form, @PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class, id);
        if(produto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado");
        }
        Usuario usuario = tokenService.getLogado();
        Opiniao opiniao = form.toOpiniao(produto,usuario);
        manager.persist(opiniao);
        return ResponseEntity.ok().build();
    }
}
