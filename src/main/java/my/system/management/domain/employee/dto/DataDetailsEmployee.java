package my.system.management.domain.employee.dto;

import my.system.management.domain.address.model.Address;
import my.system.management.domain.employee.model.Employee;

public record DataDetailsEmployee(String id, String name, String cpf, Address address) {
    public DataDetailsEmployee(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getCpf(), employee.getAddress());
    }
}
