package com.example.urinoirapp.Controller;


import com.example.urinoirapp.Model.Medecin;
import com.example.urinoirapp.Model.Secretaire;
import com.example.urinoirapp.Repository.AdminRepository;
import com.example.urinoirapp.Service.EmailService;
import com.example.urinoirapp.service.MedecinService;
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
public class MedecinController {
  @Autowired
	private MedecinService medecinService;
  @Autowired
	private EmailService emailService;
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	public MedecinController(MedecinService medecinService, AdminRepository adminRepository, EmailService emailService) {
		this.medecinService = medecinService;
		this.adminRepository = adminRepository;
		this.emailService = emailService;
	}


	// handler method to handle list medecins and return mode and view
	@GetMapping("/medecins")
	public String listMedecins(Model model) {
		String adminName = getCurrentUsername();
		model.addAttribute("adminName", adminName);
		model.addAttribute("medecins", medecinService.getAllMedecins());
		return "medecins";
	}

	@GetMapping("/medecins/new")
	public String createMedecinForm(Model model) {

		// create medecin object to hold medecin form data
		Medecin medecin = new Medecin();
		model.addAttribute("medecin", medecin);
		return "create_medecin";

	}

	@PostMapping("/medecins")
	public String saveMedecin(@ModelAttribute("medecins") Medecin medecin){
		Medecin savedMedecin = medecinService.saveMedecin (medecin);

		// Send secretaire information via email
		emailService.sendMedecinInformation (savedMedecin.getEmail(), savedMedecin);
		return "redirect:/medecins";
	}


	@GetMapping("/medecins/edit/{id}")
	public String editMedecinForm(@PathVariable Long id, Model model) {
		model.addAttribute("medecin", medecinService.getMedecinById(id));
		return "edit_medecin";
	}

	@PostMapping("/medecins/{id}")
	public String updateMedecin(@PathVariable Long id,
								@ModelAttribute("medecin") Medecin medecin,
								Model model) {

		// get student from database by id
		Medecin existingMedecin = medecinService.getMedecinById(id);
		existingMedecin.setId(id);
		existingMedecin.setCode(medecin.getCode());
		existingMedecin.setNom(medecin.getNom());
		existingMedecin.setPrenom(medecin.getPrenom());
		existingMedecin.setCin(medecin.getCin());
		existingMedecin.setEmail(medecin.getEmail());
		existingMedecin.setMotDePasse(medecin.getMotDePasse());
		existingMedecin.setNumeroTelephone(medecin.getNumeroTelephone());


		// save updated student object
		medecinService.updateMedecin(existingMedecin);
		return "redirect:/medecins";
	}

	// handler method to handle delete student request

	@GetMapping("/medecins/{id}")
	public String deleteMedecins(@PathVariable Long id) {
		medecinService.deleteMedecinById(id);
		return "redirect:/medecins";
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

	@GetMapping("/loginMedecin")
	public String showLoginForm(Model model) {
		model.addAttribute("Medecin", new Medecin ());
		return "loginMedecin";
	}
}

