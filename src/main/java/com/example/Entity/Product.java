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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productTitle;
	private String productDesc;
	private String productImage;
	private int price;
	private int discount;
	private int quantity;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrdersProduct> ordersProducts = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	//@JsonIgnore
	private Category category;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	@JsonIgnore
    private List<CartProduct> cartProducts = new ArrayList<>();

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer productId, String productTitle, String productDesc, String productImage, int price,
			int discount, int quantity, List<OrdersProduct> ordersProducts, Category category,
			List<CartProduct> cartProducts) {
		super();
		this.productId = productId;
		this.productTitle = productTitle;
		this.productDesc = productDesc;
		this.productImage = productImage;
		this.price = price;
		this.discount = discount;
		this.quantity = quantity;
		this.ordersProducts = ordersProducts;
		this.category = category;
		this.cartProducts = cartProducts;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productTitle=" + productTitle + ", productDesc=" + productDesc
				+ ", productImage=" + productImage + ", price=" + price + ", discount=" + discount + ", quantity="
				+ quantity + ", ordersProducts=" + ordersProducts + ", category=" + category + ", cartProducts="
				+ cartProducts + "]";
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<OrdersProduct> getOrdersProducts() {
		return ordersProducts;
	}

	public void setOrdersProducts(List<OrdersProduct> ordersProducts) {
		this.ordersProducts = ordersProducts;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}



	

}
