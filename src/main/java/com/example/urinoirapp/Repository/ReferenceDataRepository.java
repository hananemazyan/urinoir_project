package com.example.urinoirapp.Repository;


import com.example.urinoirapp.Model.ReferenceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ReferenceDataRepository extends JpaRepository<ReferenceData, Long> {

}

