package com.example.urinoirapp.Service;

import com.example.urinoirapp.Model.ReferenceData;
import com.example.urinoirapp.Repository.ReferenceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceDataServiceImpl implements ReferenceDataService {

    @Autowired
    private ReferenceDataRepository referenceDataRepository;

    @Override
    public void saveReferenceData(List<ReferenceData> referenceDataList) {
        referenceDataRepository.saveAll(referenceDataList);
    }

    @Override
    public List<ReferenceData> getAllReferenceData() {
        return referenceDataRepository.findAll();
    }
}

