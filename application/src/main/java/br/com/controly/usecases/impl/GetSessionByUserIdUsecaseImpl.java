package br.com.controly.usecases.impl;

import br.com.controly.domain.entities.Sessions;
import br.com.controly.domain.entities.Users;
import br.com.controly.dtos.LoginDTO;
import br.com.controly.exception.InvalidCredentials;
import br.com.controly.jpa.SessionRepository;
import br.com.controly.jpa.UserRepository;
import br.com.controly.usecases.GetSessionByUserIdUsecase;
import br.com.controly.usecases.LoginUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetSessionByUserIdUsecaseImpl implements GetSessionByUserIdUsecase {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Sessions execute(Long body) {
        return this.sessionRepository.findByUserId(body).orElseGet(() -> null);
    }
}
