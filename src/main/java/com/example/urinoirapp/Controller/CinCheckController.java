package com.example.urinoirapp.Controller;


import com.example.urinoirapp.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinCheckController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/check-cin-exists")
    public boolean checkCinExists(@RequestParam String cin) {
        return patientRepository.existsByCIN(cin);
    }
}
