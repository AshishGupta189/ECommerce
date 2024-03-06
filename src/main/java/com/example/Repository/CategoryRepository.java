package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
