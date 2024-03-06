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

import com.example.Exceptions.CartException;
import com.example.Exceptions.CategoryException;
import com.example.Exceptions.OrderException;
import com.example.Exceptions.ProductException;
import com.example.Exceptions.UserException;
import com.example.Model.Cart;
import com.example.Model.Category;
import com.example.Model.LoginDTO;
import com.example.Model.Orders;
import com.example.Model.Product;
import com.example.Model.User;
import com.example.Service.LoginService;
import com.example.Service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	@Autowired
	private UserService uService;
	
	@Autowired
	private LoginService logS;
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> createUserHandler(@Valid @RequestBody User user)	{
		String a = uService.registerUser(user);
		
		return new ResponseEntity<String>(a, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> result  = uService.getAllCategory();
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> result = uService.getAllProducts();
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> logInUserHandler(@Valid @RequestBody LoginDTO dto) throws UserException {
		LoginDTO ldto = new LoginDTO(dto.getPhonenumber(), dto.getPassword());
		String result = logS.loginUser(ldto);
		return new ResponseEntity<String>(result,HttpStatus.OK );
	}
	
	@PostMapping("/logout")
	public String logoutUserHandler(@Valid @RequestParam(required = false) String sessionid) throws UserException {
		return logS.logOut(sessionid);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUserHandler(@Valid @RequestParam(required = false) String email,@RequestParam(required = false) String mobileNumber,@RequestParam(required = false) String picture,@RequestParam(required = false) String username,@RequestParam String address,@RequestParam String sessionid){
		String a = uService.updateProfile(email,mobileNumber,username,picture,address,sessionid);
		return new ResponseEntity<String>(a, HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword/{id}")
	public ResponseEntity<String> updatePasswordHandler(@Valid @PathVariable("id") Integer id,@RequestParam String password){
		String a  = uService.changePassword(id,password);
		
		return new ResponseEntity<String>(a, HttpStatus.OK);
	}

	@PostMapping("/addToCart/{userId}")
	public ResponseEntity<String> addToCartHandler(@Valid @PathVariable("userId") Integer userId, @RequestParam Integer pId) {
	    String result  = uService.addToCart(userId, pId);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/removeFromCart/{userId}")
	public ResponseEntity<String> removefromCartHandler(@Valid @PathVariable("userId") Integer userId, @RequestParam Integer pId) {
	    String result = uService.removeFromCart(userId, pId);
		
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/showCart/{userId}")
	public ResponseEntity<Cart> showCart(@Valid @PathVariable("userId") Integer userId) {
	    Cart result =  uService.showCart(userId);
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getOrder")
	public ResponseEntity<List<Orders>> getAllOrders(@Valid @RequestParam String sessionid) {
		List<Orders> result = uService.getOrder(sessionid);
		
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/placeOrder")
	public ResponseEntity<String> placeOrders(@Valid @RequestParam Integer productId,@RequestParam String sessionid) {
		String result = uService.placeAnOrder(productId,sessionid);
		
	    
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/proceedCart")
	public ResponseEntity<String> proceedCart(@Valid @RequestParam String sessionid) {
		String result = uService.proceedCart(sessionid);
	    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	

}
