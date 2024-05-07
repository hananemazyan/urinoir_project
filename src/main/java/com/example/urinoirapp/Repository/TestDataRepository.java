package com.example.urinoirapp.Repository;


import com.example.urinoirapp.Model.TestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDataRepository extends JpaRepository<TestData, Long> {
}
