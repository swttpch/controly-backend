package br.com.controly.usecases.impl;

import br.com.controly.domain.entities.Sessions;
import br.com.controly.domain.entities.Users;
import br.com.controly.jpa.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateSessionUsecaseImplTest {
    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private CreateSessionUsecaseImpl createSessionUsecase;

    private Sessions sessions;
    private Users users;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarSessaoSemErros(){
        users = new Users();
        users.setId(Long.valueOf(1));

        var newSession = createSessionUsecase.execute(users.getId());
    }
}
