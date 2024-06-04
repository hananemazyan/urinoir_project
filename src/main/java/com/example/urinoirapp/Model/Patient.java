package com.example.urinoirapp.Model;

import com.example.urinoirapp.HealthProblem;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String Firstname;
    private String Lastname;
    private String CIN;
    private String Adresse;
    private LocalDate dateOfBirth;
    private String Telephone;
    private String Email;
    private String Role;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    private String Password;

    @Enumerated(EnumType.STRING)
    private HealthProblem healthProblem;

    private String allergy;

    //    private String Code;
    private String serialCode;

    private String qrCodePath;

    @ManyToOne
    @JoinColumn(name = "secretaire_id")
    private Secretaire secretaire;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

//    @ManyToMany(mappedBy = "patients")
//    private List<Rapport> rapports;

    // Getters and setters



    public Secretaire getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(Secretaire secretaire) {
        this.secretaire = secretaire;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.Firstname;
    }

    public void setFirstname(final String firstname) {
        this.Firstname = firstname;
    }

    public String getLastname() {
        return this.Lastname;
    }

    public void setLastname(final String lastname) {
        this.Lastname = lastname;
    }

    public String getCIN() {
        return this.CIN;
    }

    public void setCIN(final String CIN) {
        this.CIN = CIN;
    }

    public String getAdresse() {
        return this.Adresse;
    }

    public void setAdresse(final String adresse) {
        this.Adresse = adresse;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelephone() {
        return this.Telephone;
    }

    public void setTelephone(final String telephone) {
        this.Telephone = telephone;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(final String email) {
        this.Email = email;
    }

    public String getRole() {
        return this.Role;
    }

    public void setRole(final String role) {
        this.Role = role;
    }

    public HealthProblem getHealthProblem() {
        return this.healthProblem;
    }

    public void setHealthProblem(final HealthProblem healthProblem) {
        this.healthProblem = healthProblem;
    }

    public String getAllergy() {
        return this.allergy;
    }

    public void setAllergy(final String allergy) {
        this.allergy = allergy;
    }

    public String getSerialCode() {
        return this.serialCode;
    }

    public void setSerialCode(final String serialCode) {
        this.serialCode = serialCode;
    }

    public String getQrCodePath() {
        return this.qrCodePath;
    }

    public void setQrCodePath(final String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }
}





//    public List<Rapport> getRapports() {
//        return rapports;
//    }
//
//    public void setRapports(List<Rapport> rapports) {
//        this.rapports = rapports;
//    }
//}
