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

import com.example.Entity.Category;
import com.example.Entity.Orders;
import com.example.Entity.Product;
import com.example.Entity.User;
import com.example.Exceptions.CategoryException;
import com.example.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<Orders>> getAllOrders() {
		List<Orders> result = adminService.getAllOrders();
	    if(result==null) {
	    	
	    }
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> result = null;
		try {
			result = adminService.getAllCategory();
		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> result = adminService.getAllUsers();
	    if(result==null) {
	    	
	    }
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> result = adminService.getAllProducts();
	    if(result==null) {
	    	
	    }
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUserHandler(@RequestParam Integer id,@RequestBody User user){
		String a=adminService.updateProfile(id,user);
		return new ResponseEntity<String>(a, HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword/{id}")
	public ResponseEntity<String> updatePasswordHandler(@PathVariable("id") Integer id,@RequestParam String password){
		String a=adminService.changePassword(id,password);
		return new ResponseEntity<String>(a, HttpStatus.OK);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody Product product)	{
		String a=adminService.addProduct(product);
		return new ResponseEntity<String>(a, HttpStatus.CREATED);
	}
	
	@PostMapping("/addCategory")
	public ResponseEntity<String> addProduct(@RequestBody Category category)	{
		String a=adminService.addCategory(category);
		return new ResponseEntity<String>(a, HttpStatus.CREATED);
	}


}
