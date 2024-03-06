package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Exceptions.CategoryException;
import com.example.Exceptions.UserException;
import com.example.Model.Admin;
import com.example.Model.Category;
import com.example.Model.LoginDTO;
import com.example.Model.Orders;
import com.example.Model.Product;
import com.example.Model.User;
import com.example.Service.AdminService;
import com.example.Service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private LoginService logS;
	
	@PostMapping("/login")
	public ResponseEntity<String> logInAdminHandler(@Valid @RequestBody LoginDTO dto) throws UserException {
		LoginDTO ldto = new LoginDTO(dto.getPhonenumber(), dto.getPassword());
		String result = logS.loginAdmin(ldto);
		return new ResponseEntity<String>(result,HttpStatus.OK );
	}
	
	@PostMapping("/logout")
	public String logoutAdminHandler(@Valid @RequestParam String sessionid) throws UserException {
		return logS.logOut(sessionid);
	}
	
	@PostMapping("/registeradmin")
	public ResponseEntity<Admin> createAdminHandler(@Valid @RequestBody Admin admin)	{
		Admin a=adminService.createAdmin(admin);
		return new ResponseEntity<Admin>(a, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<Orders>> getAllOrders(@Valid @RequestParam String sessionid) {
		List<Orders> result = adminService.getAllOrders(sessionid);
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> result = adminService.getAllCategory();
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUsers(@Valid @RequestParam String sessionid) {
		List<User> result = adminService.getAllUsers(sessionid);
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> result = adminService.getAllProducts();
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUserHandler(@Valid @RequestParam(required = false) String email,@RequestParam(required = false) String mobileNumber,@RequestParam(required = false) String picture,@RequestParam(required = false) String username,@RequestParam String sessionid){
		String a=adminService.updateProfile(email,mobileNumber,username,picture,sessionid);
		return new ResponseEntity<String>(a, HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword/{id}")
	public ResponseEntity<String> updatePasswordHandler(@Valid @PathVariable("id") Integer id,@RequestParam String password){
		String a=adminService.changePassword(id,password);
		return new ResponseEntity<String>(a, HttpStatus.OK);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@Valid @RequestBody Product product,@RequestParam String sessionid)	{
		String a=adminService.addProduct(product,sessionid);
		return new ResponseEntity<String>(a, HttpStatus.CREATED);
	}
	
	@PostMapping("/addCategory")
	public ResponseEntity<String> addProduct(@Valid @RequestBody Category category,@RequestParam String sessionid)	{
		String a=adminService.addCategory(category,sessionid);
		return new ResponseEntity<String>(a, HttpStatus.CREATED);
	}


}
