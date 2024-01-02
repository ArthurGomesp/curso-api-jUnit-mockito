package br.com.gomes.api.services;

import br.com.gomes.api.domain.User;

public interface UserService {
    public User findById(Long id);
}
