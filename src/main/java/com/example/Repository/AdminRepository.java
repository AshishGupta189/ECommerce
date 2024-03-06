package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public Admin findByMobileNumber(String phone);
}
