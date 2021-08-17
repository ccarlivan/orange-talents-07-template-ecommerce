package br.com.zupacademy.carlivan.mercadolivre.controller;

import br.com.zupacademy.carlivan.mercadolivre.controller.dto.UsuarioDto;
import br.com.zupacademy.carlivan.mercadolivre.controller.form.UsuarioForm;
import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import br.com.zupacademy.carlivan.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public void salvar(@RequestBody @Valid UsuarioForm form){
        Usuario usuario = form.toUsuario();

        manager.persist(usuario);
    }

}
