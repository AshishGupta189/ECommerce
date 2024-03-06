package com.example.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "orders_product")
public class OrdersProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_product_id")
    private Integer ordersProductId;

    @ManyToOne
    @JoinColumn(name = "orders_order_id")
    @JsonIgnore
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_product_id")
    private Product product;
    

	@Override
	public String toString() {
		return "OrdersProduct [ordersProductId=" + ordersProductId + ", orders=" + orders + ", product=" + product
				+ "]";
	}


	public Integer getOrdersProductId() {
		return ordersProductId;
	}


	public void setOrdersProductId(Integer ordersProductId) {
		this.ordersProductId = ordersProductId;
	}


	public Orders getOrders() {
		return orders;
	}


	public void setOrders(Orders orders) {
		this.orders = orders;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public OrdersProduct() {
		super();
	}


	public OrdersProduct(Integer ordersProductId, Orders orders, Product product) {
		super();
		this.ordersProductId = ordersProductId;
		this.orders = orders;
		this.product = product;
	}

    
    // Constructors, getters, setters...
}

