package com.example.Service;

import com.example.Exceptions.UserException;
import com.example.Model.LoginDTO;

public interface LoginService {

	 public String logOut(String key)throws UserException;
	
	 public String loginAdmin(LoginDTO ldto)throws UserException;

	 public String loginUser(LoginDTO dto) throws UserException;
	 
	 

	
}