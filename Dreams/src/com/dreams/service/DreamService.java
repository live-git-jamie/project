package com.dreams.service;

import java.util.List;

import com.dreams.model.Dream;
import com.dreams.repository.DreamRepository;

public class DreamService {

	public boolean saveDream(Dream dream) {
		DreamRepository dreamRepository = new DreamRepository();
		return dreamRepository.saveDream(dream);
	}
	
	public boolean deleteDream(String date) {
		DreamRepository dreamRepository = new DreamRepository();
		return dreamRepository.deleteDream(date);
	}
	
	public List<Dream> searchDatabase(String stringToSearch) {
		DreamRepository dreamRepository = new DreamRepository();
		return dreamRepository.searchDatabase(stringToSearch);
	}
}
