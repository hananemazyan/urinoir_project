package com.example.urinoirapp.Repository;


import com.example.urinoirapp.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT COUNT(p) FROM Patient p")
    long countPatients();

    boolean existsByCIN(String cin);

}
