package br.com.gomes.api.services;

import br.com.gomes.api.domain.User;

public interface UserService {
    User findById(Long id);
}
