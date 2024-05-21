package com.example.urinoirapp.service.impl;


import com.example.urinoirapp.Model.Medecin;
import com.example.urinoirapp.Repository.MedecinRepository;
import com.example.urinoirapp.service.MedecinService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecinServiceImpl implements MedecinService {

	private MedecinRepository medecinRepository;
	
	public MedecinServiceImpl(MedecinRepository medecinRepository) {
		super();
		this.medecinRepository = medecinRepository;
	}

	@Override
	public List<Medecin> getAllMedecins() {
		return medecinRepository.findAll();
	}

	@Override
	public Medecin saveMedecin(Medecin medecin) {
		return medecinRepository.save(medecin);
	}


	@Override
	public Medecin getMedecinById(Long id) {
		return medecinRepository.findById(id).get();
	}

	@Override
	public Medecin updateMedecin(Medecin medecin) {
		return medecinRepository.save(medecin);
	}

	@Override
	public void deleteMedecinById(Long id) {
		medecinRepository.deleteById(id);
	}

	@Override
	public long countMedecins() {
		return medecinRepository.count();
	}
}
