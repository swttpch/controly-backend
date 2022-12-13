package controly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailService {

    @Autowired
    final private JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public ResponseEntity<?> sendEmail(String nome, String email, String senha) {

        try{

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("suporte.controly@gmail.com");
            message.setTo(email);
            message.setSubject("Solicitação de Troca de Senha - ControlY");
            message.setText("Olá "+nome+"!\nSua nova senha é: "+senha+"\nAltere o mais breve possível!");
            emailSender.send(message);
            return ResponseEntity.status(200).build();

        }catch (Exception err){
            err.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }




}
