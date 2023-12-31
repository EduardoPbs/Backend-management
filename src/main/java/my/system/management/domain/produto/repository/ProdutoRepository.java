package my.system.management.domain.produto.repository;

import my.system.management.domain.produto.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    List<Produto> findAllByAtivoTrue();

    Page<Produto> findAll(Pageable pageable);
}
