package my.system.management.domain.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.system.management.domain.address.model.Address;
import my.system.management.domain.employee.dto.DataUpdateEmployee;
import my.system.management.domain.employee.dto.DataCreateEmployee;
import my.system.management.domain.order.model.Order;
import my.system.management.domain.user.model.LgUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "employees")
@Entity(name = "Employee")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private String id;
    private String name;

    @Column(unique = true)
    private String cpf;

    @Embedded
    private Address address;

    private boolean active;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private LgUser lgUser;

    public Employee(DataCreateEmployee dados) {
        this.id = UUID.randomUUID().toString();
        this.name = dados.name();
        this.cpf = dados.cpf();
        this.address = new Address(dados.address());
        this.active = true;
    }

    public void delete() {
        this.active = false;
    }

    public void update(DataUpdateEmployee data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.adress() != null) {
            this.address.updateAddress(data.adress());
        }
    }
}
