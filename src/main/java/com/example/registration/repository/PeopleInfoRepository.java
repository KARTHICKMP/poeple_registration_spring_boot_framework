package com.example.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.registration.model.PeopleInfo;

@Repository
public interface PeopleInfoRepository extends JpaRepository<PeopleInfo, Long>{

}
