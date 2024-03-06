package com.example.Model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	
	@OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrdersProduct> ordersProducts = new ArrayList<>();
	
	
	@NotNull(message =  "order date can't be null")
	private LocalDateTime orderDate;
	
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orders(Integer orderId, List<OrdersProduct> product, LocalDateTime orderDate) {
		super();
		this.orderId = orderId;
		this.ordersProducts = product;
		this.orderDate = orderDate;
		
	}
	
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", product=" + "" +  ", orderDate="
				+ orderDate + "]";
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public List<OrdersProduct> getProduct() {
		return ordersProducts;
	}
	public void setProduct(List<OrdersProduct> product) {
		this.ordersProducts = product;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	
	

}
