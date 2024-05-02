package com.example.urinoirapp.Model;


import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rapports")
public class Rapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreation;
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @ManyToMany
    @JoinTable(
            name = "rapport_secretaires",
            joinColumns = @JoinColumn(name = "rapport_id"),
            inverseJoinColumns = @JoinColumn(name = "secretaire_id")
    )
    private List<Secretaire> secretaires;

    @OneToMany(mappedBy = "rapport")
    private List<RapportDetails> rapportDetails;
    @ManyToOne
    private Patient patients;
    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public List<Secretaire> getSecretaires() {
        return secretaires;
    }

    public void setSecretaires(List<Secretaire> secretaires) {
        this.secretaires = secretaires;
    }

    public List<RapportDetails> getRapportDetails() {
        return rapportDetails;
    }

    public void setRapportDetails(List<RapportDetails> rapportDetails) {
        this.rapportDetails = rapportDetails;
    }
}

