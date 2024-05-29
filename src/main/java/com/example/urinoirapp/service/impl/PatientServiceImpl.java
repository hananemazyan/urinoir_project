package com.example.urinoirapp.Service.impl;


import com.example.urinoirapp.Model.Patient;
import com.example.urinoirapp.Repository.PatientRepository;
import com.example.urinoirapp.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient savePatient(Patient patient) {
// Generate a unique serial code
        String serialCode = generateSerialCode(patient.getCIN (), patient.getDateOfBirth());
        patient.setSerialCode(generateSerialCode(patient.getCIN(), patient.getDateOfBirth()));
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).get();
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
//    public String generateSerialCode(String cin, LocalDate dateOfBirth) {
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String formattedDateOfBirth = dateOfBirth.format(dateFormatter);
//        int randomNum = new Random().nextInt(1000); // Random number between 0 and 999
//        return cin + formattedDateOfBirth + String.format("%03d", randomNum);
//    }


    public String generateSerialCode(String cin, LocalDate dateOfBirth) {
        if (cin == null || dateOfBirth == null) {
            throw new IllegalArgumentException("CIN and date of birth cannot be null");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = dateOfBirth.format(formatter);
        return cin +  formattedDate;
    }


    @Override
    public long countPatients() {
        return patientRepository.count();
    }
}











//    @Autowired
//    private PatientRepository patientRepository;
//
//    public Patient savePatient(Patient patient) {
//        // Générer un code série unique
//        String codeSerie = generateUniqueCodeSerie();
//        patient.setCodeSerie(codeSerie);
//
//        Patient savedPatient = patientRepository.save(patient);
//
//        // Générer le code QR avec le code série
//        try {
//            String qrCodePath = "path/to/qrcode_" + savedPatient.getId() + ".png";
//            QRCodeGenerator.generateQRCodeImage(codeSerie, 200, 200, qrCodePath);
//
//            // Générer le PDF avec le code QR
//            String pdfPath = "path/to/qrcode_" + savedPatient.getId() + ".pdf";
//            PDFGenerator.generatePDFWithQRCode(qrCodePath, pdfPath);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return savedPatient;
//    }
//
//    private String generateUniqueCodeSerie() {
//        // Logique pour générer un code série unique
//        return UUID.randomUUID().toString(); // Exemple simple utilisant UUID
//    }

    // autres méthodes nécessaires

