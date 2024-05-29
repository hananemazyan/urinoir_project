package com.example.urinoirapp.Service;


import com.example.urinoirapp.Model.TestData;
import com.example.urinoirapp.Repository.TestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDataService {

    @Autowired
    private TestDataRepository testDataRepository;

    public void saveTestData(TestData testData) {
        testDataRepository.save(testData);
    }

    public List<TestData> getAllTestData() {
        return testDataRepository.findAll();
    }

    public List<TestData> getAllTestDataForPatient(Long patientId) {
        return testDataRepository.findByPatientId(patientId);
    }

    public Long generateNewTestId() {
        // This method generates a new testId based on the current highest testId in the database
        Long maxTestId = testDataRepository.findMaxTestId();
        return (maxTestId == null) ? 1 : maxTestId + 1;
    }

    // New method to fetch test data for a specific patient and test
    public List<TestData> getAllTestDataForPatientAndTest(Long patientId, Long testId) {
        return testDataRepository.findByPatientIdAndTestId(patientId, testId);
    }
    public Long getPatientIdByTestId(Long testId) {
        TestData testData = testDataRepository.findById(testId).orElse(null);
        if (testData != null) {
            return testData.getPatient().getId();
        }
        return null; // or throw an exception if necessary
    }
    public TestData getTestDataById(Long testId) {
        return testDataRepository.findByTestId(testId);
    }

}
