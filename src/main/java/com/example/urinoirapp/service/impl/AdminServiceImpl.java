package com.example.urinoirapp.service.impl;


import com.example.urinoirapp.Dto.AdminDto;
import com.example.urinoirapp.Model.Admin;
import com.example.urinoirapp.Repository.AdminRepository;
import com.example.urinoirapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
   private AdminRepository adminRepository;
@Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findByUsernname(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin FindByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin(adminDto.getUsername(),adminDto.getEmail(),passwordEncoder.encode(adminDto.getPassword()));
        return adminRepository.save(admin);
    }


}



