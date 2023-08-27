package my.system.management.domain.funcionario.repository;

import my.system.management.domain.funcionario.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
    Page<Funcionario> findAllByAtivoTrue(Pageable pageable);
}
