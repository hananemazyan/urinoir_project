package com.example.urinoirapp.Service;


import com.example.urinoirapp.Dto.AdminDto;
import com.example.urinoirapp.Model.Admin;

import java.util.List;

// Interface définissant les opérations disponibles pour gérer les entités Admin
public interface AdminService {


//    Admin findByUsernname(String username);

    // Recherche un administrateur par son adresse e-mail
    // Retourne l'objet Admin correspondant à l'adresse e-mail spécifiée


//    List<Admin> findByUsername(String username);



    Admin FindByEmail(String Email);


    // Enregistre un nouvel administrateur dans la base de données
    // Prend un objet Admin en paramètre et l'enregistre dans la base de données

    Admin save(AdminDto adminDto);


    Admin findByUsername(String username);
}
