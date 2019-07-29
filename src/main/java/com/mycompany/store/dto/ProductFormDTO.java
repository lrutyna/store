package com.mycompany.store.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

import com.mycompany.store.model.Category;


public class ProductFormDTO {
	
	private Long id;

	@NotEmpty(message="To pole nie może być puste.")
	private String name="";
	
	@NotEmpty(message="To pole nie może być puste.")
	private String description="";
	
	@Min(value = 0, message="To pole nie może być ujemne.")
	private Long quantityInStock;
	
	@Min(value = (long) 0.01, message="To pole nie może mniejsze lub równe 0.")
	private double price;
	
	@Range(min = 1, message="Kategoria nie została wybrana.")
	private Long categoryId;
	
	private Category category;
	
	
	public String getName() {
		return name;
	}
	public void setName(String productName) {
		this.name = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(Long quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
