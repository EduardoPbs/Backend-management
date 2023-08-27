package my.system.management.adapter.in.api;

import jakarta.validation.Valid;
import my.system.management.domain.funcionario.dto.DadosAtualizadosFuncionario;
import my.system.management.domain.funcionario.dto.DadosCadastroFuncionario;
import my.system.management.domain.funcionario.dto.DadosDetalhesFuncionario;
import my.system.management.domain.funcionario.dto.DadosListagemFuncionario;
import my.system.management.domain.funcionario.model.Funcionario;
import my.system.management.domain.funcionario.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity addFuncionario(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder){
        var funcionario = service.save(new Funcionario(dados));
        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhesFuncionario(funcionario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemFuncionario>> getFuncionarios(@PageableDefault(sort = "nome") Pageable pageable){
        var page = service.findAllByAtivoTrue(pageable).map(DadosListagemFuncionario::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarFuncionario(@RequestBody @Valid DadosAtualizadosFuncionario dados){
        var funcionario = service.getReferenceById(dados.id());
        funcionario.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhesFuncionario(funcionario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirFuncionario(@PathVariable("id") String id){
        var funcionario = service.getReferenceById(id);
        funcionario.excluir();
        return ResponseEntity.noContent().build();
    }
}
