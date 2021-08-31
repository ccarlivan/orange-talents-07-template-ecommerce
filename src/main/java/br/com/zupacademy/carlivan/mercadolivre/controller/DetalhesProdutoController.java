package br.com.zupacademy.carlivan.mercadolivre.controller;

import br.com.zupacademy.carlivan.mercadolivre.controller.dto.ProdutoDto;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/detalhes")
public class DetalhesProdutoController {
    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhes(@PathVariable("id") Long id) {
        Produto produto = manager.find(Produto.class, id);

        if (produto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado");
        }

        return ResponseEntity.ok(new ProdutoDto(produto));
    }
}
