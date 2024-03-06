package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Cart;
import com.example.Model.CartProduct;
import com.example.Model.Product;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
	
	
}
