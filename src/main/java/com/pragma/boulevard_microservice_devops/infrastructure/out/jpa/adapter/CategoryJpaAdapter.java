package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice_devops.domain.model.CategoryModel;
import com.pragma.boulevard_microservice_devops.domain.spi.ICategoryPersistencePort;
import com.pragma.boulevard_microservice_devops.infrastructure.exception.NoDataFoundException;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository iCategoryRepository;
    private final ICategoryEntityMapper iCategoryEntityMapper;

    @Override
    public void saveCategory(CategoryModel categoryModel) {
        iCategoryRepository.save(iCategoryEntityMapper.toEntity(categoryModel));
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        List<CategoryEntity> entityList = iCategoryRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return iCategoryEntityMapper.toModelList(entityList);
    }

    @Override
    public CategoryModel getCategory(Long userId) {
        return iCategoryEntityMapper.toModel(iCategoryRepository.findById(userId)
                .orElseThrow(NoDataFoundException::new));
    }

    @Override
    public void updateCategory(CategoryModel categoryModel) {
        iCategoryRepository.save(iCategoryEntityMapper.toEntity(categoryModel));
    }

    @Override
    public void deleteCategory(Long userId) {
        iCategoryRepository.deleteById(userId);
    }

}