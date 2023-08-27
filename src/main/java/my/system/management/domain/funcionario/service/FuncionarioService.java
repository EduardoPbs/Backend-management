package my.system.management.domain.funcionario.service;

import my.system.management.domain.funcionario.model.Funcionario;
import my.system.management.domain.funcionario.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<Funcionario> findAll(){
        return repository.findAll();
    }

    public Optional<Funcionario> findById(String id){
        return repository.findById(id);
    }

    public Funcionario getReferenceById(String id){
        return repository.getReferenceById(id);
    }

    public Funcionario save(Funcionario funcionario){
        return repository.save(funcionario);
    }

    public Page<Funcionario> findAllByAtivoTrue(Pageable pageable){
        return repository.findAllByAtivoTrue(pageable);
    }

}
