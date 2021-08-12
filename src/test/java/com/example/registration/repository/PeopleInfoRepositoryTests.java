package com.example.registration.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.registration.model.PeopleInfo;
import com.example.registration.repository.PeopleInfoRepository;


@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class PeopleInfoRepositoryTests {

	@Autowired
	private PeopleInfoRepository peopleInfoRepo;


	@Test
	@Rollback(false)
	@Order(1)
	public void testCreatePeopleInfo() {
		@SuppressWarnings("deprecation")
		java.sql.Date sqlDate = new java.sql.Date(1992,02,15);
		PeopleInfo peopleInfo = new PeopleInfo((long) 1, "Karthick", "Prasannakumar", 
				"Mallapuram", sqlDate, "Bangalore", "India");
		PeopleInfo ret_val = peopleInfoRepo.save(peopleInfo);

		assertNotNull(ret_val);
		assertTrue(ret_val.getId() > 0);
		System.out.println(ret_val.getId());

	}
	
	@Test
	@Order(2)
	public void testGetPeopleInfoByID() {
		List<PeopleInfo> val = peopleInfoRepo.findAll();
		System.out.println(val);
		PeopleInfo ret_val = peopleInfoRepo.getById((long) 1);
		
		assertNotNull(ret_val);
	}

	@Test
	@Order(3)
	public void testUpdatePeopleInfoByID() {
		String new_name = "KarthickMP";
		@SuppressWarnings("deprecation")
		java.sql.Date sqlDate = new java.sql.Date(1992,02,15);
		PeopleInfo peopleInfo = new PeopleInfo((long) 1, new_name, "Prasannakumar", 
				"Mallapuram", sqlDate, "Bangalore", "India");
		PeopleInfo ret_val = peopleInfoRepo.save(peopleInfo);
		
		assertEquals(ret_val.getFirstName(), new_name);
	}
	
	@Test
	@Order(4)
	public void testDeletePeopleInfoByID() {
		Long id = (long) 1;
		boolean isIdExsistBeforeDelete = peopleInfoRepo.findById(id).isPresent();
		assertTrue(isIdExsistBeforeDelete);
		peopleInfoRepo.deleteById((long) 1);
		boolean isIdExsistAfterDelete = peopleInfoRepo.findById(id).isPresent();
		assertFalse(isIdExsistAfterDelete);
		
	}
	
	
}
