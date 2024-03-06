package com.example.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exceptions.CartException;
import com.example.Exceptions.CategoryException;
import com.example.Exceptions.OrderException;
import com.example.Exceptions.ProductException;
import com.example.Exceptions.UserException;
import com.example.Model.Cart;
import com.example.Model.CartProduct;
import com.example.Model.Category;
import com.example.Model.Orders;
import com.example.Model.OrdersProduct;
import com.example.Model.Product;
import com.example.Model.User;
import com.example.Model.UserSession;
import com.example.Repository.CartProductRepository;
import com.example.Repository.CartRepository;
import com.example.Repository.CategoryRepository;
import com.example.Repository.OrdersRepository;
import com.example.Repository.ProductRepository;
import com.example.Repository.UserRepository;
import com.example.Repository.UserSessionRepository;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private OrdersRepository oRepo;
	
	@Autowired
	
	private ProductRepository pRepo;
	
	@Autowired
	private CategoryRepository cRepo;
	
	@Autowired
	private CartProductRepository cpr;
	
	@Autowired
	private UserSessionRepository usession;
	@Override
	public Cart showCart(int userId){
		// TODO Auto-generated method stub
		User us=userRepo.findById(userId).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+userId +". Please register first !");
		}
		Cart cart=us.getCart();
		return cart;
	}
	
	@Override
	public List<Product> getAllProducts() {
		List<Product> list=new ArrayList<>();
		list =pRepo.findAll();
		return list;
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
	public String updateProfile(String email,String mobileNumber,String username,String picture,String address,String sessionid) {
		UserSession uss=usession.findBySessionId(sessionid);
		if(uss==null) {
			throw new UserException("Not logged in !! . Please login first.");
		}
		User us=userRepo.findById(uss.getUserid()).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+uss.getUserid() +". Please register first !");
			
		}
		if(address!=null) {
			us.setAddress(address);
		}
		if(mobileNumber!=null) {
			us.setMobileNumber(mobileNumber);
		}
		if(picture!=null) {
			us.setPicture(picture);
		}
		if(username!=null) {
			us.setUserName(username);
		}
		if(email!=null) {
			us.setUserEmail(email);
		}
		userRepo.save(us);
		return "Profile updated successfully";
		}

	@Override
	public String registerUser(User user) {
		// TODO Auto-generated method stub
		User us=userRepo.findByMobileNumber(user.getMobileNumber());
		
		if(us!=null) {
			//throwing user already exists exception
			throw new UserException("User already exists with that mobile number :"+user.getMobileNumber());
		}
		Cart cart=new Cart();
		cart.setUser(user);
		
		user.setCart(cart);
		userRepo.save(user);
		
		return "User Registered Successfully";
	}

	@Override
	public List<Orders> getOrder(String sessionid) {
		UserSession uss=usession.findBySessionId(sessionid);
		if(uss==null) {
			throw new UserException("Not logged in !! . Please login first.");
		}
		User us=userRepo.findById(uss.getUserid()).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+uss.getUserid() +". Please register first !");
		}
		if(us.getOrders().isEmpty()) {
			throw new OrderException("No Orders found for user with  UserId : "+uss.getUserid() +". Please order something first !");
		}
		
		return us.getOrders();
	}



	@Override
	public String proceedCart(String sessionid) {
		UserSession uss=usession.findBySessionId(sessionid);
		if(uss==null) {
			throw new UserException("Not logged in !! . Please login first.");
		}
		User us=userRepo.findById(uss.getUserid()).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+uss.getUserid() +". Please register first !");
		}
		Cart cart=us.getCart();
		if(cart.getCartProducts().size()==0) {
			throw new CartException("Oops!!. Cart is empty . Add products to cart");

		}
		
		List<CartProduct> lcp=cart.getCartProducts();
		List<Product> prodList=new ArrayList<>();
		for(CartProduct pro : lcp) {
			prodList.add(pro.getProduct());
		}
		List<Orders> orders=new ArrayList<>();
		for(Product pro : prodList) {
			Orders newOrder=new Orders();
			OrdersProduct op=new OrdersProduct();
			op.setProduct(pro);
			op.setOrders(newOrder);
			
			newOrder.setOrderDate(LocalDateTime.now());
			newOrder.getProduct().add(op);
			orders.add(newOrder);
			us.getOrders().add(newOrder);
			orders.add(newOrder);
			cart.setQuantity(cart.getQuantity() - 1);
	        cart.setTotalPrice(cart.getTotalPrice() - pro.getPrice());
		}
		
		// Set the Cart's cartProducts list to an empty list
	    cart.setCartProducts(new ArrayList<>());
		oRepo.saveAll(orders);
		cartRepo.save(cart);
		
		
		cpr.deleteAll(lcp);
		// TODO Auto-generated method stub
		return "Order placed Successfully";
	}
	
	@Override
	public String addToCart(int userId, int productId) {
		User us=userRepo.findById(userId).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+userId +". Please register first !");
		}
		Cart cart=us.getCart();
		if(cart==null) {
			cart=new Cart();
			cart.setUser(us);
		}
		Product pro=pRepo.findById(productId).orElse(null);
		if(pro==null) {
			throw new ProductException("Product doesn't exist with that id : "+productId +" ." );
		}
		
		CartProduct cp=new CartProduct();
		cp.setCart(cart);
		cp.setProduct(pro);
		
		cart.getCartProducts().add(cp);
		cart.setQuantity(cart.getQuantity()+1);
		cart.setTotalPrice(cart.getTotalPrice()+pro.getPrice());
		us.setCart(cart);
		userRepo.save(us);
		return "added to cart";
	}
	
	@Override
	public String placeAnOrder( int productId,String sessionid)  {
		
		UserSession uss=usession.findBySessionId(sessionid);
		if(uss==null) {
			throw new UserException("Not logged in !! . Please login first.");
		}
		
		User us=userRepo.findById(uss.getUserid()).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+uss.getUserid() +". Please register first !");
		}
		Product pro=pRepo.findById(productId).orElse(null);
		if(pro==null) {
			throw new ProductException("Product doesn't exist with that id : "+productId +" ." );
		}
		OrdersProduct op=new OrdersProduct();
		Orders newOrder=new Orders();
		op.setProduct(pro);
		op.setOrders(newOrder);
		newOrder.setOrderDate(LocalDateTime.now());
		newOrder.getProduct().add(op);
		
		us.getOrders().add(newOrder);
		
		oRepo.save(newOrder);
		// TODO Auto-generated method stub
		return "Order placed Successfully";
	}

	
	
	public String removeFromCart(int userId, int productId)  {
	    User user = userRepo.findById(userId).orElse(null);
	    if (user == null) {
	    	throw new UserException("No User found with  UserId : "+userId +". Please register first !");
	    }

	    Cart cart = user.getCart();
	    Product product = pRepo.findById(productId).orElse(null);
	    if (product == null) {
	    	throw new ProductException("Product doesn't exist with that id : "+productId +" ." );
	    }

	    List<CartProduct> cartProducts = cart.getCartProducts();
	    if (cartProducts.isEmpty()) {
	    	throw new CartException("Oops!!. Cart is empty . Add products to cart");
	    }

	    CartProduct cartProductToRemove = null;
	    for (CartProduct cartProduct : cartProducts) {
	        if (cartProduct.getProduct().getProductId().equals(productId)) {
	            cartProductToRemove = cartProduct;
	            break;
	        }
	    }

	    if (cartProductToRemove != null) {
	        cartProducts.remove(cartProductToRemove);
	        cart.setCartProducts(cartProducts);
	        cart.setQuantity(cart.getQuantity() - 1);
	        cart.setTotalPrice(cart.getTotalPrice() - product.getPrice());

	        // Save the changes to update the cart
	        cartRepo.save(cart);

	        // Remove the cart product association from the database
	        cpr.delete(cartProductToRemove);

	        return "Removed from cart";
	    }

	    return "Product not found in cart";
	}


	@Override
	public String changePassword(int userId, String password) {
		User us=userRepo.findById(userId).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+userId +". Please register first !");
		}
		us.setPassword(password);
		userRepo.save(us);
		
		// TODO Auto-generated method stub
		return "Password Changed successfully";
	}



}
