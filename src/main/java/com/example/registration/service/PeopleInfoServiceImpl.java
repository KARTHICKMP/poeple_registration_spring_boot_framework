package com.example.registration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.registration.model.PeopleInfo;
import com.example.registration.repository.PeopleInfoRepository;

@Service
public class PeopleInfoServiceImpl implements PeopleInfoService{

	@Autowired
	private PeopleInfoRepository peopleRepo;
	
	@Override
	public List<PeopleInfo> getAllPeopleInfo() {
		return peopleRepo.findAll();
	}

	@Override
	public PeopleInfo savePeopleInfo(PeopleInfo people_info) {
		PeopleInfo people = peopleRepo.save(people_info);
		return people;
		
	}

	@Override
	public PeopleInfo getPeopleInfoById(long id) {
		Optional<PeopleInfo> optional = peopleRepo.findById(id);
		PeopleInfo peopleInfo = null;
		if (optional.isPresent()) {
			peopleInfo = optional.get();
		} else {
			throw new RuntimeException("People Info not found with id :"+id);
		}
		return peopleInfo;
	}

	@Override
	public void deletePeopleInfoById(long id) {
		this.peopleRepo.deleteById(id);
		
	}

	@Override
	public Optional<PeopleInfo> findPeopleInforById(long id) {
		return peopleRepo.findById(id);
	}
}
