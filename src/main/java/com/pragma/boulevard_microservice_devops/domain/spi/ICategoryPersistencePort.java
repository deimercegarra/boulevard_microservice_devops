package com.pragma.boulevard_microservice_devops.domain.spi;


import com.pragma.boulevard_microservice_devops.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryPersistencePort {

    void saveCategory(CategoryModel categoryModel);

    List<CategoryModel> getAllCategories();

    CategoryModel getCategory(Long userId);

    void updateCategory(CategoryModel categoryModel);

    void deleteCategory(Long userId);

}