package com.example.urinoirapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.urinoirapp.Model.TestData;
import com.example.urinoirapp.Repository.TestDataRepository;

@Service
public class TestDataService {

    @Autowired
    private TestDataRepository testDataRepository;

    public TestData saveTestData(TestData testData) {
        return testDataRepository.save(testData);
    }
}
