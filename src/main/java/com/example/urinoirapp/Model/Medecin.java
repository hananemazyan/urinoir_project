package com.example.urinoirapp.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "medecins")
public class Medecin{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "cin")
    private String cin;

    @Column(name = "email")
    private String email;

    @Column(name = "motDePasse")
    private String motDePasse;

    @Column(name = "numeroTelephone")
    private String numeroTelephone;

    public Medecin() {
    }


    @JoinColumn(name = "admin_id")
    private Long admin;

    public Medecin(String code, String nom, String prenom, String cin, String email, String motDePasse, String numeroTelephone) {

        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.motDePasse = motDePasse;
        this.numeroTelephone = numeroTelephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public Long getAdmin() {
        return admin;
    }

    public void setAdmin(Long admin) {
        this.admin = admin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
}


