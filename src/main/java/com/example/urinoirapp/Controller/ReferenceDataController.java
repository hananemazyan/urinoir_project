package com.example.urinoirapp.Controller;

import com.example.urinoirapp.Model.ReferenceData;
import com.example.urinoirapp.Service.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/api/referenceData")
public class ReferenceDataController {

    @Autowired
    private ReferenceDataService referenceDataService;

    @GetMapping
    public List<ReferenceData> getReferenceData() {
        return referenceDataService.getAllReferenceData();
    }
}
