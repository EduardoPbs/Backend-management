package my.system.management.domain.product.repository;

import my.system.management.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByActiveTrue();

    Page<Product> findAll(Pageable pageable);

    Product findByCode(String code);
}
