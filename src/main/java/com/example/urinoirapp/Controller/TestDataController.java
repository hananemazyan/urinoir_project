package com.example.urinoirapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.urinoirapp.Model.TestData;
import com.example.urinoirapp.Service.TestDataService;

@RestController
public class TestDataController {

    @Autowired
    private TestDataService testDataService;

    @PostMapping("/testdata")
    public String addTestData(@RequestBody TestData testData) {
        return testDataService.saveTestData(testData).toString();
    }
}

