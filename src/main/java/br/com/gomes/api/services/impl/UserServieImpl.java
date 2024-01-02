package br.com.gomes.api.services.impl;

import br.com.gomes.api.domain.User;
import br.com.gomes.api.repositories.UserRepository;
import br.com.gomes.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServieImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return  user.orElse(null);
    }
}
