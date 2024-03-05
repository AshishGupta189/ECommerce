package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Cart;
import com.example.Entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
