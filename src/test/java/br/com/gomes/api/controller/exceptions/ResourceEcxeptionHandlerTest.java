package br.com.gomes.api.controller.exceptions;

import br.com.gomes.api.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ResourceEcxeptionHandlerTest {

    private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    private static final String E_MAIL_JA_CADASTRADO = "E-mail já cadastrado";

    @InjectMocks
    private ResourceEcxeptionHandler ecxeptionHandler;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void objectNotFoundException() {
        ResponseEntity<StandardError> response = ecxeptionHandler.ObjectNotFoundException(
                new ObjectNotFoundException("Objeto não encontrado"),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(404, response.getBody().getStatus());
        assertEquals("Objeto não encontrado", response.getBody().getError());
    }

    @Test
    void dataIntegratyViolationException() {
    }
}