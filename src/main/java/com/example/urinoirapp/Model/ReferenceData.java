package com.example.urinoirapp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reference_data")
public class ReferenceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double volume; // Volume urine en millilitres

    private double second; // Temps associé au volume (en seconds)

    // Constructeur par défaut
    public ReferenceData() {
    }

    // Constructeur avec paramètres
    public ReferenceData(double volume, double second) {
        this.volume = volume;
        this.second = second;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }
}
