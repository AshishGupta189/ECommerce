package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.UserSession;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Integer> {

	public UserSession findBySessionId(String id);
	
	public List<UserSession> findByUserId(Integer id);
	
}
