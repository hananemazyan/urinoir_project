package com.example.urinoirapp.Controller;


import com.example.urinoirapp.Model.Secretaire;
import com.example.urinoirapp.Service.SecretaireService;
import com.example.urinoirapp.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecretaireController {

    private SecretaireService secretaireService;

    @Autowired
    private EmailService emailService;

    public SecretaireController(SecretaireService secretaireService) {

        this.secretaireService = secretaireService;
    }

    // handler method to handle list secretaire and return mode and view
    @GetMapping("/secretaires")
    public String listSecretaires(Model model) {
        model.addAttribute("secretaires", secretaireService.getAllSecretaire());
        String adminName = getCurrentUsername();
        model.addAttribute("adminName", adminName);
        return "secretaires";
    }

    @GetMapping("/secretaires/new")
    public String createSecretaireForm(Model model) {

        // create secretaire object to hold secretaire form data
        Secretaire secretaire = new Secretaire();
        model.addAttribute("secretaire", secretaire);
        return "create_secretaire";

    }

    @PostMapping("/secretaires")
    public String saveSecretaires(@ModelAttribute("secretaire") Secretaire secretaire) {
       Secretaire savedSecretaire = secretaireService.saveSecretaire(secretaire);

        // Send secretaire information via email
        emailService.sendSecretaireInformation(savedSecretaire.getEmail(), savedSecretaire);
        return "redirect:/secretaires";
    }

    @GetMapping("/secretaires/edit/{id}")
    public String editSecretaireForm(@PathVariable Long id, Model model) {
        model.addAttribute("secretaire", secretaireService.getSecretaireById(id));
        return "edit_secretaire";
    }

    @PostMapping("/secretaires/{id}")
    public String updateSecretaire(@PathVariable Long id,
                                @ModelAttribute("secretaire") Secretaire secretaire,
                                Model model) {

        // get student from database by id
        Secretaire existingSecretaire = secretaireService.getSecretaireById(id);
        existingSecretaire.setId(id);
        existingSecretaire.setCode(secretaire.getCode());
        existingSecretaire.setCin(secretaire.getCin());
        existingSecretaire.setNom(secretaire.getNom());
        existingSecretaire.setPrenom(secretaire.getPrenom());
        existingSecretaire.setEmail(secretaire.getEmail());
        existingSecretaire.setMotDePasse(secretaire.getMotDePasse());
        existingSecretaire.setNumeroTelephone(secretaire.getNumeroTelephone());
        existingSecretaire.setServiceAffecte(secretaire.getServiceAffecte());

        // save updated student object
        secretaireService.updateSecretaire(existingSecretaire);
        return "redirect:/secretaires";
    }

    // handler method to handle delete student request

    @GetMapping("/secretaires/{id}")
    public String deleteSecretaires(@PathVariable Long id) {
        secretaireService.deleteSecretaireById(id);
        return "redirect:/secretaires";
    }
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return "login"; // Indicate that no user is currently logged in
    }
}



