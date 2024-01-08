package br.com.gomes.api.services.impl;

import br.com.gomes.api.domain.User;
import br.com.gomes.api.domain.dto.UserDTO;
import br.com.gomes.api.exceptions.DataIntegratyViolationException;
import br.com.gomes.api.exceptions.ObjectNotFoundException;
import br.com.gomes.api.repositories.UserRepository;
import br.com.gomes.api.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServieImpl implements UserService {

    @Autowired
    private UserRepository   userRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));

    }

    @Override
    public User update(UserDTO dto) {
        findByEmail(dto);
        return userRepository.save(mapper.map(dto, User.class));
    }

    private void findByEmail(UserDTO obj) {
    Optional<User> user = userRepository.findByEmail(obj.getEmail());
    if(user.isPresent() && !user.get().getId().equals(obj.getId())) {
        throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
    }
}
}
