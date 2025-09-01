package br.com.controly.services;

import br.com.controly.dtos.PasswordRecoveryDTO;
import br.com.controly.exception.UsersEmailNotFould;
import br.com.controly.utils.PasswordGenerator;
import br.com.controly.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.controly.jpa.UserRepository;

import javax.transaction.Transactional;

@Service
public class PasswordRecoveryService {

    @Autowired
    final private PasswordGenerator passwordGenerator;

    @Autowired
    final private EmailService sendEmail;

    @Autowired
    final private UserRepository userRepository;

    public PasswordRecoveryService(PasswordGenerator passwordGenerator, EmailService sendEmail, UserRepository userRepository) {
        this.passwordGenerator = passwordGenerator;
        this.sendEmail = sendEmail;
        this.userRepository = userRepository;
    }

    @Transactional
    public void passwordRecovery(PasswordRecoveryDTO userToRecover){
        UserEntity user = userRepository.findByEmail(userToRecover.getEmail())
                .orElseThrow(UsersEmailNotFould::new);
        user.setPassword(passwordGenerator.generate());
        sendEmail.sendEmail(user.getName(),user.getEmail(),user.getPassword());
    }
}
