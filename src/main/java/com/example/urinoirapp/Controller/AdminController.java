package com.example.urinoirapp.Controller;


import com.example.urinoirapp.Model.Admin;

import com.example.urinoirapp.Repository.AdminRepository;
import com.example.urinoirapp.Service.AdminService;
import com.example.urinoirapp.Service.SecretaireService;
import com.example.urinoirapp.service.MedecinService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@Controller
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;
@Autowired
    private AdminService adminService;
    @Autowired
    private MedecinService medecinService;
    @Autowired
    private SecretaireService secretaireService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }





//    @GetMapping("/profile")
//    public String userProfile(Model model) {
//        String username = getCurrentUsername();
//        if (username != null) {
//            Admin admin = adminService.findByUsername(username); // Assuming such a method exists in your service
//            model.addAttribute("admin", admin);
//            return "users-profile"; // Assuming "users-profile" is your view name
//        } else {
//            return "redirect:/login"; // Redirect to the login page if no user is logged in
//        }
//    }



//    @GetMapping("/profile")
//    public String showProfile(Model model) {
//        String username = getCurrentUsername(); // Assuming you have a method to get the current admin
//        if (username.equals("login")) {
//            // Handle the case where no admin is authenticated
//            return "redirect:/login";
//        }
//        Admin admin = adminService.findByUsername(username); // Fetch the admin details using the username
//        model.addAttribute("admin", admin);
//        return "users-profile";
//    }



    @GetMapping("/users-profile")
    public String showUserProfile(Model model) {
        String currentUsername = getCurrentUsername();
        model.addAttribute("username", currentUsername);

        return "users-profile"; // This should match the name of your HTML file
    }



//        @PostMapping("/users-profile")
//        public String updateUserProfile(@ModelAttribute("admin") Admin admin, BindingResult result) {
//            if (result.hasErrors()) {
//                return "users-profile";
//            }
//            // Save or update the admin object
//            return "redirect:/some-other-page";
//        }

//    public String showUserProfile() {
//
//        return "users-profile"; // This should match the name of your HTML file
//    }






//    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
//
//
//
//    @GetMapping("/profile/{id}")
//    public String getProfile(@PathVariable Long id, Model model, Principal principal) {
//        String currentUsername = getCurrentUsername(SecurityContextHolder.getContext().getAuthentication());
//        Admin admin = adminRepository.findByUsername(currentUsername);
//        if (admin != null && admin.getId().equals(id)) {
//            model.addAttribute("admin", admin);
//            return "AdminProfile";
//        } else {
//            return "redirect:/AdminProfile"; // Redirige vers une page d'erreur
//        }
//    }
//
//
//    @GetMapping("/adminprofile")
//    public String adminProfile(Model model, Principal principal) {
//        // Retrieve the admin object based on the principal (logged-in user)
//        String currentUsername = getCurrentUsername(SecurityContextHolder.getContext().getAuthentication());
//        Admin admin = adminService.findByUsernname (currentUsername);
//
//        // Add the admin object to the model
//        model.addAttribute("admin", admin);
//
//        // Return the view name
//        return "AdminProfile";
//    }
//
//
//      }


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
            String adminName = getCurrentUsername();
            model.addAttribute("adminName", adminName);
            return "/dashboard";
        } else {
            return "/login";
        }
    }

//    private String getCurrentUsername(Authentication authentication) {
//        if (authentication != null && authentication.getPrincipal() != null) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof UserDetails) {
//                return ((UserDetails) principal).getUsername();
//            } else {
//                return principal.toString();
//            }
//        }
//        return "login";}

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








