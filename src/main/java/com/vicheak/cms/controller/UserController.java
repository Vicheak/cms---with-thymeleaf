package com.vicheak.cms.controller;

import com.vicheak.cms.model.User;
import com.vicheak.cms.service.RoleService;
import com.vicheak.cms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String viewUser(ModelMap modelMap) {
        //userService.findAllUsers().forEach(System.out::println);
        modelMap.addAttribute("users", userService.findAllUsers());
        return "user/main";
    }

    @GetMapping("/form")
    public String viewUserForm(User user,
                               ModelMap modelMap) {
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roleService.findRoles());
        modelMap.addAttribute("isEditAction", false);
        return "user/form";
    }

    @PostMapping("/create")
    public String saveUser(@RequestParam("isEditAction") Boolean isEditAction,
                           @Valid User user,
                           BindingResult bindingResult,
                           ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            System.out.println("Has errors");
            modelMap.addAttribute("roles", roleService.findRoles());
            modelMap.addAttribute("isEditAction", isEditAction);

            return "user/form";
        }

        //check the roles
        boolean isMatched = user.getRoles().stream()
                .allMatch(role -> role.getId() == null);
        if(isMatched){
            System.out.println("No role selected!");
            bindingResult.addError(new FieldError("user", "roles",
                    "Please select at least one role!"));

            modelMap.addAttribute("roles", roleService.findRoles());
            modelMap.addAttribute("isEditAction", isEditAction);

            return "user/form";
        }

        /*System.out.println("Is edit action : " + isEditAction);
        System.out.println(user);
        System.out.println(user.getRoles());*/

        if(!isEditAction){
            userService.saveUser(user);
        }

        return "redirect:/user";
    }

}
