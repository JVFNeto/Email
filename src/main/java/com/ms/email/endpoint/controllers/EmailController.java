package com.ms.email.endpoint.controllers;


import com.ms.email.endpoint.dtos.EmailDTO;
import com.ms.email.endpoint.services.EmailService;
import com.ms.email.models.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mail")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailController {

   private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Email> sendingEmail (@RequestBody @Valid EmailDTO emailDTO){
        Email email = new Email();

        BeanUtils.copyProperties(emailDTO, email);
        emailService.sendEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }
}
