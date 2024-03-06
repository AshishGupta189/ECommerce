package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Cart;
import com.example.Model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
