package my.system.management.domain.produto.repository;

import my.system.management.domain.produto.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    Page<Produto> findAllByAtivoTrue(Pageable pageable);
}
