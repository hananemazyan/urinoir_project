package com.example.urinoirapp.Service;


import com.example.urinoirapp.Model.Device;
import com.example.urinoirapp.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device retrieveDeviceById(long deviceId) {
        return deviceRepository.findById(deviceId);
    }
}
