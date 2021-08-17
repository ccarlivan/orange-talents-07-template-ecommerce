package br.com.zupacademy.carlivan.mercadolivre.repository;

import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String s);
}
