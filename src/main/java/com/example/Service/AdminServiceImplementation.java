package com.example.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Category;
import com.example.Entity.Orders;
import com.example.Entity.Product;
import com.example.Entity.User;
import com.example.Exceptions.CategoryException;
import com.example.Repository.CategoryRepository;
import com.example.Repository.OrdersRepository;
import com.example.Repository.ProductRepository;
import com.example.Repository.UserRepository;

@Service
public class AdminServiceImplementation implements AdminService {

	@Autowired
	private CategoryRepository cRepo;
	
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private ProductRepository pRepo;
	@Autowired
	private OrdersRepository oRepo;
	@Override
	public List<Category> getAllCategory() throws CategoryException {
		// TODO Auto-generated method stub
		List<Category> list=new ArrayList<>();
		list =cRepo.findAll();
		if(list.isEmpty()) {
			throw new CategoryException("No categories present");
		}
		return list;
	}
	@Override
	public List<Product> getAllProducts() {
		List<Product> list=new ArrayList<>();
		list =pRepo.findAll();
		return list;
	}
	@Override
	public List<User> getAllUsers() {
		List<User> list=new ArrayList<>();
		list =uRepo.findAll();
		return list;
	}
	@Override
	public List<Orders> getAllOrders() {
		List<Orders> list=new ArrayList<>();
		list =oRepo.findAll();
		return list;
	}
	@Override
	public String addProduct(Product product) {
	    System.out.println(product);

	    // Fetch the category from the repository to ensure it is managed
	    Category category = cRepo.findById(product.getCategory().getCategoryId()).orElse(null);

	    // If the category is not found, you might want to handle this case appropriately

	    // Create a new product
	    Product newProduct = new Product();
	    newProduct.setDiscount(product.getDiscount());
	    newProduct.setPrice(product.getPrice());
	    newProduct.setProductDesc(product.getProductDesc());
	    newProduct.setProductImage(product.getProductImage());
	    newProduct.setQuantity(product.getQuantity());
	    newProduct.setProductTitle(product.getProductTitle());

	    // Associate the new product with the existing or fetched category
	    newProduct.setCategory(category);
	    category.getList().add(newProduct);

	    // Save the new product, which should cascade to save the associated category
	    pRepo.save(newProduct);

	    return "Product saved";
	}

	@Override
	public String addCategory(Category category) {

		Category newCategory = new Category();
	    newCategory.setCategoryTitle(category.getCategoryTitle());
	    newCategory.setCategoryDesc(category.getCategoryDesc());

	    // Create a new list to hold associated products
	    List<Product> productList = new ArrayList<>();

	    // Iterate over the products in the input category
	    for (Product originalProduct : category.getList()) {
	        // Create a new Product object
	        Product newProduct = new Product();
	        newProduct.setProductTitle(originalProduct.getProductTitle());
	        newProduct.setProductDesc(originalProduct.getProductDesc());
	        newProduct.setProductImage(originalProduct.getProductImage());
	        newProduct.setPrice(originalProduct.getPrice());
	        newProduct.setDiscount(originalProduct.getDiscount());
	        newProduct.setQuantity(originalProduct.getQuantity());

	        // Associate the new product with the new category
	        newProduct.setCategory(newCategory);

	        // Add the new product to the list
	        productList.add(newProduct);
	    }

	    // Set the list of products to the new category
	    newCategory.setList(productList);

	    // Save the new category, which should cascade to save the associated products
	    cRepo.save(newCategory);

	    return "Category saved";
	}

	@Override
	public String updateProfile(int userId, User user) {
		User us=uRepo.findById(userId).orElse(null);
		if(us==null) {
			//throwing user not present exception
			return "No user found";
		}
		if(user.getAddress()!=null) {
			us.setAddress(user.getAddress());
		}
		if(user.getMobileNumber()!=null) {
			us.setMobileNumber(user.getMobileNumber());
		}
		if(user.getPicture()!=null) {
			us.setPicture(user.getPicture());
		}
		if(user.getUserName()!=null) {
			us.setUserName(user.getUserName());
		}
		if(user.getUserEmail()!=null) {
			us.setUserEmail(user.getUserEmail());
		}
		uRepo.save(us);
		return "Profile updated successfully";
		}
	
	@Override
	public String changePassword(int userId, String password) {
		User us=uRepo.findById(userId).orElse(null);
		if(us==null) {
			//throwing user not present exception
			return "No User found";
		}
		us.setPassword(password);
		uRepo.save(us);
		
		// TODO Auto-generated method stub
		return "Password Changed successfully";
	}
	

}
