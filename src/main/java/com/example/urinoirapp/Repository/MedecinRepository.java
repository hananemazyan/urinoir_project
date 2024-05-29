package com.example.urinoirapp.Repository;


import com.example.urinoirapp.Model.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

//    Medecin findByEmail(String email);

}
