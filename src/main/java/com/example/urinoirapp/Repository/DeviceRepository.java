package com.example.urinoirapp.Repository;


import com.example.urinoirapp.Model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findById(long id);
}
