package com.nassim.usermanagement.Controller;

import com.nassim.usermanagement.Model.ContactModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping("/contact")
    public ResponseEntity<String> sendContactForm(@RequestBody ContactModel contact) {

        SimpleMailMessage message = new SimpleMailMessage();

        //message.setFrom(contact.getEmail());
        message.setTo("kouidri@et.intechinfo.fr");
        message.setSubject(contact.getSubject());
        message.setText("Name: " + contact.getName() + ("\nEmail: " + contact.getEmail()+ "\nMessage: " + contact.getMessage() + "\nNumero de téléphone : "+ contact.getPhoneNumber()));

        emailSender.send(message);

        return ResponseEntity.ok("Message envoyé avec succès bg !");
    }
}