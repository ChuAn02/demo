package com.example.app.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


/**
 * Đó là DTO đại diện cho một danh mục
 */
@Getter
@Setter
public class CategoryDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    private Long categoryParent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryParent() {
		return categoryParent;
	}

	public void setCategoryParent(Long categoryParent) {
		this.categoryParent = categoryParent;
	}

    
}