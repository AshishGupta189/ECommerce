package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Cart;
import com.example.Entity.CartProduct;
import com.example.Entity.Product;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
	
	
}
