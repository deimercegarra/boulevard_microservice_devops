package com.pragma.boulevard_microservice_devops.application.handler;


import com.pragma.boulevard_microservice_devops.application.dto.request.CategoryRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {

    public void saveCategory(CategoryRequestDto categoryRequestDto);

    public List<CategoryResponseDto> getAllCategories();

    public CategoryResponseDto getCategory(Long categoryId);

    public void updateCategory(CategoryRequestDto categoryRequestDto);

    public void deleteCategory(Long categoryId);

}