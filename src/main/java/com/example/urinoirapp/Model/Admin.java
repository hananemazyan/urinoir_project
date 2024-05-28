package com.example.urinoirapp.Model;


import com.example.urinoirapp.Dto.AdminDto;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;


@Entity
@Table(name = "admins")
public class Admin extends AdminDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullname;

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DateOfBirth;


    private String tel;
    private String address;

    @Column(name = "email", unique = true)
    private String email;


    @Column(name = "password")
    private String password;

    public Admin(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Admin() {
    }
    @Column(name = "role")
    private String role = "admin"; // Rôle par défaut
    @OneToMany(mappedBy = "admin")
    private List<Secretaire> secretaires;

    @OneToMany(mappedBy = "admin")
    private List<Medecin> medecins;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Secretaire> getSecretaires() {
        return secretaires;
    }

    public void setSecretaires(List<Secretaire> secretaires) {
        this.secretaires = secretaires;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Date getDateOfBirth() {
        return this.DateOfBirth;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }



    public String getTel() {
        return this.tel;
    }

    public void setTel(final String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }


    }