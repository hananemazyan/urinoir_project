package com.example.urinoirapp.Service;


import com.example.urinoirapp.Model.Patient;

import java.time.LocalDate;
import java.util.List;

public interface PatientService {
    List<Patient> getAllPatient();

   Patient savePatient(Patient patient);

    Patient getPatientById(Long id);

    Patient updatePatient(Patient patient);

    void deletePatientById(Long id);

    String generateSerialCode(String cin, LocalDate dateOfBirth);

    long countPatients();
}

