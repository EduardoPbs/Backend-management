package my.system.management.domain.user.dto;

import my.system.management.domain.user.model.LgUser;

import java.util.List;

public record DataDetailsLgUser(String email, String password, List<String> roles) {
    public DataDetailsLgUser(LgUser lgUser){
        this(lgUser.getLogin(), lgUser.getPassword(), lgUser.getRoles());
    }
}
