package com.apijavalogin.apijavalogin.services;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.apijavalogin.apijavalogin.dto.ProductDTO;
import com.apijavalogin.apijavalogin.entities.Product;
import com.apijavalogin.apijavalogin.repositories.ProductRepository;
import com.apijavalogin.apijavalogin.services.excepitions.DatabaseException;
import com.apijavalogin.apijavalogin.services.excepitions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;



@Service
public class ProductService {
	

	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAll(Sort.by("name"));
        if (list.isEmpty()) {
            throw new RuntimeException("No products found");
        }
        return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> optional = repository.findById(id);
		Product entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		entity.setName(dto.getName());
		entity.setCategory(dto.getCategory());
		entity.setPrice(dto.getPrice());
		entity.setImg(dto.getImg());
		repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity.setCategory(dto.getCategory());
			entity.setPrice(dto.getPrice());
			entity.setImg(dto.getImg());
			repository.save(entity);
			return new ProductDTO(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}

	@Transactional
	public ProductDTO updatePatch(Long id, Map<String, Object> fields) {
		try {
			Product entity = repository.findById(id).get();
			merge(fields, entity);
			repository.save(entity);
			return new ProductDTO(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.findById(id).get();
		}
		catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		finally {
			try {
				repository.deleteById(id);
			}catch (DataIntegrityViolationException e) {
				throw new DatabaseException("Integrity vilation");
			}
		}
		
	}
	
	
	@SuppressWarnings("null")
    private void merge(Map<String, Object> fields, Product entity) {
		ObjectMapper objectMapper = new ObjectMapper();
		Product entityConvert = objectMapper.convertValue(fields, Product.class);
		fields.forEach((propertyName, propertyValue) -> {
			Field field = ReflectionUtils.findField(Product.class, propertyName);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, entityConvert);
			
			ReflectionUtils.setField(field, entity, newValue);
		});
	}

}
