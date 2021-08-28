package br.com.zupacademy.carlivan.mercadolivre.controller;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.ImagensForm;
import br.com.zupacademy.carlivan.mercadolivre.controller.form.ProdutoForm;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import br.com.zupacademy.carlivan.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.carlivan.mercadolivre.security.TokenService;
import br.com.zupacademy.carlivan.mercadolivre.validation.ProibeCaracteristicaComNomeIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UploaderFake uploaderFake;

    @InitBinder(value = "form")
    public void init(WebDataBinder binder) {
        binder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> salvar(@RequestBody @Valid ProdutoForm form) {

        Usuario dono = tokenService.getLogado();

        Produto produto = form.toProduto(manager, dono);
        manager.persist(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public String adiciona(@PathVariable("id") Long id, @Valid ImagensForm form) {
        Usuario dono = tokenService.getLogado();
        Produto produto = manager.find(Produto.class, id);

        if(!produto.getDono().equals(dono)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.envia(form.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);

        return produto.toString();
    }
}
