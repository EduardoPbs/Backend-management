package my.system.management.domain.user.repository;

import my.system.management.domain.user.model.LgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface LgUserRepository extends JpaRepository<LgUser, String> {
    UserDetails findByLogin(String login);
}
