package com.example.urinoirapp.Controller;


import com.example.urinoirapp.Model.Admin;
import com.example.urinoirapp.service.AdminService;

import com.example.urinoirapp.service.MedecinService;
import com.example.urinoirapp.service.SecretaireService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
@Autowired
    private AdminService adminService;
    @Autowired
    private MedecinService medecinService;
    @Autowired
    private SecretaireService secretaireService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


        @GetMapping("/AdminProfile")
    public String profil() {
        return "AdminProfile";
    }


//    @GetMapping("/dashboard")
//    public String Home() {
//        return "dashboard";
//    }

    @GetMapping("/signup")
    public String Signup(Model model) {
        model.addAttribute("admin", new Admin ()); // Removed the space before "admin"
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute("admin") Admin admin) {
        adminService.save(admin);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "login";
    }
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("admin") Admin admin) {
        adminService.save(admin);
        return "redirect:/dashboard";
    }

//    @GetMapping("/dashboard")
//    public String getDashboardCounts(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        boolean isAdmin = authentication.getAuthorities().stream()
//                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
//
//        if (isAdmin) {
//            long medecinCount = medecinService.countMedecins();
//            long secretaireCount = secretaireService.countSecretaire();
//            model.addAttribute("medecinCount", medecinCount);
//            model.addAttribute("secretaireCount", secretaireCount);
//            return "/dashboard";
//        } else {
//            return "/login";
//        }
//    }}


    @GetMapping("/dashboard")
    public String getDashboardCounts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            long medecinCount = medecinService.countMedecins();
            long secretaireCount = secretaireService.countSecretaire();
            model.addAttribute("medecinCount", medecinCount);
            model.addAttribute("secretaireCount", secretaireCount);
            String adminName = getCurrentUsername(authentication);
            model.addAttribute("adminName", adminName);
            return "/dashboard";
        } else {
            return "/login";
        }
    }

    private String getCurrentUsername(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}






