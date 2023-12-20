package com.vicheak.cms.controller;

import com.vicheak.cms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String viewCategory(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findCategories());
        return "category/main";
    }

}
