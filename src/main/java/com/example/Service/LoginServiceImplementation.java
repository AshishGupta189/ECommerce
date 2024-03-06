package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exceptions.UserException;
import com.example.Model.Admin;
import com.example.Model.LoginDTO;
import com.example.Model.User;
import com.example.Model.UserSession;
import com.example.Repository.AdminRepository;
import com.example.Repository.UserRepository;
import com.example.Repository.UserSessionRepository;


	
	@Service
	public class LoginServiceImplementation implements LoginService{

		 @Autowired
		 private AdminRepository usera;
		
		 @Autowired
		 private UserSessionRepository usersession;
		 
		 @Autowired
		 private UserRepository userRepo;

		 @Override
		 public String logOut(String key)throws UserException {
		 	UserSession us =  usersession.findBySessionId(key);
		 	if(us != null) {
		 		usersession.delete(us);
		 		return "Logged Out!";
		 	}else throw new UserException("Error Occured Unable to log out !");
			
		 }

		 @Override
		 public String loginAdmin(LoginDTO dto) throws UserException {
			
		 	
			

				
		 		Admin admin = usera.findByMobileNumber(dto.getPhonenumber());
		 		if(admin == null) {
		 			throw new UserException("Please Enter a valid mobile number");
		 		}
		 		
		 		List<UserSession> currentUserSession =usersession.findByUserId(admin.getAdminId());
		 		for(UserSession us : currentUserSession) {
		 			if(us!=null && us.getRole().equals("Admin")) {
			 			throw new UserException("Admin already Logged In with this number");
			 		}
		 		}
		 		
		 	    if(admin.getPassword().equals(dto.getPassword())) {
					
		 			String key= RandomString.make(8);
					
		 			 UserSession uss = new UserSession(admin.getAdminId(), key, LocalDateTime.now(),"Admin");
					
		 			 usersession.save(uss);
		 			 return uss.toString();
		 	    }
		 	    else
		 			throw new UserException("Please Enter a valid password");
			 
				


		 }
		 
		 @Override
		 public String loginUser(LoginDTO dto) throws UserException {
			
		 	
			

				
		 		User user = userRepo.findByMobileNumber(dto.getPhonenumber());
		 		if(user == null) {
		 			throw new UserException("Please Enter a valid mobile number");
		 		}
		 		
		 		List<UserSession> currentUserSession =usersession.findByUserId(user.getUserId());
		 		for(UserSession us : currentUserSession) {
		 			if(us!=null && us.getRole().equals("User")) {
			 			throw new UserException("User already Logged In with this number");
			 		}
		 		}
		 	    if(user.getPassword().equals(dto.getPassword())) {
					
		 			String key= RandomString.make(8);
					
		 			 UserSession uss = new UserSession(user.getUserId(), key, LocalDateTime.now(),"User");
					
		 			 usersession.save(uss);
		 			 return uss.toString();
		 	    }
		 	    else
		 			throw new UserException("Please Enter a valid password");
			 
				


		 }
		 
}

		


