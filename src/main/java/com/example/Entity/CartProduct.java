package com.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_products")
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    private Integer cartProductId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

	public CartProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartProduct(Integer cartProductId, Cart cart, Product product) {
		super();
		this.cartProductId = cartProductId;
		this.cart = cart;
		this.product = product;
	}

	public Integer getCartProductId() {
		return cartProductId;
	}

	public void setCartProductId(Integer cartProductId) {
		this.cartProductId = cartProductId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartProduct [cartProductId=" + cartProductId + ", cart=" + cart + ", product=" + product + "]";
	}

    
    // Constructors, getters, setters...
}

