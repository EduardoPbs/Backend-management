package my.system.management.adapter.in.api;

import jakarta.validation.Valid;
import my.system.management.domain.enums.Categoria;
import my.system.management.domain.produto.dto.DadosAtualizadosProduto;
import my.system.management.domain.produto.dto.DadosCadastroProduto;
import my.system.management.domain.produto.dto.DadosDetalhesProduto;
import my.system.management.domain.produto.dto.DadosListagemProduto;
import my.system.management.domain.produto.model.Produto;
import my.system.management.domain.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    @Transactional
    public ResponseEntity addProduto(@RequestBody @Valid DadosCadastroProduto data, UriComponentsBuilder uriBuilder){
        final Produto produto = service.save(new Produto(data));
        final URI location  = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(produto.getId());
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarProduto(@RequestBody @Valid DadosAtualizadosProduto dados){
        final Produto produto = service.update(dados);
        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhesProduto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirProduto(@PathVariable("id") String id){
        final Produto produto = service.getReferenceById(id);
        produto.excluir();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemProduto>> getProdutos(){
        List<DadosListagemProduto> activeProducts = service.findAllByAtivoTrue().stream().map(DadosListagemProduto::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(activeProducts);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Produto>> getAllProdutos(){
        final List<Produto> allProducts = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @GetMapping("/all-pagination")
    public ResponseEntity<Page<DadosListagemProduto>> getPageProducts(@PageableDefault(sort = "nome") Pageable pageable) {
        final Page<DadosListagemProduto> produtos = service.findAll(pageable).map(DadosListagemProduto::new);
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhesProduto(@PathVariable("id") String id){
        final Produto produto = service.getReferenceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhesProduto(produto));
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getCategorias(){
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(Categoria.values()));
    }
}
