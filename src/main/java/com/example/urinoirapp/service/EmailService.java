package com.example.urinoirapp.service;


import com.example.urinoirapp.Model.Medecin;
import com.example.urinoirapp.Model.Secretaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMedecinInformation(String email, Medecin medecin) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Account Information.");
        mailMessage.setText("Here is your account information:\n" +
                "Code: " + medecin.getCode() + "\n" +
                "Email: " + medecin.getEmail() + "\n" +
                "Password: " + medecin.getMotDePasse() + "\n" +
                "...");
        javaMailSender.send(mailMessage);
    }

    public void sendSecretaireInformation(String email, Secretaire secretaire) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Account Information.");
        mailMessage.setText("Here is your account information:\n" +
                "Code: " + secretaire.getCode() + "\n" +
                "Email: " + secretaire.getEmail() + "\n" +
                "Password: " + secretaire.getMotDePasse() + "\n" +
                "...");
        javaMailSender.send(mailMessage);
    }


}


