package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	
}
