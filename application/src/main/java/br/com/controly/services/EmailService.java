package br.com.controly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class EmailService {

    @Autowired
    final private JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String name, String email, String password) {

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("suporte.controly@gmail.com");
            message.setTo(email);
            message.setSubject("Solicitação de Troca de Senha - ControlY");
            message.setText("Olá "+name+"!\nSua nova password é: "+password+"\nAltere o mais breve possível!");
            emailSender.send(message);
        }catch (Exception err){
            err.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes wrong.");
        }
    }




}
