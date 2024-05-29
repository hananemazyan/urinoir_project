package com.example.urinoirapp.Controller;


import com.example.urinoirapp.Model.Patient;
import com.example.urinoirapp.Model.TestData;
import com.example.urinoirapp.Repository.PatientRepository;
import com.example.urinoirapp.Repository.TestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TicketController {

    @Autowired
    private TestDataRepository testDataRepository; // Assuming you have a TestDataRepository

    @Autowired
    private PatientRepository patientRepository; // Assuming you have a PatientRepository

    @GetMapping("/fetchTicketInfo")
    public ResponseEntity<Map<String, Object>> fetchTicketInfo(
            @RequestParam("patientId") Long patientId,
            @RequestParam("testId") Long testId) {
        try {
            // Fetch patient information
            Optional<Patient> optionalPatient = patientRepository.findById(patientId);
            if (!optionalPatient.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            Patient patient = optionalPatient.get();

            // Fetch test data
            Optional<TestData> optionalTestData = testDataRepository.findById(testId);
            if (!optionalTestData.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            TestData testData = optionalTestData.get();

            // Calculate statistics
            List<TestData> testDataList = testDataRepository.findByPatientIdAndTestId(patientId,  testId);
            double maxVolume = testDataList.stream().mapToDouble(TestData::getVolume).max().orElse(0);
            double minVolume = testDataList.stream().mapToDouble(TestData::getVolume).min().orElse(0);
            double avgVolume = testDataList.stream().mapToDouble(TestData::getVolume).average().orElse(0);

            // Populate ticket information
            Map<String, Object> ticketInfo = new HashMap<>();

            ticketInfo.put("patientName", patient.getFirstname());
            ticketInfo.put("patientLastName", patient.getLastname());
            ticketInfo.put("deviceId", testData.getDevice().getId()); // Assuming you have a method to retrieve device ID from TestData
            ticketInfo.put("testDate", testData.getTestDate());
            ticketInfo.put("maxVolume", maxVolume);
            ticketInfo.put("minVolume", minVolume);
            ticketInfo.put("avgVolume", avgVolume);

            return ResponseEntity.ok(ticketInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}