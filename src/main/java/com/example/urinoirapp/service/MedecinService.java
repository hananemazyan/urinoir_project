package com.example.urinoirapp.service;


import com.example.urinoirapp.Model.Medecin;

import java.util.List;

public interface MedecinService {
	List<Medecin> getAllMedecins();
	
	Medecin saveMedecin(Medecin medecin);
	
	Medecin getMedecinById(Long id);
	
	Medecin updateMedecin(Medecin medecin);
	
	void deleteMedecinById(Long id);

	long countMedecins() ;
}
