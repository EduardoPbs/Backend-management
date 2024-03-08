package my.system.management.domain.user.service;

import my.system.management.domain.user.repository.LgUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LgUserService implements UserDetailsService {

    @Autowired
    private LgUserRepository lgUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return lgUserRepository.findByLogin(username);
    }
}
