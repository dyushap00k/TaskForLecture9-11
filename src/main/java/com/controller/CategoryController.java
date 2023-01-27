package com.controller;

import com.model.Category;
import com.model.dto.CategoryCreationDTO;
import com.model.dto.CategoryDTO;
import com.repos.CategoryRepo;
import com.service.CategoryDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepo repo;

    @Autowired
    CategoryController(CategoryRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    public void create(@RequestBody CategoryCreationDTO category) {
        repo.save(CategoryDtoMapper.toCategory(category));
    }

    @GetMapping("{id}")
    public CategoryDTO read(@PathVariable Long id) {
        return CategoryDtoMapper.toDto(repo.findById(id).get());
    }

    @PutMapping
    public void update(@RequestBody CategoryCreationDTO category) {
        repo.save(CategoryDtoMapper.toCategory(category));
    }

    @GetMapping
    public List<CategoryDTO> getAll() {
        return CategoryDtoMapper.allToDto((List<Category>) repo.findAll());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
