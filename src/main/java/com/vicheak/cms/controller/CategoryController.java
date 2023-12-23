package com.vicheak.cms.controller;

import com.vicheak.cms.model.Category;
import com.vicheak.cms.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String viewCategory(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findCategories());
        return "category/main";
    }

    @GetMapping("/form")
    public String viewCategoryForm(Category category,
                                   ModelMap modelMap){
        modelMap.addAttribute("category", category);
        return "category/form";
    }

    @PostMapping("/create")
    public String createNewCategory(@Valid Category category,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            //System.out.println("Has errors!");

            return "category/form";
        }

        //System.out.println("Category Name : " + category.getName());
        //System.out.println("Category Is Deleted : " + category.getIsDeleted());
        categoryService.createNewCategory(category);

        return "redirect:/category";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategoryById(@PathVariable Integer id){
        //System.out.println(id);

        categoryService.deleteCategoryById(id);

        return "redirect:/category";
    }

}
