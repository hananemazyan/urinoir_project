package com.example.urinoirapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.urinoirapp.Model.ReferenceData;
import com.example.urinoirapp.Service.ReferenceDataService;
import java.util.List;

@RestController
@RequestMapping("/api/reference-data")
public class ReferenceDataController {

    @Autowired
    private ReferenceDataService referenceDataService;

    public ReferenceDataController(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }

    @GetMapping("/all")
    public List<ReferenceData> getAllReferenceData() {
        return referenceDataService.getAllReferenceData();
    }
}
