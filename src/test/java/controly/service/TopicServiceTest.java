package controly.service;

import controly.entity.TopicEntity;
import controly.exception.TopicIdNotFould;
import controly.exception.UsersIdNotFould;
import controly.repository.TopicHasFollowersRepository;
import controly.repository.TopicRepository;
import controly.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TopicServiceTest {
    @Autowired
    private TopicService topicService;
    @Test
    @DisplayName("Check if getAllTopics method is not returning empty")
    void getAllTopics(){
        List<TopicEntity> topics = topicService.getAllTopics();
        assertFalse(topics.isEmpty());
    }

    @Test
    @DisplayName("getTopicById passing a topic id that does not exists")
    void getTopicsById_passingATopicIdThatNotExists(){
        assertThrows(TopicIdNotFould.class, ()-> topicService.getTopicById(11L));
    }
    @Test
    @DisplayName("getTopicById passing a topic id that does not exists")
    void getTopicsById_passingATopicIdThatExists(){
        assertEquals(TopicEntity.class, topicService.getTopicById(10L).getClass());
    }
}