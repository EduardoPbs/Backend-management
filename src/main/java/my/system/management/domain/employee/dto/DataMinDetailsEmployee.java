package my.system.management.domain.employee.dto;

import my.system.management.domain.employee.model.Employee;

public record DataMinDetailsEmployee(String id, String name, String cpf) {
    public DataMinDetailsEmployee(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getCpf());
    }
}
