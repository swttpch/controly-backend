package br.com.controly.usecases.impl;

import br.com.controly.domain.entities.Sessions;
import br.com.controly.domain.entities.Users;
import br.com.controly.jpa.SessionRepository;
import br.com.controly.usecases.CreateSessionUsecase;
import br.com.controly.usecases.GetSessionByUserIdUsecase;
import br.com.controly.utils.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

@Service
public class CreateSessionUsecaseImpl implements CreateSessionUsecase {
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Sessions execute(Long body) {
        Sessions session = new Sessions();
        session.setToken(GenerateToken.generate());
        var user = new Users();
        user.setId(body);
        session.setUser(user);
        session.setExpiresAt(getExpirationDate());

        return sessionRepository.save(session);
    }

    private LocalDateTime getExpirationDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        return LocalDateTime.ofInstant(
                calendar.getTime().toInstant(),
                ZoneId.systemDefault()
        );
    }
}
