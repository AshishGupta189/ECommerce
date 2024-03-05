package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Cart;
import com.example.Entity.Orders;
import com.example.Entity.Product;
import com.example.Entity.User;
import com.example.Exceptions.CartException;
import com.example.Exceptions.OrderException;
import com.example.Exceptions.ProductException;
import com.example.Exceptions.UserException;
import com.example.Service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService uService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> createUserHandler(@RequestBody User user)	{
		String a = null;
		try {
			a = uService.registerUser(user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(a, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUserHandler(@RequestParam Integer id,@RequestBody User user){
		String a = null;
		try {
			a = uService.updateProfile(id,user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(a, HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword/{id}")
	public ResponseEntity<String> updatePasswordHandler(@PathVariable("id") Integer id,@RequestParam String password){
		String a = null;
		try {
			a = uService.changePassword(id,password);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(a, HttpStatus.OK);
	}

	@PostMapping("/addToCart/{userId}")
	public ResponseEntity<String> addToCartHandler(@PathVariable("userId") Integer userId, @RequestParam Integer pId) {
	    String result = null;
		try {
			result = uService.addToCart(userId, pId);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/removeFromCart/{userId}")
	public ResponseEntity<String> removefromCartHandler(@PathVariable("userId") Integer userId, @RequestParam Integer pId) {
	    String result = null;
		try {
			result = uService.removeFromCart(userId, pId);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/showCart/{userId}")
	public ResponseEntity<Cart> showCart(@PathVariable("userId") Integer userId) {
	    Cart result = null;
		try {
			result = uService.showCart(userId);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getOrder/{userId}")
	public ResponseEntity<List<Orders>> getAllOrders(@PathVariable("userId") Integer userId) {
		List<Orders> result = null;
		try {
			result = uService.getOrder(userId);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/placeOrder/{userId}")
	public ResponseEntity<String> placeOrders(@PathVariable("userId") Integer userId,@RequestParam Integer productId) {
		String result = null;
		try {
			result = uService.placeAnOrder(userId,productId);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/proceedCart/{userId}")
	public ResponseEntity<String> proceedCart(@PathVariable("userId") Integer userId) {
		String result = null;
		try {
			result = uService.proceedCart(userId);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	

}
