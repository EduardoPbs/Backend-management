package my.system.management.domain.employee.service;

import jakarta.persistence.EntityNotFoundException;
import my.system.management.domain.employee.dto.DataListEmployee;
import my.system.management.domain.employee.model.Employee;
import my.system.management.domain.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<DataListEmployee> findAll() {
        final List<DataListEmployee> employees = employeeRepository
                .findAll()
                .stream()
                .map(DataListEmployee::new)
                .toList();
        return employees;
    }

    public Employee findById(String id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Funcionário não encontrado.")
                );
    }

    @Transactional
    public Employee getReferenceById(String id) {
        return employeeRepository.getReferenceById(id);
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Page<Employee> findAllByActiveTrue(Pageable pageable) {
        return employeeRepository.findAllByActiveTrue(pageable);
    }

}
