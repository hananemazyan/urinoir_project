package com.example.urinoirapp.Service;

import com.example.urinoirapp.Model.ReferenceData;
import com.example.urinoirapp.Repository.ReferenceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceDataService {

    @Autowired
    private ReferenceDataRepository repository;

    public String saveReferenceData(ReferenceData referenceData) {
        repository.save(referenceData);
        return "saved Data ";
    }

    public List<ReferenceData> getAllReferenceData() {
        return repository.findAll();
    }
}
