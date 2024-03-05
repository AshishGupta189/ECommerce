package com.example.Service;

import java.util.List;

import com.example.Entity.Cart;
import com.example.Entity.Orders;
import com.example.Entity.User;
import com.example.Exceptions.CartException;
import com.example.Exceptions.OrderException;
import com.example.Exceptions.ProductException;
import com.example.Exceptions.UserException;

public interface UserService {

	public Cart showCart(int userid) throws UserException;
	public String registerUser(User user) throws UserException;
	public List<Orders> getOrder(int userId) throws UserException, OrderException;
	public String placeAnOrder(int userId, int pId) throws UserException, ProductException;
	public String addToCart(int userId,int productId) throws UserException, ProductException;
	public String removeFromCart(int userId,int productId) throws UserException, ProductException, CartException;
	public String updateProfile(int userId,User user) throws UserException;
	public String changePassword(int userId, String password) throws UserException;
	public String proceedCart(int userId) throws UserException, CartException;
	
}
