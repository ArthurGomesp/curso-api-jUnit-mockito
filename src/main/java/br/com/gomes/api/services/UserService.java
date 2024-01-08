package br.com.gomes.api.services;

import br.com.gomes.api.domain.User;
import br.com.gomes.api.domain.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    User createUser(UserDTO dto);

    User update(UserDTO dto);
     void delete(Long id);
}
