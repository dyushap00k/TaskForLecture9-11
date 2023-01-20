package com.service;

import com.model.Category;
import com.model.dto.CategoryCreationDTO;
import com.model.dto.CategoryDTO;

import java.util.List;

public class CategoryDtoMapper {
    private CategoryDtoMapper(){}

    public static CategoryDTO toDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static Category toCategory(CategoryCreationDTO categoryCreationDTO) {
        Category category = new Category();
        category.setId(categoryCreationDTO.getId());
        category.setName(categoryCreationDTO.getName());
        return category;
    }

    public static List<CategoryDTO> allToDto(List<Category> categories){
        return categories.stream()
                .map(CategoryDtoMapper::toDto)
                .toList();
    }
}
