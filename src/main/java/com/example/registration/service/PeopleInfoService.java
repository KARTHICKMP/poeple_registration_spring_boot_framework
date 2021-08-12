package com.example.registration.service;

import java.util.List;
import java.util.Optional;

import com.example.registration.model.PeopleInfo;

public interface PeopleInfoService {
	List<PeopleInfo> getAllPeopleInfo();
	PeopleInfo savePeopleInfo(PeopleInfo people_info);
	PeopleInfo getPeopleInfoById(long id);
	void deletePeopleInfoById(long id);
	Optional<PeopleInfo> findPeopleInforById(long id);
}
