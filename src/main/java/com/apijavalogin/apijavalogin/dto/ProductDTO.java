package com.apijavalogin.apijavalogin.dto;

import com.apijavalogin.apijavalogin.entities.Product;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String category;
    private Double price;
    private String img;

    public ProductDTO(Long id, String name, String category, Double price, String img) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.img = img;
	}
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.category = product.getCategory();
		this.price = product.getPrice();
		this.img = product.getImg();
	}
}
