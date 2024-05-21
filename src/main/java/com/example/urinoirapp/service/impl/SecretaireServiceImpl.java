package com.example.urinoirapp.service.impl;


import com.example.urinoirapp.Model.Secretaire;
import com.example.urinoirapp.Repository.SecretaireRepository;
import com.example.urinoirapp.service.SecretaireService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SecretaireServiceImpl implements SecretaireService {

    private SecretaireRepository secretaireRepository;

    public SecretaireServiceImpl(SecretaireRepository secretaireRepository) {
        this.secretaireRepository = Objects.requireNonNull(secretaireRepository, "secretaireRepository must not be null");
    }


    @Override
    public List<Secretaire> getAllSecretaire() {
        return secretaireRepository.findAll();
    }

    @Override
    public Secretaire saveSecretaire(Secretaire secretaire) {
        return secretaireRepository.save(secretaire);
    }

    @Override
    public Secretaire updateSecretaire(Secretaire secretaire) {
        return secretaireRepository.save(secretaire);
    }



    @Override
    public Secretaire getSecretaireById(Long id) {
        return secretaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Secretaire not found with id: " + id));
    }



    @Override
    public void deleteSecretaireById(Long id) {
        secretaireRepository.deleteById(id);
    }

    @Override
    public long countSecretaire() {
        return secretaireRepository.count();
    }
}
