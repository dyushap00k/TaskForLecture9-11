package com.controller;

import com.dao.CategoryDAO;

import com.model.dto.CategoryCreationDTO;
import com.model.dto.CategoryDTO;
import com.service.CategoryDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryDAO categoryDAO;

    @PostMapping
    public void create(@RequestBody CategoryCreationDTO category) {
        categoryDAO.createCategory(CategoryDtoMapper.toCategory(category));
    }

    @GetMapping("{id}")
    public CategoryDTO read(@PathVariable Long id) {
        return CategoryDtoMapper.toDto(categoryDAO.readCategory(id));
    }

    @PutMapping
    public void update(@RequestBody CategoryCreationDTO category) {
        categoryDAO.updateCategory(CategoryDtoMapper.toCategory(category));
    }

    @GetMapping
    public List<CategoryDTO> getAll() {
        return CategoryDtoMapper.allToDto(categoryDAO.getAllCategories());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        categoryDAO.deleteCategory(id);
    }
}
