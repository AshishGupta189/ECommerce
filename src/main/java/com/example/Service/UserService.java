package com.example.Service;

import java.util.List;

import com.example.Exceptions.CartException;
import com.example.Exceptions.OrderException;
import com.example.Exceptions.ProductException;
import com.example.Exceptions.UserException;
import com.example.Model.Cart;
import com.example.Model.Category;
import com.example.Model.Orders;
import com.example.Model.Product;
import com.example.Model.User;

public interface UserService {

	public Cart showCart(int userid);
	public String registerUser(User user);
	public List<Orders> getOrder( String sessionid);
	public String placeAnOrder( int pId,String sessionid);
	public String addToCart(int userId,int productId);
	public String removeFromCart(int userId,int productId);
	public String updateProfile(String email,String mobileNumber,String username,String picture,String address,String sessionid);
	public String changePassword(int userId, String password);
	public String proceedCart(String sessionid);
	public List<Category> getAllCategory();
	public List<Product> getAllProducts();
	
}
