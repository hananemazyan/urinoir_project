package com.example.urinoirapp.Model;


import com.example.urinoirapp.Dto.AdminDto;
import jakarta.persistence.*;
import java.util.List;



@Entity
@Table(name = "admins")
public class Admin extends AdminDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

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

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
