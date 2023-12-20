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
}
