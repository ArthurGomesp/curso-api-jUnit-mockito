package br.com.gomes.api.repositories;

import br.com.gomes.api.domain.User;
import br.com.gomes.api.domain.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Override
    void deleteById(Long id);
}
