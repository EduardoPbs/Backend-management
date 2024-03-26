package my.system.management.domain.product.service;

import my.system.management.domain.product.dto.DataDetailsProduct;
import my.system.management.domain.product.dto.DataListProduct;
import my.system.management.domain.product.dto.DataUpdateProduct;
import my.system.management.domain.product.model.Product;
import my.system.management.domain.product.repository.ProductRepository;
import my.system.management.infra.exception.ProdutoDuplicatedException;
import my.system.management.infra.exception.ProdutoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        final Product productEncontrado = productRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
        return productEncontrado;
    }

    public Product getReferenceById(String id) {
        return productRepository.getReferenceById(id);
    }

    public List<DataListProduct> findAllByAtivoTrue() {
        List<DataListProduct> activeProducts = productRepository
                .findAllByActiveTrue()
                .stream()
                .map(DataListProduct::new)
                .toList();
        return Collections.unmodifiableList(activeProducts);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> findAll(Sort sort) {
        return Collections.unmodifiableList(productRepository.findAll(sort));
    }

    public Product save(Product product) {
        final Optional<Product> productFounded = Optional.ofNullable(productRepository.findByCode(product.getCode()));

        if (productFounded.isPresent() && productFounded.get().getCode().equals(product.getCode())) {
            throw new ProdutoDuplicatedException(product.getCode());
        }

        return productRepository.save(product);
    }

    @Transactional
    public Product update(String id, DataUpdateProduct data) {
        Product productRecuperado = findById(id);
        productRecuperado.update(data);

        return productRecuperado;
    }
}
