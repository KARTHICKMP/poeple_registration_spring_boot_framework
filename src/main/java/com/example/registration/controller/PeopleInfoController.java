package com.example.registration.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.registration.model.PeopleInfo;
import com.example.registration.service.PeopleInfoService;


@Controller
public class PeopleInfoController {
	
	@Autowired
	private PeopleInfoService peopleInfoService;

	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	// display list of employees
	@GetMapping("/people_list")
	public String viewHomePage(Model model) {
		model.addAttribute("listPeople", peopleInfoService.getAllPeopleInfo());
		return "people_list";
	}
	
	@GetMapping("/register")
	public String registerNewUser(Model model) {
		model.addAttribute("person", new PeopleInfo());
		return "registration_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(PeopleInfo person) {
		peopleInfoService.savePeopleInfo(person);
		return "redirect:/people_list";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
		PeopleInfo person_info = peopleInfoService.getPeopleInfoById(id);
		model.addAttribute("person_info", person_info);
		return "update_person_info";
	}
	
	@RequestMapping(value="/delete/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
	public String deletePersonInfo(@PathVariable (value = "id") long id) {
		this.peopleInfoService.deletePeopleInfoById(id);
		return "redirect:/people_list";
	}
	

}
