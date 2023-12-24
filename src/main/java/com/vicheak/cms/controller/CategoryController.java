package com.vicheak.cms.controller;

import com.vicheak.cms.model.Category;
import com.vicheak.cms.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
                                   ModelMap modelMap) {
        modelMap.addAttribute("category", category);
        modelMap.addAttribute("isEditAction", false);
        return "category/form";
    }

    @PostMapping("/create")
    public String createNewCategory(@RequestParam("isEditAction") Boolean isEditAction,
                                    @Valid Category category,
                                    BindingResult bindingResult,
                                    ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            //System.out.println("Has errors!");
            modelMap.addAttribute("isEditAction", isEditAction);

            return "category/form";
        }

        //System.out.println("Category Name : " + category.getName());
        //System.out.println("Category Is Deleted : " + category.getIsDeleted());

        if (!isEditAction)
            categoryService.createNewCategory(category);
        else
            categoryService.editCategoryById(category);

        return "redirect:/category";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategoryById(@PathVariable Integer id) {
        //System.out.println(id);

        categoryService.deleteCategoryById(id);

        return "redirect:/category";
    }

    @GetMapping("/{id}/detail")
    public String viewCategoryDetail(@PathVariable Integer id,
                                     ModelMap modelMap) {
        Category category;
        try {
            category = categoryService.findCategoryById(id);
        } catch (RuntimeException ex) {
            return "error/notfound";
        }

        modelMap.addAttribute("category", category);

        return "category/detail";
    }

    @GetMapping("/{id}/edit")
    public String viewEditCategoryForm(@PathVariable Integer id,
                                       ModelMap modelMap) {
        Category category;
        try {
            category = categoryService.findCategoryById(id);
        } catch (RuntimeException ex) {
            return "error/notfound";
        }

        modelMap.addAttribute("category", category);
        modelMap.addAttribute("isEditAction", true);

        return "category/form";
    }

}
