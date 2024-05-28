package com.example.urinoirapp.Repository;


import com.example.urinoirapp.Model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDataRepository extends JpaRepository<Test, Long> {

    List<Test> findByPatientId(Long patientId);

    // Method to find test data for a specific patient and test
    List<Test> findByPatientIdAndTestId(Long patientId, Long testId);

    // Method to find the maximum test ID in the database
    @Query("SELECT MAX(t.testId) FROM Test t")
    Long findMaxTestId();
}
