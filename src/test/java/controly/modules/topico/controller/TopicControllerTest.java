package controly.modules.topico.controller;

import controly.controller.TopicController;
import controly.dto.TopicDetailResponse;
import controly.entity.TopicEntity;
import controly.repository.TopicHasFollowersRepository;
import controly.repository.TopicRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TopicControllerTest {

    @Autowired
    private TopicController controller;

    @MockBean
    private TopicRepository repository;

    @MockBean
    private TopicHasFollowersRepository hasSeguidoresRepository;


//    @Test
//    @DisplayName("Devera retornar um 200 e trazer um topico por id")
//    void getTopicoSucess() {
//
//        when(repository.findById(anyLong())).thenReturn(Optional.of(new TopicoEntity()));
//
//        ResponseEntity<TopicoDTO> topico = controller.getTopico(anyLong());
//
//        assertEquals(200, topico.getStatusCodeValue());
//    }

    @Test
    @DisplayName("Devera retornar um 404 e não trazer um topico por id")
    void getTopicoFailed() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<TopicDetailResponse> topico = controller.getTopicById(anyLong());

        assertEquals(404, topico.getStatusCodeValue());

    }

    @Test
    @DisplayName("Devera retornar um 200 e trazer uma lista de topicos")
    void getTopicosSucess() {

        when(repository.findAll()).thenReturn(List.of(
                new TopicEntity()));

        when(hasSeguidoresRepository.countFollowersATopicHas(1L)).thenReturn(1);

        ResponseEntity<List<TopicDetailResponse>> topicoDTO = controller.getAllTopics();

        assertEquals(200, topicoDTO.getStatusCodeValue());
    }

    @Test
    @DisplayName("Devera retornar um 204 e não trazer uma lista de topicos")
    void getTopicosFailed() {
        when(repository.findAll()).thenReturn(
                new ArrayList<>());

        when(hasSeguidoresRepository.countFollowersATopicHas(1L)).thenReturn(1);

        ResponseEntity<List<TopicDetailResponse>> topicoDTO = controller.getAllTopics();

        assertEquals(204, topicoDTO.getStatusCodeValue());
    }

//    @Test
//    @DisplayName("Devera retornar um 201 e seguir o topico")
//    void followTopicoSuccess() {
//
//        ResponseEntity<?> teste = controller.followTopico(1L,1L);
//
//        assertEquals(201, teste.getStatusCodeValue());
//        assertNotNull(teste.getBody());
//    }

//    @Test
//    @DisplayName("Devera retornar um 400 e não seguir um topico")
//    void followTopicoFailed() {
//
//        ResponseEntity<?> teste = controller.followTopic(35453435454L,35453435454L);
//
//        assertEquals(404, teste.getStatusCodeValue());
//        assertNotNull(teste.getBody());
//    }

//    @Test
//    @DisplayName("Devera retornar um 201 e deixar de seguir o topico")
//    void unfollowTopicoSucess() {
//        ResponseEntity<?> teste = controller.unfollowTopico(1L,1L);
//
//        assertEquals(201, teste.getStatusCodeValue());
//        assertNotNull(teste.getBody());
//    }

//    @Test
//    @DisplayName("Devera retornar um 404 e não deixar de seguir um topico")
//    void unfollowTopicoFailed() {
//        ResponseEntity<?> teste = controller.unfollowTopico(35453435454L,35453435454L);
//
//        assertEquals(404, teste.getStatusCodeValue());
//        assertNotNull(teste.getBody());
//    }
}