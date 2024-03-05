package com.example.Service;

import java.util.List;

import com.example.Entity.Category;
import com.example.Entity.Orders;
import com.example.Entity.Product;
import com.example.Entity.User;
import com.example.Exceptions.CategoryException;

public interface AdminService {

	public List<Category> getAllCategory() throws CategoryException;
	public List<Product> getAllProducts();
	public List<User> getAllUsers();
	public List<Orders> getAllOrders();
	public String addProduct(Product product);
	public String addCategory(Category category);
	public String updateProfile(int userId,User user);
	public String changePassword(int userId, String password);
	
}
