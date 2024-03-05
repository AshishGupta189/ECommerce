package com.example.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Cart;
import com.example.Entity.CartProduct;
import com.example.Entity.Orders;
import com.example.Entity.OrdersProduct;
import com.example.Entity.Product;
import com.example.Entity.User;
import com.example.Exceptions.CartException;
import com.example.Exceptions.OrderException;
import com.example.Exceptions.ProductException;
import com.example.Exceptions.UserException;
import com.example.Repository.CartProductRepository;
import com.example.Repository.CartRepository;
import com.example.Repository.OrdersRepository;
import com.example.Repository.ProductRepository;
import com.example.Repository.UserRepository;

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
	private CartProductRepository cpr;
	@Override
	public Cart showCart(int userId) throws UserException {
		// TODO Auto-generated method stub
		User us=userRepo.findById(userId).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+userId +". Please register first !");
		}
		Cart cart=us.getCart();
		return cart;
	}

	@Override
	public String updateProfile(int userId, User user) throws UserException {
		User us=userRepo.findById(userId).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+userId +". Please register first !");
			
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
		userRepo.save(us);
		return "Profile updated successfully";
		}

	@Override
	public String registerUser(User user) throws UserException {
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
	public List<Orders> getOrder(int userId) throws UserException, OrderException {
		User us=userRepo.findById(userId).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+userId +". Please register first !");
		}
		if(us.getOrders().isEmpty()) {
			throw new OrderException("No Orders found for user with  UserId : "+userId +". Please order something first !");
		}
		
		return us.getOrders();
	}



	@Override
	public String proceedCart(int userId) throws UserException, CartException {
		User us=userRepo.findById(userId).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+userId +". Please register first !");
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
	public String addToCart(int userId, int productId) throws UserException, ProductException {
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
	public String placeAnOrder(int userId, int productId) throws UserException,ProductException {
		User us=userRepo.findById(userId).orElse(null);
		if(us==null) {
			throw new UserException("No User found with  UserId : "+userId +". Please register first !");
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

	
	
	public String removeFromCart(int userId, int productId) throws UserException , ProductException , CartException {
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
	public String changePassword(int userId, String password) throws UserException {
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
