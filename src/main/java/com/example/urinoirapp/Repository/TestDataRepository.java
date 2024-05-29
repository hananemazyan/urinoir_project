package com.example.urinoirapp.Repository;

import com.example.urinoirapp.Model.TestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDataRepository extends JpaRepository<TestData, Long> {

    List<TestData> findByPatientId(Long patientId);

    // Method to find test data for a specific patient and test
    List<TestData> findByPatientIdAndTestId(Long patientId, Long testId);

    // Method to find the maximum test ID in the database
    @Query("SELECT MAX(t.testId) FROM TestData t")
    Long findMaxTestId();
    TestData findByTestId(Long testId);
}
