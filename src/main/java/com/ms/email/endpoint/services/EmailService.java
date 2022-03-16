package com.ms.email.endpoint.services;

import com.ms.email.repositories.EmailRepository;
import com.ms.email.enums.StatusEmail;
import com.ms.email.models.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailService {

    private final EmailRepository emailRepository;


    private final JavaMailSender emailSender;//vem da dependencia do Email


    //posteriormente trocar pelo Amazon Simple Email Service, SE for gratuito
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());//seta a data de envio
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            //presta atenção em tudo que vc vai entender
            message.setFrom(email.getEmailFrom());//from
            message.setTo(email.getEmailTo());//to
            message.setSubject(email.getSubject());//titulo
            message.setText(email.getText());//texto
            emailSender.send(message);//enviar
            email.setStatusEmail(StatusEmail.SENT);
        }catch(MailException e){//se der erro
            email.setStatusEmail(StatusEmail.ERROR);
            System.out.println("ERRO: "+ e);
        }//salva o Model com seu determinado estado (SENT ou ERROR)
            return emailRepository.save(email);

    }
}