package com.apijavalogin.apijavalogin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apijavalogin.apijavalogin.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
