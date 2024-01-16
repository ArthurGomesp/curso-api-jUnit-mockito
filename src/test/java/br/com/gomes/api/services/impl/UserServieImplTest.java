package br.com.gomes.api.services.impl;

import br.com.gomes.api.domain.User;
import br.com.gomes.api.domain.dto.UserDTO;
import br.com.gomes.api.exceptions.DataIntegratyViolationException;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

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
    void setup() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    @DisplayName("when find user by id then return an user instance")
    void findById() {
        when(userRepository.findById(Mockito.any())).thenReturn(userOptional);
        User response = userServie.findById(ID);

        assertEquals(User.class, response.getClass());
    }

    @Test
    @DisplayName("when find user by id then return object not found Exception")
    void findByIdFailed() {
        when(userRepository.findById(Mockito.any())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try {
            userServie.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());

        }
    }

    @Test
    @DisplayName("when find all users then return object list of users")
    void findAll() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> response = userServie.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());

    }

    @Test
    @DisplayName("when sucssesfully create a user")
    void createUser() {
        when(userRepository.save(any())).thenReturn(user);
        User response = userServie.createUser(userDTO);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());


    }

    @Test
    @DisplayName("when fail  create a user ")
    void createUser1() {
        when(userRepository.findByEmail(anyString())).thenReturn(userOptional);
        try {
            userOptional.get().setId(2L);
            userServie.createUser(userDTO);
        } catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
        }


    }

    @Test
    @DisplayName("when sucssesfully update user")
    void update() {
        when(userRepository.save(any())).thenReturn(user);
        User response = userServie.update(userDTO);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }
    @Test
    @DisplayName("when fail update user")
    void update1() {
        when(userRepository.findByEmail(anyString())).thenReturn(userOptional);
        try {
            userOptional.get().setId(2L);
            userServie.createUser(userDTO);
        } catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
        }


    }

    @Test
    void delete() {
        when(userRepository.findById(anyLong())).thenReturn(userOptional);
        doNothing().when(userRepository).deleteById(anyLong());
        userServie.delete(ID);
        verify(userRepository, times(1)).deleteById(anyLong());
    }
    @Test
    void deleteWithNotFoundException() {
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("objeto não encontrado"));
    try {
        userServie.delete(ID);
    }  catch (Exception e ){
        assertEquals(ObjectNotFoundException.class ,e.getClass());
        assertEquals("objeto não encontrado" ,e.getMessage());

    }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        userOptional = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}