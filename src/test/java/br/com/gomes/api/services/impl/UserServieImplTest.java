package br.com.gomes.api.services.impl;

import br.com.gomes.api.domain.User;
import br.com.gomes.api.domain.dto.UserDTO;
import br.com.gomes.api.exceptions.ObjectNotFoundException;
import br.com.gomes.api.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServieImplTest {

    public static final long ID = 1L;
    public static final String NAME = "arthur";
    public static final String EMAIL = "arthur@gmail.com";
    public static final String PASSWORD = "1234";

    @InjectMocks
    private UserServieImpl userServie;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> userOptional;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    @DisplayName("when find user by id then return an user instance")
    void findById() {
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(userOptional);
        User response = userServie.findById(ID);

        Assertions.assertEquals(User.class, response.getClass());
    }

    @Test
    @DisplayName("when find user by id then return object not found Exception")
    void findByIdFailed() {
        Mockito.when(userRepository.findById(Mockito.any())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try {
            userServie.findById(ID);
        }catch (Exception e ){
            Assertions.assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void createUser() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }

    private void startUser(){
        User user1 = new User(ID, NAME, EMAIL, PASSWORD);
        UserDTO userDTO1 = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        userOptional = Optional.of( new User(ID,NAME, EMAIL, PASSWORD));
    }
}