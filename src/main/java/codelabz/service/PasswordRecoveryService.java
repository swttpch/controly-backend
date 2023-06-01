package codelabz.service;

import codelabz.dto.PasswordRecoveryRequest;
import codelabz.entity.UserEntity;
import codelabz.exception.UsersEmailNotFould;
import codelabz.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import codelabz.repository.UserRepository;

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
    public void passwordRecovery(PasswordRecoveryRequest userToRecover){
        UserEntity user = userRepository.findByEmail(userToRecover.getEmail())
                .orElseThrow(UsersEmailNotFould::new);
        user.setPassword(passwordGenerator.generate());
        sendEmail.sendEmail(user.getName(),user.getEmail(),user.getPassword());
    }
}
