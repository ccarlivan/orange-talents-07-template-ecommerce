package br.com.zupacademy.carlivan.mercadolivre.repository;

import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
