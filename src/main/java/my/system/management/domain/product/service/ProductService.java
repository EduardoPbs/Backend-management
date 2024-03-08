package my.system.management.domain.product.service;

import my.system.management.domain.product.dto.DataUpdateProduct;
import my.system.management.domain.product.model.Product;
import my.system.management.domain.product.repository.ProductRepository;
import my.system.management.infra.exception.ProdutoDuplicatedException;
import my.system.management.infra.exception.ProdutoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(String id) {
        final Product productEncontrado = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
        return productEncontrado;
    }

    public Product getReferenceById(String id) {
        return repository.getReferenceById(id);
    }

    public List<Product> findAllByAtivoTrue() {
        return repository.findAllByActiveTrue();
    }

    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Product save(Product product) {
        final Optional<Product> productFounded = Optional.ofNullable(repository.findByCode(product.getCode()));

        if (productFounded.isPresent() && productFounded.get().getCode().equals(product.getCode())) {
            throw new ProdutoDuplicatedException(product.getCode());
        }

        return repository.save(product);
    }

    public Product update(String id, DataUpdateProduct data) {
        Product productRecuperado = findById(id);
        productRecuperado.update(data);

        return productRecuperado;
    }
}
