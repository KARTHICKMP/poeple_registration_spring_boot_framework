package com.example.registration.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.registration.repository.PeopleInfoRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class PeopleInfoServiceTest {
	
	@MockBean
	private PeopleInfoRepository peopleInfoRepo;
	
	@InjectMocks
	private PeopleInfoServiceImpl peopleInfoService;
	
	// test can be included only for business layer logic verification test
	// e.g. if a verification is inlcuded to find uniqueness of the column is used

	// as of now no test are included as service layer as not business logic to talk
}
