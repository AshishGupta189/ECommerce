package com.example.Service;

import java.util.List;

import com.example.Model.Admin;
import com.example.Model.Category;
import com.example.Model.Orders;
import com.example.Model.Product;
import com.example.Model.User;

public interface AdminService {

	public List<Category> getAllCategory();
	public Admin createAdmin(Admin admin);
	public List<Product> getAllProducts();
	public List<User> getAllUsers(String sessionid);
	public List<Orders> getAllOrders(String sessionid);
	public String addProduct(Product product,String sessionid);
	public String addCategory(Category category,String sessionid);
	public String updateProfile(String email,String mobileNumber,String username,String picture,String sessionid);
	public String changePassword(int userId, String password);
	
}
