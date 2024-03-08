package my.system.management.domain.employee.dto;

import my.system.management.domain.address.model.Address;
import my.system.management.domain.employee.model.Employee;

public record DataListEmployee(String id, String name, String cpf, Address address) {
    public DataListEmployee(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getCpf(), employee.getAddress());
    }
}
