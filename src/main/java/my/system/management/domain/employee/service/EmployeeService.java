package my.system.management.domain.employee.service;

import my.system.management.domain.employee.model.Employee;
import my.system.management.domain.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> findAll(){
        return repository.findAll();
    }

    public Optional<Employee> findById(String id){
        return repository.findById(id);
    }

    public Employee getReferenceById(String id){
        return repository.getReferenceById(id);
    }

    public Employee save(Employee employee){
        return repository.save(employee);
    }

    public Page<Employee> findAllByActiveTrue(Pageable pageable){
        return repository.findAllByActiveTrue(pageable);
    }

}
