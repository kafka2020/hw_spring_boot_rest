package ru.netolgy.hw_spring_boot_rest.repository;

import ru.netolgy.hw_spring_boot_rest.model.Authorities;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        if ("admin".equals(user) && "admin123".equals(password)) {
            return Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE);
        }
        if ("user".equals(user) && "user123".equals(password)) {
            return Arrays.asList(Authorities.READ, Authorities.WRITE);
        }
        return Collections.emptyList();  // Пользователь не найден
    }
}
