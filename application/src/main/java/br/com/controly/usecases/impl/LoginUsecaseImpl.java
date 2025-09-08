package br.com.controly.usecases.impl;

import br.com.controly.domain.entities.Sessions;
import br.com.controly.domain.entities.Users;
import br.com.controly.dtos.LoginDTO;
import br.com.controly.exception.InvalidCredentials;
import br.com.controly.jpa.SessionRepository;
import br.com.controly.jpa.UserRepository;
import br.com.controly.usecases.CreateSessionUsecase;
import br.com.controly.usecases.GetSessionByUserIdUsecase;
import br.com.controly.usecases.LoginUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUsecaseImpl implements LoginUsecase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GetSessionByUserIdUsecase getSessionByUserIdUsecase;

    @Autowired
    private CreateSessionUsecase createSessionUsecase;

    @Override
    public String execute(LoginDTO body) {
        Users user = userRepository.findByEmail(body.getEmail())
                .orElseThrow(InvalidCredentials::new);
        if (!user.getPassword().equals(body.getPassword()))
            throw new InvalidCredentials();

        Sessions sessions = getSessionByUserIdUsecase.execute(user.getId());
        if(sessions == null){
            sessions = createSessionUsecase.execute(user.getId());
        }
        return sessions.getToken();
    }
}
