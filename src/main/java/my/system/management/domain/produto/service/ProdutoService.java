package my.system.management.domain.produto.service;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import my.system.management.domain.enums.Categoria;
import my.system.management.domain.produto.dto.DadosAtualizadosProduto;
import my.system.management.domain.produto.model.Produto;
import my.system.management.domain.produto.repository.ProdutoRepository;
import my.system.management.infra.exception.ProdutoDuplicatedException;
import my.system.management.infra.exception.ProdutoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Produto findById(String id){
        final Produto produtoEncontrado = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
        return produtoEncontrado;
    }

    public Produto getReferenceById(String id){
        return repository.getReferenceById(id);
    }

    public Page<Produto> findAllByAtivoTrue(Pageable pageable){
        return repository.findAllByAtivoTrue(pageable);
    }

    public Produto save(Produto produto){
        final Optional<Produto> produtoEncontrado = repository.findById(produto.getId());

        if(produtoEncontrado.isPresent() && produtoEncontrado.get().getCodigo().equals(produto.getCodigo())){
            throw new ProdutoDuplicatedException(produto.getCodigo());
        }

        return repository.save(produto);
    }

    public Produto update(DadosAtualizadosProduto data){
        Produto produtoRecuperado = findById(data.id());
        produtoRecuperado.atualizar(data);

        return produtoRecuperado;
    }
}
