package com.example.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exceptions.CategoryException;
import com.example.Exceptions.ProductException;
import com.example.Exceptions.UserException;
import com.example.Model.Admin;
import com.example.Model.Category;
import com.example.Model.Orders;
import com.example.Model.Product;
import com.example.Model.User;
import com.example.Model.UserSession;
import com.example.Repository.AdminRepository;
import com.example.Repository.CategoryRepository;
import com.example.Repository.OrdersRepository;
import com.example.Repository.ProductRepository;
import com.example.Repository.UserRepository;
import com.example.Repository.UserSessionRepository;

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
	
	@Autowired
	private AdminRepository admindao;
	
	@Autowired
	private UserSessionRepository usersessiondao;
	
	@Override
	public Admin createAdmin(Admin admin) throws UserException {
		Admin a=admindao.findByMobileNumber(admin.getMobileNumber());
		if(a!=null) {
			throw new UserException("Admin alrady exists");
		}
			return admindao.save(admin);
		
	}
	
	@Override
	public List<Category> getAllCategory(){
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
		if(list.isEmpty()) {
			throw new ProductException("No Products present");
		}
		return list;
	}
	@Override
	public List<User> getAllUsers(String sessionid) {
		UserSession us=usersessiondao.findBySessionId(sessionid);
		if(us!=null) {
			List<User> list=new ArrayList<>();
			list =uRepo.findAll();
			return list;
		}
		throw new UserException("Please login as Admin first");
		
	}
	@Override
	public List<Orders> getAllOrders(String sessionid) {
		
		UserSession us=usersessiondao.findBySessionId(sessionid);
		if(us!=null) {
			List<Orders> list=new ArrayList<>();
			list =oRepo.findAll();
			return list;
		}
		throw new UserException("Please login as Admin first");
	}
	@Override
	public String addProduct(Product product,String sessionid) {
		UserSession us=usersessiondao.findBySessionId(sessionid);
		if(us!=null) {
			// Fetch the category from the repository to ensure it is managed
		    Category category = cRepo.findById(product.getCategory().getCategoryId()).orElse(null);

		    // If the category is not found, you might want to handle this case appropriately

		    // Create a new product
		    Product newProduct = new Product();
		    newProduct.setPrice(product.getPrice());
		    newProduct.setProductDesc(product.getProductDesc());
		    newProduct.setProductImage(product.getProductImage());
		    newProduct.setProductTitle(product.getProductTitle());

		    // Associate the new product with the existing or fetched category
		    newProduct.setCategory(category);
		    category.getList().add(newProduct);

		    // Save the new product, which should cascade to save the associated category
		    pRepo.save(newProduct);

		    return "Product saved";
		}
		throw new UserException("Please login as Admin first");
	    
	}

	@Override
	public String addCategory(Category category,String sessionid) {
		
		UserSession us=usersessiondao.findBySessionId(sessionid);
		if(us==null) {
			throw new UserException("Please login as Admin first");
		}
		

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
	public String updateProfile(String email,String mobileNumber,String username,String picture,String sessionid) {
		UserSession uss=usersessiondao.findBySessionId(sessionid);
		if(uss==null) {
			throw new UserException("Please login as Admin first");
		}
		Admin us=admindao.findById(uss.getUserid()).orElse(null);
		if(us==null) {
			//throwing user not present exception
			throw new UserException("Admin not present");
		}
		
		if(email!=null) {
			us.setEmail(email);
		}
		if(mobileNumber!=null) {
			us.setMobileNumber(mobileNumber);
		}
		if(picture!=null) {
			us.setPicture(picture);
		}
		if(username!=null) {
			us.setUsername(username);
		}
		
		admindao.save(us);
		return "Profile updated successfully";
		}
	
	@Override
	public String changePassword(int userId, String password) {
		Admin us=admindao.findById(userId).orElse(null);
		if(us==null) {
			//throwing user not present exception
			throw new UserException("Admin not present");
		}
		us.setPassword(password);
		admindao.save(us);
		
		// TODO Auto-generated method stub
		return "Password Changed successfully";
	}
	

}
