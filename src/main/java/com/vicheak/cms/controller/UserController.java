package com.vicheak.cms.controller;

import com.vicheak.cms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String viewUser(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.findAllUsers());
        return "user/main";
    }

}
