package com.example.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.app.dto.CategoryDTO;
import com.example.app.model.Category;
import com.example.app.repo.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    public CategoryService() {

    }
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category get(final Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public Long create(final CategoryDTO categoryDTO) {
        final Category category = new Category();
        mapToEntity(categoryDTO, category);
        return categoryRepository.save(category).getId();
    }
    public void update(final Long id, final CategoryDTO categoryDTO) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(categoryDTO, category);

        categoryRepository.save(category);
    }
    public void delete(final Long id) {
        categoryRepository.deleteById(id);
    }
    
    private Category mapToEntity(final CategoryDTO categoryDTO, Category category) {
        modelMapper.map(categoryDTO, category);
        if(categoryDTO.getCategoryParent()!=null){
            category.setCategoryParent(categoryRepository.findById(categoryDTO.getCategoryParent()).orElse(null));
        }

        return category;
    }
}
