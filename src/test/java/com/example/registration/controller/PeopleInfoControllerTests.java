package com.example.registration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.registration.model.PeopleInfo;
import com.example.registration.repository.PeopleInfoRepository;
import com.example.registration.service.PeopleInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest()
@TestMethodOrder(OrderAnnotation.class)
public class PeopleInfoControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PeopleInfoService peopleInfoService;
	
	@MockBean
	private PeopleInfoRepository peopleInfoRepo;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testListAllPeopleInfo() throws Exception{
		@SuppressWarnings("deprecation")
		java.sql.Date sqlDate = new java.sql.Date(1992,02,15);
		List<PeopleInfo> peopleInfoList = new ArrayList<>();
		peopleInfoList.add(new PeopleInfo((long) 1, "Karthick", "Prasannakumar", 
				"Mallapuram", sqlDate, "Bangalore", "India"));
		peopleInfoList.add(new PeopleInfo((long) 2, "Kushal", "Lakshmi", 
				"Thalanki", sqlDate, "Bangalore", "India"));
		
		Mockito.when(peopleInfoService.getAllPeopleInfo()).thenReturn(peopleInfoList);
		String url = "/get_all_registered_people";
		mockMvc.perform(get(url)).andExpect(status().isOk());
		
//		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
//		System.out.println(mvcResult);
//		byte[] bytes = mvcResult.getResponse().getContentAsByteArray();
//		Path path = Paths.get("peopleinfo.xls");
//		Files.write(path, bytes);
	}
	
	@Test
	@Rollback(false)
	@Order(2)
	public void testGetPeopleInfoById() throws Exception {
		@SuppressWarnings("deprecation")
		java.sql.Date sqlDate = new java.sql.Date(1992,02,15);
		PeopleInfo peopleInfobyId = new PeopleInfo((long) 1, "Karthick", "Prasannakumar", 
				"Mallapuram", sqlDate, "Bangalore", "India");
		
		Mockito.when(peopleInfoService.getPeopleInfoById((long) 1)).thenReturn(peopleInfobyId);
		String url = "/get_people_info/{id}";
		
		mockMvc.perform(MockMvcRequestBuilders.get(url,"1")).andExpect(status().isOk());
		
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url,"1")).andExpect(status().isOk()).andReturn();
//		System.out.println(mvcResult);
//		byte[] bytes = mvcResult.getResponse().getContentAsByteArray();
//		Path path = Paths.get("peopleinfo.xls");
//		Files.write(path, bytes);
		
	}
	
//	@Test
//	@Order(3)
//	public void testDeletePeopleInfoByID() throws Exception {
//		@SuppressWarnings("deprecation")
//		java.sql.Date sqlDate = new java.sql.Date(1992,02,15);
//		List<PeopleInfo> peopleInfoList = new ArrayList<>();
//		peopleInfoList.add(new PeopleInfo((long) 1, "Karthick", "Prasannakumar", 
//				"Mallapuram", sqlDate, "Bangalore", "India"));
//		peopleInfoList.add(new PeopleInfo((long) 2, "Kushal", "Lakshmi", 
//				"Thalanki", sqlDate, "Bangalore", "India"));
//		
//		Mockito.when(peopleInfoService.getAllPeopleInfo()).thenReturn(peopleInfoList);
//		String url = "/get_all_registered_people";
//		mockMvc.perform(get(url)).andExpect(status().isOk());
//		
//		url = "/delete_people_info/{id}";
//		mockMvc.perform(MockMvcRequestBuilders.delete(url,"2")).andExpect(status().isOk());
//	}
	
//	public void testUpdatePeopleInfoById() throws Exception {
//		@SuppressWarnings("deprecation")
//		java.sql.Date sqlDate = new java.sql.Date(1992,02,15);
//		PeopleInfo peopleInfobyId = new PeopleInfo((long) 1, "KarthickMP", "Prasannakumar", 
//				"Mallapuram", sqlDate, "Bangalore", "India");
//		
//		Mockito.when(peopleInfoService.getPeopleInfoById((long) 1)).thenReturn(peopleInfobyId);
//		String url = "/update_peopleinfo/{id}";
//		
//		mockMvc.perform(MockMvcRequestBuilders.put(url,"1")
//				.content("{\"id\":1,\"firstName\":\"Harika\",\"middleName\":\"dasd\",\"familyName\":\"Sidhula\",\"dob\":\"1994-08-15\","
//						+ "\"placeOfBirth\":\"Manadanapalli\",\"nationality\":\"India\"}")).andExpect(status().isOk());
//		
//	}
}
