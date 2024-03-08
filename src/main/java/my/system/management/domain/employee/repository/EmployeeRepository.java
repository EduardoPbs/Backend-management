package my.system.management.domain.employee.repository;

import my.system.management.domain.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Page<Employee> findAllByActiveTrue(Pageable pageable);
}
