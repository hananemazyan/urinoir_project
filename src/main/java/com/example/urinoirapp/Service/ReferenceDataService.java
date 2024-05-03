package com.example.urinoirapp.Service;


import com.example.urinoirapp.Model.ReferenceData;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing reference data.
 */
@Service
public interface ReferenceDataService {

    /**
     * Saves a list of reference data.
     *
     * @param referenceDataList The list of reference data to be saved.
     */
    void saveReferenceData(List<ReferenceData> referenceDataList);

    /**
     * Retrieves all reference data stored in the system.
     *
     * @return A list of all reference data stored in the system.
     */
    List<ReferenceData> getAllReferenceData();
}