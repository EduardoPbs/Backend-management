package my.system.management.domain.user.model;

import jakarta.persistence.*;
import lombok.*;
import my.system.management.domain.employee.model.Employee;
import my.system.management.domain.user.dto.DataCreateLgUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class LgUser implements UserDetails {

    private final static String[] ROLES_USER = new String[]{"USER"};

    @Id
    private String id;

    private String login;
    private String password;
    private boolean is_admin;

    @OneToMany(mappedBy = "lgUser", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public LgUser(DataCreateLgUser data) {
        this.id = UUID.randomUUID().toString();
        this.login = data.login();
        this.password = data.password();
        this.is_admin = false;
    }

    public List<String> getRoles() {
        return List.of(ROLES_USER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
