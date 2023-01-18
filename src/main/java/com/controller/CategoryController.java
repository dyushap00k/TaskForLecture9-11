package com.controller;

import com.dao.CategoryDAO;

import com.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryDAO categoryDAO;

    @PostMapping("/create")
    public void create(@RequestBody Category category) {
        categoryDAO.createCategory(category);
    }

    @GetMapping("/read/{id}")
    public Category read(@PathVariable Long id) {
        return categoryDAO.readCategory(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Category category) {
        categoryDAO.updateCategory(category);
    }

    @GetMapping("/get-all")
    public List<Category> getAll(){
        return categoryDAO.getAllCategories();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        categoryDAO.deleteCategory(id);
    }
}
