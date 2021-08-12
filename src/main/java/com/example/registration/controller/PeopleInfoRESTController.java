package com.example.registration.controller;

import java.rmi.ServerException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.registration.model.PeopleInfo;
import com.example.registration.service.PeopleInfoService;

@RestController
public class PeopleInfoRESTController {

	@Autowired private PeopleInfoService peopleInfoService;
	
	@GetMapping("/get_all_registered_people")
	public List<PeopleInfo> getAllUsers() {
		return peopleInfoService.getAllPeopleInfo();
	}
	
	@GetMapping(value="/get_people_info/{id}")
	public PeopleInfo getUserById(@PathVariable (value = "id") long id) {
		return peopleInfoService.getPeopleInfoById(id);
	}
	
	@PostMapping(value="/new_registration")
	public ResponseEntity<PeopleInfo> createNewPeopleInfo(@RequestBody Map<String, Object> payload) throws ServerException {
		PeopleInfo people_info = new PeopleInfo();
		System.out.println(payload);
		people_info.setFirstName((String) payload.get("first_name"));
		people_info.setMiddleName((String) payload.get("middle_name"));
		people_info.setFamilyName((String) payload.get("family_name"));
		people_info.setdOB(Date.valueOf((String) payload.get("dob")));
		people_info.setPlaceOfBirth((String) payload.get("place_of_birth"));
		people_info.setNationality((String) payload.get("nationality"));
		PeopleInfo ret_val = peopleInfoService.savePeopleInfo(people_info);
	    if (ret_val == null) {
	        throw new ServerException("Not able Register User info");
	    } else {
	        return new ResponseEntity<>(ret_val, HttpStatus.CREATED);
	    }
	}
	
	@PutMapping(value="/update_peopleinfo/{id}", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<PeopleInfo> updatePersonInfo(@PathVariable (value = "id") long id, @RequestBody Map<String, Object> payload) throws ServerException {
		PeopleInfo people_info = new PeopleInfo();
		boolean isIdExsistBeforeUpdate = peopleInfoService.findPeopleInforById(id).isPresent();
		if (isIdExsistBeforeUpdate) {
			people_info.setId(id);
			people_info.setFirstName((String) payload.get("first_name"));
			people_info.setMiddleName((String) payload.get("middle_name"));
			people_info.setFamilyName((String) payload.get("family_name"));
			people_info.setdOB(Date.valueOf((String) payload.get("dob")));
			people_info.setPlaceOfBirth((String) payload.get("place_of_birth"));
			people_info.setNationality((String) payload.get("nationality"));
			PeopleInfo ret_val = peopleInfoService.savePeopleInfo(people_info);
		    if (ret_val == null) {
		        throw new ServerException("Not able Update info");
		    } else {
		        return new ResponseEntity<>(ret_val, HttpStatus.OK);
		    }
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping(value="/delete_people_info/{id}")
	public ResponseEntity<Integer> deletePersonInfoById(@PathVariable (value = "id") long id) {
		
		List<PeopleInfo> val = peopleInfoService.getAllPeopleInfo();
		boolean isIdExsistBeforeDelete = peopleInfoService.findPeopleInforById(id).isPresent();
		if (isIdExsistBeforeDelete) {
			peopleInfoService.deletePeopleInfoById(id);
			return new ResponseEntity<>(1, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
		}
	}
	
}
