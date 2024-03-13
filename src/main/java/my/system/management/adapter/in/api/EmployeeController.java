package my.system.management.adapter.in.api;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import my.system.management.domain.employee.dto.DataUpdateEmployee;
import my.system.management.domain.employee.dto.DataCreateEmployee;
import my.system.management.domain.employee.dto.DataDetailsEmployee;
import my.system.management.domain.employee.dto.DataListEmployee;
import my.system.management.domain.employee.model.Employee;
import my.system.management.domain.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    @Transactional
    public ResponseEntity addFuncionario(@RequestBody @Valid DataCreateEmployee dados, UriComponentsBuilder uriBuilder) {
        System.out.println("REQUEST");
        var funcionario = service.save(new Employee(dados));
        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailsEmployee(funcionario));
    }

    @GetMapping("all-pagination")
    public ResponseEntity<Page<DataListEmployee>> getFuncionarios(@PageableDefault(sort = "name") Pageable pageable) {
        var page = service.findAllByActiveTrue(pageable).map(DataListEmployee::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("all")
    public ResponseEntity<List<DataListEmployee>> getEmployees() {
        final List<Employee> employees = service.findAll();
        List<DataListEmployee> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(new DataListEmployee(employee));
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") String id) {
        final Employee employee = service
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarFuncionario(@RequestBody @Valid DataUpdateEmployee dados) {
        var funcionario = service.getReferenceById(dados.id());
        funcionario.update(dados);
        return ResponseEntity.ok(new DataDetailsEmployee(funcionario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirFuncionario(@PathVariable("id") String id) {
        var funcionario = service.getReferenceById(id);
        funcionario.delete();
        return ResponseEntity.noContent().build();
    }
}
