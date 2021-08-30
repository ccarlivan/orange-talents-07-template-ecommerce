package br.com.zupacademy.carlivan.mercadolivre.controller;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.PerguntaForm;
import br.com.zupacademy.carlivan.mercadolivre.model.Pergunta;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import br.com.zupacademy.carlivan.mercadolivre.security.TokenService;
import br.com.zupacademy.carlivan.mercadolivre.service.Email;
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
@RequestMapping("/perguntas")
public class PerguntasController {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private Email mail;

    @PostMapping("/{id}/perguntas")
    @Transactional
    public ResponseEntity<?> pergunta(@RequestBody @Valid PerguntaForm form, @PathVariable("id")Long id){
        Usuario usuario = tokenService.getLogado();
        Produto produto = manager.find(Produto.class, id);
        if(produto == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Produto não encontrado!!");
        }

        Pergunta pergunta = form.toPergunta(produto, usuario);
        manager.persist(pergunta);

        mail.send(pergunta);

        return ResponseEntity.ok(pergunta.toString());
    }
}
