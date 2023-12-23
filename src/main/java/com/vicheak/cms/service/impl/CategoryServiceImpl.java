package com.vicheak.cms.service.impl;

import com.vicheak.cms.model.Category;
import com.vicheak.cms.repository.CategoryRepository;
import com.vicheak.cms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findCategories() {
        return categoryRepository.select();
    }

    @Override
    public void createNewCategory(Category category) {
        categoryRepository.insert(category);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        boolean isDeleted = categoryRepository.updateIsDeletedById(id, true);

        if(!isDeleted)
            throw new RuntimeException("Category is failed to be deleted!");
    }

}
