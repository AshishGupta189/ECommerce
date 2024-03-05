package com.example.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
public class Cart {

	public Cart() {
	}

	




	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", user=" + user + ", product=" + ""
				+ ", totalPrice=" + totalPrice + ", quantity=" + quantity + "]";
	}



	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}






	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}






	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public List<Product> getProduct() {
//		return products;
//	}
//
//	public void setProduct(List<Product> product) {
//		this.products = product;
//	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart(User user, List<Product> product, int totalPrice, int quantity) {
		super();
		this.user = user;
		//this.products = product;
		this.totalPrice = totalPrice;
		this.quantity = quantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts = new ArrayList<>();
	
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<Product> products=new ArrayList<>();
	
	private int totalPrice;
	
	private int quantity;
}
