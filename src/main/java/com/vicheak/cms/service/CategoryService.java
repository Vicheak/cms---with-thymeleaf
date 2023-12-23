package com.vicheak.cms.service;

import com.vicheak.cms.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findCategories();

    void createNewCategory(Category category);

    void deleteCategoryById(Integer id);

}
