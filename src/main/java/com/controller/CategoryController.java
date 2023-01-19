package com.controller;

import com.dao.CategoryDAO;

import com.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryDAO categoryDAO;

    @PostMapping
    public void create(@RequestBody Category category) {
        categoryDAO.createCategory(category);
    }

    @GetMapping("{id}")
    public Category read(@PathVariable Long id) {
        return categoryDAO.readCategory(id);
    }

    @PutMapping
    public void update(@RequestBody Category category) {
        categoryDAO.updateCategory(category);
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryDAO.getAllCategories();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        categoryDAO.deleteCategory(id);
    }
}
