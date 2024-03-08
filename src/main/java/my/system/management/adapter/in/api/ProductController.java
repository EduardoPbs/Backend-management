package my.system.management.adapter.in.api;

import jakarta.validation.Valid;
import my.system.management.domain.enums.Category;
import my.system.management.domain.product.dto.DataUpdateProduct;
import my.system.management.domain.product.dto.DataCreateProduct;
import my.system.management.domain.product.dto.DataDetailsProduct;
import my.system.management.domain.product.dto.DataListProduct;
import my.system.management.domain.product.model.Product;
import my.system.management.domain.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Product> create(@RequestBody @Valid DataCreateProduct data, UriComponentsBuilder uriBuilder){
        final Product product = service.save(new Product(data));
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateProduct dados){
        final Product product = service.update(dados);
        return ResponseEntity.status(HttpStatus.OK).body(new DataDetailsProduct(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") String id){
        final Product product = service.getReferenceById(id);
        product.delete();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<DataListProduct>> getProducts(){
        List<DataListProduct> activeProducts = service.findAllByAtivoTrue().stream().map(DataListProduct::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(activeProducts);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> showAll(){
        final List<Product> allProducts = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @GetMapping("/all-pagination")
    public ResponseEntity<Page<DataListProduct>> showPageable(@PageableDefault(sort = "name") Pageable pageable) {
        final Page<DataListProduct> produtos = service.findAll(pageable).map(DataListProduct::new);
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity showOne(@PathVariable("id") String id){
        final Product product = service.getReferenceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DataDetailsProduct(product));
    }

        @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(Category.values()));
    }
}
