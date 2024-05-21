package com.example.urinoirapp.service;

import com.example.urinoirapp.Model.Secretaire;


import java.util.List;

public interface SecretaireService {
    List<Secretaire> getAllSecretaire();

    Secretaire saveSecretaire(Secretaire secretaire);

    Secretaire getSecretaireById(Long id);

    Secretaire updateSecretaire(Secretaire secretaire);

    void deleteSecretaireById(Long id);

    long countSecretaire();
}


