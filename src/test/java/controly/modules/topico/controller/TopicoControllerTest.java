package controly.modules.topico.controller;

import controly.modules.topico.entities.TopicoEntity;
import controly.modules.topico.repository.TopicoHasSeguidoresRepositoy;
import controly.modules.topico.repository.TopicoRepository;
import controly.modules.topico.service.TopicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TopicoControllerTest {

    @Autowired
    private TopicoController controller;

    @Autowired
    private TopicoService service;

    @MockBean
    private TopicoRepository repository;

    @MockBean
    private TopicoHasSeguidoresRepositoy hasSeguidoresRepository;


    @Test
    @DisplayName("Devera retornar um 200 e trazer um topico por id")
    void getTopicoSucess() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(new TopicoEntity()));

        ResponseEntity<TopicoEntity> topico = controller.getTopico(anyLong());

        assertEquals(200, topico.getStatusCodeValue());
    }

    @Test
    @DisplayName("Devera retornar um 201 e seguir o topico")
    void getTopicoFailed() {
    }

    @Test
    @DisplayName("Devera retornar um 200 e trazer uma lista de topicos")
    void getTopicosSucess() {
    }

    @Test
    @DisplayName("Devera retornar um 201 e seguir o topico")
    void getTopicosFailed() {
    }

    @Test
    @DisplayName("Devera retornar um 201 e seguir o topico")
    void followTopicoSuccess() {
    }

    @Test
    @DisplayName("Devera retornar um 400 e não seguir um topico")
    void followTopicoFailed() {
    }

    @Test
    @DisplayName("Devera retornar um 201 e seguir o topico")
    void unfollowTopicoSucess() {
    }

    @Test
    @DisplayName("Devera retornar um 201 e seguir o topico")
    void postTopicosFailed() {
    }
}