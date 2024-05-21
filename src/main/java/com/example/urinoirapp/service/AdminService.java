package com.example.urinoirapp.service;


import com.example.urinoirapp.Dto.AdminDto;
import com.example.urinoirapp.Model.Admin;

// Interface définissant les opérations disponibles pour gérer les entités Admin
public interface AdminService {

    // Recherche un administrateur par son nom d'utilisateur
    // Retourne l'objet Admin correspondant au nom d'utilisateur spécifié

    Admin findByUsernname(String username);

    // Recherche un administrateur par son adresse e-mail
    // Retourne l'objet Admin correspondant à l'adresse e-mail spécifiée

    Admin FindByEmail(String Email);

    // Enregistre un nouvel administrateur dans la base de données
    // Prend un objet Admin en paramètre et l'enregistre dans la base de données

    Admin save(AdminDto adminDto);


}
